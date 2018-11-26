package com.weavernorth.gaoji.sap.action;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.workflow.webservices.WorkflowRequestInfo;
import weaver.workflow.webservices.WorkflowServiceImpl;

public class BlockingDeque implements Runnable {

    private RecordSet recordSet = new RecordSet();
    private BaseBean baseBean = new BaseBean();
    private WorkflowServiceImpl workflowService = new WorkflowServiceImpl();
    private WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();

    private String tableName;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void run() {
        try {
            baseBean.writeLog("对公付款 - 归档线程执行===============");
            Thread.sleep(3000);
            recordSet.execute("select * from " + tableName + " f left join workflow_requestbase w on f.requestId = w.requestId  where f.cxgd = 0 and w.currentnodetype = 0");
            String requestid;
            int createrid;
            while (recordSet.next()) {
                requestid = recordSet.getString("requestId");
                createrid = recordSet.getInt("creater");

                baseBean.writeLog("流程创建人id================： " + createrid);

                String result = workflowService.submitWorkflowRequest(workflowRequestInfo, Integer.parseInt(requestid.trim()), createrid, "submit", "ok");
                baseBean.writeLog("归档结果================： " + result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
