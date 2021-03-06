package com.weavernorth.taide.invoice.fentanDuofp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.weavernorth.taide.invoice.ConfigInfo;
import com.weavernorth.taide.invoice.ConnUtil;
import com.weavernorth.taide.util.TaiDeOkHttpUtils;
import org.apache.commons.codec.binary.Base64;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * 发票归档并变更发票状态-分摊多发票
 */
public class InvoiceToEndFentanDuofp extends BaseAction {

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

        this.writeLog("发票归档并变更发票状态-分摊多发票 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String mxbName = recordSet.getString("mxbName"); // 发票所在明细表名称(_dt1)
            String mainId = recordSet.getString("id");
            String sappzh = Util.null2String(recordSet.getString("sappzh")).trim();
            String workCode = recordSet.getString("workcode");

            this.writeLog("发票所在明细表名称(_dt1)============ " + mxbName);
            this.writeLog("凭证号============ " + sappzh);
            this.writeLog("workCode============ " + workCode);

            // 发票uuid - 是否抵扣
            Map<String, String> uuidSfdkMap = new HashMap<String, String>();

            // 查询明细表
            recordSet.executeQuery("select * from " + tableName + mxbName + " where mainid = " + mainId);
            List<String> bhList = new ArrayList<String>();
            while (recordSet.next()) {
                uuidSfdkMap.put(recordSet.getString("uuid"), recordSet.getString("ISDEDUCTIBLE"));
                if (!"".equals(recordSet.getString(fpName))) {
                    bhList.add(recordSet.getString(fpName));
                }
            }
            this.writeLog("所有发票编号： " + JSONObject.toJSONString(bhList));

            // 变更发票状态
            String getInvoiceUrl = ConfigInfo.InvoiceUrl.getValue();
            String appSecId = ConfigInfo.appSecId.getValue();
            String appSecKey = ConfigInfo.appSecKey.getValue();
            String appId = ConfigInfo.appId.getValue();
            this.writeLog("流程归档更新发票信息开始========================");

            String currentDate = TimeUtil.getCurrentDateString().replace("-", "");
            JSONArray dataArrayObject = new JSONArray();
            for (String invoice : bhList) {
                JSONObject dataObject = new JSONObject(true);
                dataObject.put("uuid", invoice);
                dataObject.put("certificateNumber", sappzh); // 凭证号
                dataObject.put("reimburseSerialNo", requestId); // 流程编号
                dataObject.put("reimburseSource", "2"); // 单据来源
                dataObject.put("reimburseState", "3"); // 0：未报销 2：报销中 3：已报销
                dataObject.put("userId", workCode);

                dataObject.put("isDeductible", ConnUtil.sfdkChange(Util.null2String(uuidSfdkMap.get(invoice)))); //  是否可抵扣
                dataObject.put("reimburseDate", currentDate);
                dataArrayObject.add(dataObject);
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
            this.writeLog("归档发票接口返回： " + returnInvoice);

            JSONObject returnObject = JSONObject.parseObject(returnInvoice);
            JSONObject returnInfo = returnObject.getJSONObject("returnInfo");
            if (!"0000".equals(returnInfo.getString("returnCode"))) {
                this.writeLog("流程归档变更发票状态： " + returnInvoice);
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("流程归档变更发票状态 异常： " + returnInvoice);
                return "0";
            }

            // 变更发票状态 ( 0：未报销 2：报销中 3：已报销)
            for (String invoice : bhList) {
                recordSet.executeUpdate("update uf_fpinfo set reimburseState = 3 where uuid = '" + invoice + "'");
            }

            this.writeLog("发票归档并变更发票状态-分摊多发票 End ===============");
        } catch (Exception e) {
            this.writeLog("流程归档变更发票状态-分摊 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("流程归档变更发票状态 异常： " + e);
            return "0";
        }

        return "1";
    }
}
