package com.weavernorth.taide.kaoQin.action.check;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 考勤补贴流程提交校验
 */
public class CheckQj extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("请假流程提交校验 start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName);
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = 4");
            // 标准日期 yyyy-MM-dd
            String bzDateStr = "";
            if (recordSet.next()) {
                bzDateStr = recordSet.getString("loginid");
            }

            String mainSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
            recordSet.executeQuery(mainSql);
            // 开始日期
            String ksrq = "";
            // 结束日期
            String jsrq = "";
            if (recordSet.next()) {
                ksrq = recordSet.getString("ksrq");
                jsrq = recordSet.getString("jsrq");
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            // 标准日期 Date对象
            Date bzDate = format.parse(bzDateStr);
            // 开始日期 Date对象
            Date ksDate = format.parse(ksrq);
            // 结束日期 Date对象
            Date jsDate = format.parse(jsrq);
            if (ksDate.before(bzDate)) {
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("请假开始日期需大于等于" + bzDateStr);
                return "0";

            } else if (jsDate.before(bzDate)) {
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("请假结束日期需大于等于" + bzDateStr);
                return "0";
            }
        } catch (Exception e) {
            this.writeLog("请假流程提交校验 异常： " + e);
        }
        return "1";
    }
}
