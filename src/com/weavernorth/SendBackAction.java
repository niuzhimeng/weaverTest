package com.weavernorth;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 判断下一节点是创建节点
 */
public class SendBackAction extends BaseAction {

    private String name;

    @Override
    public String execute(RequestInfo requestInfo) {
        int formid = requestInfo.getRequestManager().getFormid();
        writeLog("============formid========== " + formid);

        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formid + "'"); // 均可获取表名
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("tableName： " + tableName);

        String billTableName = requestInfo.getRequestManager().getBillTableName(); // 批量提交无法获取表名
        this.writeLog("老方法获取表名： " + billTableName);

        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
