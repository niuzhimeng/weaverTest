package com.weavernorth;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 流程标题更改测试
 */
public class RequestNameTestAction extends BaseAction {

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
        requestInfo.getRequestManager().setRemark("setRemark");
        requestInfo.getRequestManager().setRequestname("setRequestname");
        this.writeLog("流程标题更改测试 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {


            this.writeLog("流程标题更改测试 End ===============");
        } catch (Exception e) {
            this.writeLog("流程标题更改测试 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("流程标题更改测试 异常： " + e);
            return "0";
        }

        return "1";
    }
}
