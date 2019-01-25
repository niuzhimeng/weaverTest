package com.weavernorth.taide.autoSubmit;


import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestActionTIDI extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        int billid = requestInfo.getRequestManager().getBillid();
        String requestId = requestInfo.getRequestid();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        new BaseBean().writeLog("泰德节点后执行============" + TimeUtil.getCurrentTimeString() + " requestId: " + requestId +
                ", tableName: " + tableName + ", billid: " + billid);

        return "1";
    }
}
