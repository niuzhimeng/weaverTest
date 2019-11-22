package com.weavernorth.zhongsha;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 申请编号重复加数字
 */
public class CodeCheckRepeat extends BaseAction {

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

        this.writeLog("申请编号重复加数字 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select Application_Number, bhcf from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            // 申请编号
            String applicationNumber = Util.null2String(recordSet.getString("Application_Number")).replaceAll("\\s*", "");
            String bhcf = Util.null2String(recordSet.getString("bhcf")).replaceAll("\\s*", "");
            this.writeLog("系统编号： " + applicationNumber + ", 代码生成编号： " + bhcf);
            if (!"".equals(applicationNumber)) {
                this.writeLog("已生成过编号，不做操作。");
                return "1";
            }

            // 查询历史编号
            recordSet.executeQuery("select bhcf from " + tableName + " where bhcf = '" + bhcf + "'");
            recordSet.next();
            int counts = recordSet.getCounts();
            String alterNum;
            if (counts <= 1) {
                alterNum = bhcf;
            } else {
                alterNum = bhcf + "-" + (counts - 1);
            }

            String updateSql = "update " + tableName + " set Application_Number = '" + alterNum + "' where requestid = '" + requestId + "'";
            this.writeLog("更新编号sql: " + updateSql);
            recordSet.executeUpdate(updateSql);

            this.writeLog("申请编号重复加数字 End ===============");
        } catch (Exception e) {
            this.writeLog("申请编号重复加数字 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("申请编号重复加数字 异常： " + e);
            return "0";
        }

        return "1";
    }
}
