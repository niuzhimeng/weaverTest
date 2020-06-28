package com.weavernorth.hualianFlow;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 流程状态推送测试
 */
public class PushFlowState extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = " + formId);
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("流程状态推送测试 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = " + requestId);
            recordSet.next();
            // 申请人
            String sqr = recordSet.getString("sqr");



            this.writeLog("流程状态推送测试 End ===============");
        } catch (Exception e) {
            this.writeLog("流程状态推送测试 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("流程状态推送测试 异常： " + e);
            return "0";
        }

        return "1";
    }
}
