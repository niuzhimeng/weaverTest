package com.weavernorth.zhongsha.crmsap.action;

import com.sap.conn.jco.*;
import com.weavernorth.zhongsha.crmsap.ZhsPoolThree;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 准入流程
 */
public class ZhunRuAction extends BaseAction {

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

        this.writeLog("准入流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            JCoDestination jCoDestination = ZhsPoolThree.getJCoDestination();
            jCoDestination.ping();
            this.writeLog("ping 通=====");
            JCoFunction function = jCoDestination.getRepository().getFunction("ZFM_CRM_OA_INF_VENDOR_CREATE");
            JCoStructure insertStructure = function.getImportParameterList().getStructure("IS_DATA");

            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();

            insertStructure.setValue("LIFNR", recordSet.getString("sapdm")); // SAP代码
            insertStructure.setValue("KTOKK", recordSet.getString("zhz")); // 账户组
            insertStructure.setValue("LAND1", recordSet.getString("gj")); // 国家
            insertStructure.setValue("NAME1", recordSet.getString("mc")); // 供应商名称
            insertStructure.setValue("LIFNR", recordSet.getString("sapdm")); // 供应商帐号

            insertStructure.setValue("STCD1", recordSet.getString("sh")); // 税号
            insertStructure.setValue("STRAS", recordSet.getString("dz")); // 地址
            insertStructure.setValue("PSTLZ", recordSet.getString("yb")); // 邮编
            insertStructure.setValue("REGIO", recordSet.getString("dq")); // 地区
            insertStructure.setValue("TELF1", recordSet.getString("gsdh")); // 公司电话

            insertStructure.setValue("TELF2", recordSet.getString("cwrysj")); // 财务人员电话
            insertStructure.setValue("TELFX", recordSet.getString("gscz")); // 公司传真
            insertStructure.setValue("SMTP_ADDR", recordSet.getString("gsemail")); // 公司email
            insertStructure.setValue("BANKL", recordSet.getString("yhdm")); // 银行代码
            insertStructure.setValue("BANKN", recordSet.getString("zhh")); // 账户号

            insertStructure.setValue("KOINH", recordSet.getString("mc")); // 供应商名称
            insertStructure.setValue("AKONT", recordSet.getString("tykm")); // 统驭科目
            insertStructure.setValue("FDGRV", recordSet.getString("xjglz")); // 现金管理组
            insertStructure.setValue("MINDK", recordSet.getString("ssbz")); // 少数标志
            insertStructure.setValue("REPRF", recordSet.getString("jcscfp")); // 检查双重发票

            insertStructure.setValue("ZWELS", recordSet.getString("fkfs")); // 付款方式
            insertStructure.setValue("ZTERM", "T001"); // 付款条件

            this.writeLog("银行代码: " + recordSet.getString("yhdm") + " 账户号: " + recordSet.getString("zhh"));
            this.writeLog("供应商名称: " + recordSet.getString("mc") + " SAP代码: " + recordSet.getString("sapdm"));
            // 表入参
            JCoTable itMatkl = function.getTableParameterList().getTable("IT_MATKL");
            String wlz = recordSet.getString("xgwlz");// 物料组
            this.writeLog("物料组===== " + wlz);
            String[] wlzs = wlz.split(",");
            int wlxLen = wlzs.length;
            for (int i = 0; i < wlxLen; i++) {
                itMatkl.appendRow();
                itMatkl.setRow(i);
                itMatkl.setValue("MATKL", wlzs[i]);
            }
            this.writeLog("推送结构数据： " + insertStructure.toString());
            this.writeLog("推送表数据： " + itMatkl.toString());
            // 调用sap接口
            function.execute(jCoDestination);
            this.writeLog("调用接口结束===========");

            JCoParameterList exportParameterList = function.getExportParameterList();
            String type = exportParameterList.getString("EV_ZSTATE"); // 返回状态
            String EV_ZMSEG = exportParameterList.getString("EV_ZMSEG"); // 消息文本
            String EV_LIFNR = exportParameterList.getString("EV_LIFNR"); // 供应商编号
            this.writeLog("sap返回状态： " + type);
            this.writeLog("sap返回消息文本： " + EV_ZMSEG);
            this.writeLog("sap返回供应商编号： " + EV_LIFNR);
            if (!"S".equalsIgnoreCase(type)) {
                requestInfo.getRequestManager().setMessageid("120001");
                requestInfo.getRequestManager().setMessagecontent("Sap 接口异常： " + EV_ZMSEG);
                return "0";
            }

            this.writeLog("准入流程 End ===============");
        } catch (Exception e) {
            this.writeLog("准入流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("120002");
            requestInfo.getRequestManager().setMessagecontent("准入流程 异常： " + e);
            return "0";
        }

        return "1";
    }
}
