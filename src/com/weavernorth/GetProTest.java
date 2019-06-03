package com.weavernorth;

import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class GetProTest extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String name = this.getPropValue("test0529", "name");
        String age = this.getPropValue("test0529", "age");

        this.writeLog("姓名========= " + name);
        this.writeLog("age========= " + age);
        return "1";
    }
}
