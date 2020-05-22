package com.weavernorth.zhongsha.crmsap.action;

import com.alibaba.fastjson.JSONObject;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.weaver.general.Util;
import com.weavernorth.zhongsha.crmsap.ZhsPoolThree;
import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.HashMap;
import java.util.Map;

/**
 * 询比价流程-物资类
 */
public class XunBiJiaAction extends BaseAction {

    private RecordSet connSet = new RecordSet();
    /**
     * 下拉框value - 供应商所在明细表
     */
    private static Map<String, String> mxbNameMap = new HashMap<String, String>();

    static {
        mxbNameMap.put("1", "1");
        mxbNameMap.put("2", "2");
        mxbNameMap.put("3", "3");
        mxbNameMap.put("4", "4");
        mxbNameMap.put("5", "5");

        mxbNameMap.put("6", "8");
        mxbNameMap.put("7", "9");
        mxbNameMap.put("8", "10");
        mxbNameMap.put("9", "11");
        mxbNameMap.put("10", "12");
    }

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("询比价流程-物资类 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            String currentDateString = TimeUtil.getCurrentDateString();
            JCoDestination jCoDestination = ZhsPoolThree.getJCoDestination();
            jCoDestination.ping();
            this.writeLog("ping 通=====");
            JCoFunction function = jCoDestination.getRepository().getFunction("ZFM_CRM_OA_INF_PO_CREATE");
            JCoTable itOutput = function.getTableParameterList().getTable("IT_INPUT");

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            this.writeLog("主表查询结束===");
            String mainId = recordSet.getString("id");
            String scsapcgdd = recordSet.getString("scsapcgdd"); // 生成SAP采购订单
            if (!"0".equals(scsapcgdd)) {
                this.writeLog("不推送sap，接口跳过。");
                this.writeLog("询比价流程-物资类 End ===============");
                return "1";
            }
            String hth = getSysByFiled("htbh", "uf_httz", "id", recordSet.getString("hth"));
            String cgy = getSysByFiled("loginid", "hrmresource", "id", recordSet.getString("cgy"));
            this.writeLog("合同号==========" + hth);
            this.writeLog("采购员==========" + cgy);

            String gysmc_A = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gysamc")); // 供应商名称A
            String gysmc_B = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gysbmc"));
            String gysmc_C = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gyscmc"));
            String gysmc_D = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gysdmc"));
            String gysmc_E = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gysemc"));

            String gysmc_F = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gysfmc"));
            String gysmc_G = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gysgmc"));
            String gysmc_H = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gyshmc"));
            String gysmc_I = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gysimc"));
            String gysmc_J = getSysByFiled("sapdm", "uf_crm_gysxx", "id", recordSet.getString("gysjmc"));
            String[] gysNames = {gysmc_A, gysmc_B, gysmc_C, gysmc_D, gysmc_E,
                    gysmc_F, gysmc_G, gysmc_H, gysmc_I, gysmc_J};
            this.writeLog("供应商名称： " + JSONObject.toJSONString(gysNames));

            // 查询中标供应商
            recordSet.executeQuery("select * from " + tableName + "_dt6 where mainid = '" + mainId + "'");
            RecordSet fysSet = new RecordSet();
            int j = 0;
            while (recordSet.next()) {
                String zfzb = recordSet.getString("zfzb"); // 是否中标
                this.writeLog("是否中标： " + zfzb);
                if ("0".equals(zfzb)) {
                    continue;
                }
                String zbgys = recordSet.getString("zbgys"); // 中标供应商
                String zbgysSql = "select * from " + tableName + "_dt" + mxbNameMap.get(zbgys) + " where mainid = '" + mainId + "'";

                String gysName = gysNames[Util.getIntValue(zbgys) - 1];
                this.writeLog("中标供应商名称================ " + gysName);
                this.writeLog("查询中标供应商sql： " + zbgysSql);
                fysSet.executeQuery(zbgysSql);
                while (fysSet.next()) {
                    itOutput.appendRow();
                    itOutput.setRow(j);
                    itOutput.setValue("BANFN", fysSet.getString("cgsqh")); // 采购申请编号
                    itOutput.setValue("BNFPO", fysSet.getString("hxm")); // 行项目
                    itOutput.setValue("LIFNR", gysName); // 供应商名称
                    itOutput.setValue("MATNR", fysSet.getString("wlbm")); // 物料编码
                    itOutput.setValue("ERDAT", currentDateString); // 当前日期

                    itOutput.setValue("MEINS", fysSet.getString("dw")); // 单位
                    itOutput.setValue("MENGE", fysSet.getString("sl")); // 数量
                    itOutput.setValue("LFDAT", fysSet.getString("jhrq")); // 交货时间
                    itOutput.setValue("ERNAM", cgy); // 采购员
                    itOutput.setValue("INCO2", hth); // 合同号

                    itOutput.setValue("NETWR", fysSet.getString("xj")); // 小计
                    itOutput.setValue("BATXT", fysSet.getString("lx")); // 类型

                    j++;
                }
            }

            this.writeLog("推送表数据共计" + j + " 行");
            this.writeLog("推送表数据： " + itOutput.toString());
            // 调用sap接口
            function.execute(jCoDestination);
            this.writeLog("调用接口结束===========");

            RecordSet updateSet = new RecordSet();
            JCoParameterList exportParameterList = function.getTableParameterList();
            JCoTable returnTable = exportParameterList.getTable("IT_OUTPUT");
            int numRows = returnTable.getNumRows();
            for (int i = 0; i < numRows; i++) {
                returnTable.setRow(i);
                String cgsqh = returnTable.getString("BANFN"); // 采购申请号
                String id = returnTable.getString("BNFPO"); // 明细行id
                String cgddbh = returnTable.getString("EBELN");// 采购订单编号
                String zt = returnTable.getString("ZSTATE");// 状态
                String bz = returnTable.getString("ZMSG"); // 备注
                if (!"S".equalsIgnoreCase(zt)) {
                    requestInfo.getRequestManager().setMessageid("120002");
                    requestInfo.getRequestManager().setMessagecontent("询比价流程-物资类 异常，sap返回信息： " + zt + ", " + bz);
                    return "0";
                }
                this.writeLog("返回结果=======状态:" + zt + ", 备注：" + bz);
                this.writeLog("采购申请号: " + cgsqh + ", 明细行id: " + id + ", 采购订单编号: " + cgddbh);
                updateSet.executeUpdate("insert into " + tableName + "_dt7(mainid, cgsqh, hxm, cgddbh, zt, bz) values(?,?,?,?,?, ?)",
                        mainId, cgsqh, cgddbh, zt, bz, id);
            }

            this.writeLog("询比价流程-物资类 End ===============");
        } catch (Exception e) {
            this.writeLog("询比价流程-物资类 异常： " + e);
            requestInfo.getRequestManager().setMessageid("120002");
            requestInfo.getRequestManager().setMessagecontent("询比价流程-物资类 异常： " + e);
            return "0";
        }

        return "1";
    }

    /**
     * 根据某一字段查另一个字段
     *
     * @param resultField 查询的字段名
     * @param tableName   查询表名
     * @param selField    条件字段名
     */
    private String getSysByFiled(String resultField, String tableName, String whereId, String selField) {
        String returnStr = "";
        connSet.executeQuery("select " + resultField + " from " + tableName + " where " + whereId + " = '" + selField + "'");
        if (connSet.next()) {
            returnStr = connSet.getString(resultField);
        }
        return returnStr;
    }
}
