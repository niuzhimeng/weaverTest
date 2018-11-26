package com.weavernorth;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.interfaces.schedule.BaseCronJob;
import weaver.workflow.webservices.WorkflowRequestInfo;
import weaver.workflow.webservices.WorkflowServiceImpl;

public class MySchedule0411 extends BaseCronJob {

    private BaseBean baseBean = new BaseBean();
    private RecordSet recordSet = new RecordSet();

    @Override
    public void execute() {
        new BaseBean().writeLog("流程归档定时执行=========================" + TimeUtil.getCurrentTimeString());
        recordSet.execute("select * from formtable_main_41 f  left join workflow_requestbase w on f.requestId = w.requestId  where f.sfgd = 1 and w.currentnodetype != 3");
        String requestid = "";
        int createrid = 0;
        if (recordSet.next()) {
            requestid = recordSet.getString("requestId");
            createrid = recordSet.getInt("creater");
        }

        baseBean.writeLog("流程创建人id================： " + createrid);
        WorkflowServiceImpl workflowService = new WorkflowServiceImpl();
        WorkflowRequestInfo workflowRequestInfo = new WorkflowRequestInfo();
        String result = workflowService.submitWorkflowRequest(workflowRequestInfo, Integer.parseInt(requestid.trim()), createrid, "submit", "ok");
        baseBean.writeLog("退回结果================： " + result);

    }
}
