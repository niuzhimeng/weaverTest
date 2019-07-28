package com.weavernorth.workflow;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 流程表单转pdf
 */
public class WorkflowToPdf extends BaseAction {

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

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

        try {
            WorkflowToPdfThread workflowToPdfThread = new WorkflowToPdfThread();
            workflowToPdfThread.setRequestId(requestId);
            workflowToPdfThread.setTableName(tableName);
            executorService.execute(workflowToPdfThread);
        } catch (Exception e) {
            this.writeLog("流程表单转pdf 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("流程表单转pdf 异常： " + e);
            return "0";
        }
        return "1";
    }


}
















