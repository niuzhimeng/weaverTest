package com.weavernorth.taide.kaoQin.action.check;

import weaver.conn.RecordSet;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 考勤补贴流程提交校验
 */
public class CheckKqbt extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("考勤补贴流程提交校验 start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName);
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeQuery("select * from uf_loginInfo where dataType = 4");
            String jzDate = ""; // 标准日
            if (recordSet.next()) {
                jzDate = recordSet.getString("loginid");
                if (jzDate.length() < 2) {
                    jzDate = "0" + jzDate;
                }
            }

            int bzDateJian1 = Integer.parseInt(jzDate) - 1; // 标准日期减一

            String mainSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
            recordSet.executeQuery(mainSql);
            String btrq = ""; // 开始日期
            if (recordSet.next()) {
                btrq = recordSet.getString("AB401_2");
            }
            String currentDate = TimeUtil.getCurrentDateString().substring(8, 10); // 当前日
            String baseDate = TimeUtil.getCurrentDateString().substring(0, 8); // 2019-01-
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date bzDate = format.parse(baseDate + bzDateJian1);
            Date ksDate = format.parse(btrq); // 开始日期
            if (Integer.parseInt(currentDate) >= Integer.parseInt(jzDate)) {
                // 当前日期 >= 标准日期 ，开始日期必须 >= 本月 （标准日期 - 1）号
                this.writeLog("开始日期： " + format.format(ksDate));
                this.writeLog("标准日期 -1： " + format.format(bzDate));

                if (ksDate.before(bzDate)) {
                    requestInfo.getRequestManager().setMessageid("110000");
                    requestInfo.getRequestManager().setMessagecontent("补贴日期需大于等于当月 " + bzDateJian1 + " 日");
                    return "0";
                }
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(bzDate);
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);// 往前调一个月
                if (ksDate.before(calendar.getTime())) {
                    requestInfo.getRequestManager().setMessageid("110000");
                    requestInfo.getRequestManager().setMessagecontent("补贴日期需大于等于上月 " + bzDateJian1 + " 日");
                    return "0";
                }
            }
        } catch (Exception e) {
            this.writeLog("考勤补贴流程提交校验 异常： " + e);
        }
        return "1";
    }
}
