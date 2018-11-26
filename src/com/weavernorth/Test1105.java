package com.weavernorth;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class Test1105 extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestid = requestInfo.getRequestid();
        RecordSet recordSet = new RecordSet();
        recordSet.executeSql("update formtable_main_31 set xm = 3 where requestId = '" + requestid + "'");


        return "1";
    }
}
