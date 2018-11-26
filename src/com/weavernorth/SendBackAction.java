package com.weavernorth;

import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 判断下一节点是创建节点
 */
public class SendBackAction extends BaseAction {

    private String name;

    @Override
    public String execute(RequestInfo requestInfo) {
        writeLog("============获取配置参数========== " + name);
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
