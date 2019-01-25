package com.weavernorth.taide.autoSubmit;


import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AutoSubmit extends BaseAction {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public String execute(RequestInfo requestInfo) {

        String requestId = requestInfo.getRequestid();
        String tableName = requestInfo.getRequestManager().getBillTableName();

        //执行归档操作
        BlockingDeque blockingDeque = new BlockingDeque(tableName, requestId);

        executorService.execute(blockingDeque);

        return "1";
    }
}
