package com.weavernorth.zgzt.action;

import com.weavernorth.zgzt.ProcessRequest;
import com.weavernorth.zgzt.UserOperation;
import com.weavernorth.zgzt.UserOperationServiceLocator;
import weaver.hrm.User;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class SendInfoAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestid = requestInfo.getRequestid();
        User user = requestInfo.getRequestManager().getUser();
        String src = requestInfo.getRequestManager().getSrc();
        String userName = user.getLastname();

        ProcessRequest processRequest = new ProcessRequest();
        processRequest.setName(userName);
        processRequest.setProcessIdStr(requestid);
        processRequest.setStatusStr(src);

        UserOperationServiceLocator locator = new UserOperationServiceLocator();
        try {
            UserOperation userOperationSoap11 = locator.getUserOperationSoap11();
            userOperationSoap11.process(processRequest);
            this.writeLog("推送数据成功===================: " + processRequest.toString());
        } catch (Exception e) {
            this.writeLog(e);
        }
        return "1";
    }
}
