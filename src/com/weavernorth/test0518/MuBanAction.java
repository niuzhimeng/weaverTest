package com.weavernorth.test0518;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.UUID;

/**
 * 功能测试0518
 */
public class MuBanAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        RecordSet recordSet_dt2 = new RecordSet();
        RecordSet updateSet = new RecordSet();

        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("功能测试0518 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            String mainId = recordSet.getString("id");

            recordSet.executeQuery("select * from " + tableName + "_dt1 where mainid = " + mainId);
            recordSet_dt2.executeQuery("select * from " + tableName + "_dt2 where mainid = " + mainId);

            int counts = recordSet.getCounts();
            int counts1 = recordSet_dt2.getCounts();
            for (int i = 0; i < counts; i++) {
                recordSet.next();
                recordSet_dt2.next();
                String uuid = UUID.randomUUID().toString();
                updateSet.executeUpdate("update " + tableName + "_dt1 set glj = '" + uuid + "' where id = " + recordSet.getString("id"));
                updateSet.executeUpdate("update " + tableName + "_dt2 set glj = '" + uuid + "' where id = " + recordSet_dt2.getString("id"));
            }

            this.writeLog("功能测试0518 End ===============");
        } catch (Exception e) {
            this.writeLog("功能测试0518 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("功能测试0518 异常： " + e);
            return "0";
        }

        return "1";
    }
}
