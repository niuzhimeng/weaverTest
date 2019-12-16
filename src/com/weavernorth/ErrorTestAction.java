package com.weavernorth;

import com.alibaba.fastjson.JSONObject;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 异常测试
 */
public class ErrorTestAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("异常测试 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from hrmresource");
            recordSet.next();
            String[] columnName = recordSet.getColumnName();
            int[] columnType = recordSet.getColumnType();
            int counts = recordSet.getCounts();
            int colCounts = recordSet.getColCounts();
            this.writeLog("columnName: " + JSONObject.toJSONString(columnName));
            this.writeLog("columnType: " + JSONObject.toJSONString(columnType));
            this.writeLog("counts: " + counts);
            this.writeLog("colCounts: " + colCounts);

            this.writeLog("异常测试 End ===============");
        } catch (Exception e) {
            this.writeLog("异常测试 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("异常测试 异常： " + e);
            return "0";
        }

        return "1";
    }
}
