package com.weavernorth.jrzx;

import com.weaver.general.TimeUtil;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 节点后插入当前时间
 */
public class InsertDateAction extends BaseAction {

    private String zdm = "";

    @Override
    public String execute(RequestInfo requestInfo) {
        try {
            String requestId = requestInfo.getRequestid();
            RecordSet recordSet = new RecordSet();
            String tableSql = "SELECT tablename FROM workflow_bill WHERE id = (SELECT formid FROM workflow_base WHERE id = (SELECT workflowid FROM workflow_requestbase WHERE requestid = '" + requestId + "'))";

            String tableName = "";
            recordSet.execute(tableSql);
            if (recordSet.next()) {
                tableName = recordSet.getString("tablename");
            }
            String currentTime = TimeUtil.getCurrentDateString();

            this.writeLog("----------插入当前审批时间执行-------------" + currentTime + ", requestId:" + requestId + ",  tablename: " + tableName);

            String updateSql = "update " + tableName + " set " + zdm + " = '" + currentTime + "' where requestid = '" + requestId + "'";
            this.writeLog("插入审批时间字段 SQL： " + updateSql);
            recordSet.executeQuery(updateSql);
        } catch (Exception e) {
            this.writeLog("插入审批时间字段 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("插入审批时间接口异常，请联系管理员。");
            return "0";
        }
        return "1";
    }

    public String getZdm() {
        return zdm;
    }

    public void setZdm(String zdm) {
        this.zdm = zdm;
    }
}
