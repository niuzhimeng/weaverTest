package com.weavernorth.taide.autoSubmit;

import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.workflow.webservices.WorkflowRequestInfo;
import weaver.workflow.webservices.WorkflowServiceImpl;

public class BlockingDeque implements Runnable {

    private BaseBean baseBean = new BaseBean();
    private WorkflowServiceImpl workflowService = new WorkflowServiceImpl();
    private WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();

    private String tableName;
    private String requestId;

    public BlockingDeque(String tableName, String requestId) {
        this.tableName = tableName;
        this.requestId = requestId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            baseBean.writeLog("归档线程执行=============== " + TimeUtil.getCurrentTimeString());
            String result = workflowService.submitWorkflowRequest(workflowRequestInfo, Integer.parseInt(requestId), 34773, "submit", "ok");
            baseBean.writeLog("归档结果================： " + result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
