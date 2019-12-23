package com.weavernorth.taide.invoice.fentanDuofp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.weavernorth.taide.invoice.ConfigInfo;
import com.weavernorth.taide.util.TaiDeOkHttpUtils;
import org.apache.commons.codec.binary.Base64;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发票验重并变更发票状态-分摊多发票
 */
public class InvoiceCheckAndSubmitFentanDuofp extends BaseAction {
    private RecordSet selSet = new RecordSet();

    @Override
    public String execute(RequestInfo requestInfo) {
        String fpName = "uuid"; // 发票字段名
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("发票验重并变更发票状态-分摊多发票 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String mxbName = recordSet.getString("mxbName"); // 发票所在明细表名称(_dt1)
            String mainId = recordSet.getString("id");
            String lcbh = recordSet.getString("lcbh");
            String workCode = recordSet.getString("workcode");

            this.writeLog("mxbName: " + mxbName + ", fpName: " + fpName + ", lcbh: " + lcbh + ", workCode: " + workCode);

            // 查询明细表
            String mxSql = "select id, " + fpName + " from " + tableName + mxbName + " where mainid = " + mainId;
            this.writeLog("明细表查询sql：" + mxSql);
            recordSet.executeQuery(mxSql);
            List<String> bhList = new ArrayList<String>();
            while (recordSet.next()) {
                if (!"".equals(recordSet.getString(fpName))) {
                    bhList.add(recordSet.getString(fpName));
                }
            }
            this.writeLog("所有发票编号： " + JSONObject.toJSONString(bhList));

            // 验重
            StringBuilder stringBuilder = check(bhList);
            if (stringBuilder.length() > 0) {
                this.writeLog("重复发票号： " + stringBuilder.toString());
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("发票号重复： " + stringBuilder.toString());
                return "0";
            }

            // 变更发票状态
            String getInvoiceUrl = ConfigInfo.InvoiceUrl.getValue();
            String appSecId = ConfigInfo.appSecId.getValue();
            String appSecKey = ConfigInfo.appSecKey.getValue();
            String appId = ConfigInfo.appId.getValue();
            this.writeLog("更新发票信息开始========================");

            String sfdk = "N";
            String currentDate = TimeUtil.getCurrentDateString().replace("-", "");
            JSONArray dataArrayObject = new JSONArray();
            for (String invoice : bhList) {
                recordSet.executeQuery("select isDeductible from uf_fpinfo where uuid = '" + invoice + "'");
                if (recordSet.next()) {
                    JSONObject dataObject = new JSONObject(true);
                    dataObject.put("uuid", invoice);
                    dataObject.put("reimburseSerialNo", requestId); // 流程编号
                    dataObject.put("reimburseSource", "2"); // 单据来源
                    dataObject.put("reimburseState", "2"); // 0：未报销 2：报销中 3：已报销
                    dataObject.put("userId", workCode);

                    dataObject.put("certificateNumber", "");
                    String isDeductible = recordSet.getString("isDeductible");
                    if (!"".equalsIgnoreCase(isDeductible)) {
                        sfdk = isDeductible;
                    }
                    dataObject.put("isDeductible", sfdk); //  是否可抵扣
                    dataObject.put("reimburseDate", currentDate);
                    dataArrayObject.add(dataObject);
                }
            }

            String myDataStr = dataArrayObject.toJSONString().replaceAll("\\s*", "");
            this.writeLog("myDataStr======= " + myDataStr);

            Base64 base64 = new Base64();
            String srcStr = "POST/rest/openApi/invoice/dii?" +
                    "authorize={\"appSecId\":\"" + appSecId + "\"}" +
                    "&globalInfo={\"appId\":\"" + appId + "\",\"version\":\"v1.0\",\"interfaceCode\":\"REIMBURSE_UPDATE\",\"enterpriseCode\":\"null\"}" +
                    "&data=" + new String(base64.encode(myDataStr.getBytes()), "utf-8");
            this.writeLog("srcStr1: " + srcStr);

            SecretKeySpec keySpec = new SecretKeySpec(appSecKey.getBytes("utf-8"), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(keySpec);
            byte[] signBytes = mac.doFinal(srcStr.getBytes("utf-8"));

            String appSec = new String(base64.encode(signBytes), "utf-8");
            this.writeLog("appSec======================= " + appSec);

            JSONObject paramObject = new JSONObject(true);

            JSONObject authorizeObject = new JSONObject(true);
            authorizeObject.put("appSecId", appSecId);
            authorizeObject.put("appSec", appSec);
            paramObject.put("authorize", authorizeObject);

            JSONObject globalInfoObject = new JSONObject(true);
            globalInfoObject.put("appId", appId);
            globalInfoObject.put("version", "v1.0");
            globalInfoObject.put("interfaceCode", "REIMBURSE_UPDATE");
            paramObject.put("globalInfo", globalInfoObject);

            paramObject.put("data", dataArrayObject);

            this.writeLog("发送param： " + paramObject.toJSONString());

            // 调用接口
            String returnInvoice = TaiDeOkHttpUtils.post(getInvoiceUrl, paramObject.toJSONString());
            this.writeLog("更新发票接口返回： " + returnInvoice);

            JSONObject returnObject = JSONObject.parseObject(returnInvoice);
            JSONObject returnInfo = returnObject.getJSONObject("returnInfo");
            if (!"0000".equals(returnInfo.getString("returnCode"))) {
                this.writeLog("发票验重并变更发票状态-分摊InvoiceCheckAndSubmit异常： " + returnInvoice);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("发票验重并变更发票状态-分摊InvoiceCheckAndSubmit 异常： " + returnInvoice);
                return "0";
            }

            // 变更发票状态 ( 0：未报销 2：报销中 3：已报销)
            for (String invoice : bhList) {
                recordSet.executeUpdate("update uf_fpinfo set reimburseState = 2 where uuid = '" + invoice + "'");
            }

            this.writeLog("发票验重并变更发票状态-分摊多发票End ===============");
        } catch (Exception e) {
            this.writeLog("发票验重并变更发票状态-分摊多发票异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("发票验重并变更发票状态-多发票异常： " + e);
            return "0";
        }

        return "1";
    }


    private StringBuilder check(List<String> bhList) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String s : bhList) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        this.writeLog("各明细表编号次数： " + JSONObject.toJSONString(map));
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                stringBuilder.append(getNoByUUID(entry.getKey())).append(", ");
            }
        }
        return stringBuilder;
    }

    private String getNoByUUID(String uuid) {
        String invoiceNo = "";
        selSet.executeQuery("SELECT INVOICENO FROM UF_FPINFO where uuid = '" + uuid + "'");
        if (selSet.next()) {
            invoiceNo = selSet.getString("INVOICENO");
        }
        return invoiceNo;
    }
}
