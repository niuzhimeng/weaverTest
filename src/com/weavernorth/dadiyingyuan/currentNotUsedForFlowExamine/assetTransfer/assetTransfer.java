package com.weavernorth.dadiyingyuan.currentNotUsedForFlowExamine.assetTransfer;

import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class assetTransfer extends BaseAction {
    @Override
    public String execute(RequestInfo request) {
        //获取流程表单requestId
        String requestid = request.getRequestid();
        String billTableName = request.getRequestManager().getBillTableName();
        String src = request.getRequestManager().getSrc();
        if("submit".equals(src)){
            //调拨的逻辑判断
        }
        return SUCCESS;
    }
}
