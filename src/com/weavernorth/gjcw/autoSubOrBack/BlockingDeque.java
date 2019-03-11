package com.weavernorth.gjcw.autoSubOrBack;

import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.workflow.webservices.WorkflowRequestInfo;
import weaver.workflow.webservices.WorkflowServiceImpl;

public class BlockingDeque implements Runnable {

    private BaseBean baseBean = new BaseBean();
    private WorkflowServiceImpl workflowService = new WorkflowServiceImpl();
    private WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();

    // 当前操作者id
    private Integer currentOperate;
    private String requestId;
    // 执行动作
    private String operateAction;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            baseBean.writeLog("归档线程执行=============== " + TimeUtil.getCurrentTimeString() + " operateAction: " + operateAction);
            String result = workflowService.submitWorkflowRequest(workflowRequestInfo, Integer.parseInt(requestId), currentOperate, operateAction, "ok");
            baseBean.writeLog("归档结果================： " + result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer getCurrentOperate() {
        return currentOperate;
    }

    public void setCurrentOperate(Integer currentOperate) {
        this.currentOperate = currentOperate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOperateAction() {
        return operateAction;
    }

    public void setOperateAction(String operateAction) {
        this.operateAction = operateAction;
    }
}
