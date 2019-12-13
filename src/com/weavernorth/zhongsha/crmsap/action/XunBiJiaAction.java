package com.weavernorth.zhongsha.crmsap.action;

import com.sap.conn.jco.*;
import com.weavernorth.zhongsha.crmsap.ZhsPoolThree;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 询比价流程-物资类
 */
public class XunBiJiaAction extends BaseAction {

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
            JCoDestination jCoDestination = ZhsPoolThree.getJCoDestination();
            jCoDestination.ping();
            this.writeLog("ping 通=====");
            JCoFunction function = jCoDestination.getRepository().getFunction("ZFM_CRM_OA_INF_PO_CREATE");
            JCoTable itOutput = function.getImportParameterList().getTable("IT_OUTPUT ");

            // 查询主表
            recordSet.executeQuery("select id from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String mainId = recordSet.getString("id");
            recordSet.executeQuery("select * from " + tableName + "_dt1 where mainid = '" + mainId + "'");
            recordSet.next();

            itOutput.setValue("BANFN", recordSet.getString("cgsqh")); // 采购申请编号
            itOutput.setValue("BNFPO", recordSet.getString("id")); // 明细行id
            itOutput.setValue("LIFNR", recordSet.getString("sapdm")); // 供应商名称
            itOutput.setValue("MATNR", recordSet.getString("wzbm")); // 物资编码
            itOutput.setValue("TXZ01", recordSet.getString("wlcms")); // 物料长描述

            itOutput.setValue("MEINS", recordSet.getString("dw")); // 单位
            itOutput.setValue("MENGE", recordSet.getString("sl")); // 数量
            itOutput.setValue("LFDAT", recordSet.getString("jhsj")); // 交货时间
            itOutput.setValue("ERNAM", recordSet.getString("cgy")); // 采购员
            itOutput.setValue("INCO2", recordSet.getString("hth")); // 合同号

            itOutput.setValue("NETWR", recordSet.getString("zbje")); // 中标金额

            this.writeLog("推送表数据： " + itOutput.toString());
            // 调用sap接口
            function.execute(jCoDestination);
            this.writeLog("调用接口结束===========");

            RecordSet updateSet = new RecordSet();
            JCoParameterList exportParameterList = function.getExportParameterList();
            JCoTable returnTable = exportParameterList.getTable("IT_OUTPUT");
            int numRows = returnTable.getNumRows();
            for (int i = 0; i < numRows; i++) {
                returnTable.setRow(i);
                String cgsqh = returnTable.getString("BANFN"); // 采购申请号
                String id = returnTable.getString("BNFPO"); // 明细行id
                String cgddbh = returnTable.getString("EBELN");// 采购订单编号
                String zt = returnTable.getString("ZSTATE");// 状态
                String bz = returnTable.getString("ZMSG"); // 备注
                updateSet.executeUpdate("update " + tableName + "_dt1 set cgsqh = ?, cgddbh = ?, zt = ?, bz = ? where id = ?",
                        cgsqh, cgddbh, zt, bz, id);
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
}
