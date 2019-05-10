package com.weavernorth.taide.kaoQin.action.check;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 加班流程提交校验
 */
public class CheckJb0510 extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operatetype = requestInfo.getRequestManager().getSrc();
        String tableName = requestInfo.getRequestManager().getBillTableName();
        this.writeLog("加班流程提交校验 start requestid --- " + requestId + "  operatetype --- " + operatetype + "   fromTable --- " + tableName);
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
            // 加班日期
            String jbrq = "";
            // 打卡时间
            String dksj = "";
            // 结束时间
            String jssj = "";
            // 开始时间
            String kssj = "";
            if (recordSet.next()) {
                jbrq = recordSet.getString("jbrq");
                dksj = recordSet.getString("dksj");
                jssj = recordSet.getString("jssj");
                kssj = recordSet.getString("kssj");
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            // 加班时间  - 结束时间判断
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Date dksjDate = dateFormat.parse(dksj);
            Date jssjDate = dateFormat.parse(jssj);
            Date kssjDate = dateFormat.parse(kssj);
            if (dksjDate.before(jssjDate)) {
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("【打卡时间】需大于【结束时间】");
                return "0";
            }
            if (jssjDate.before(kssjDate)) {
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("【结束时间】需大于【开始时间】");
                return "0";
            }

            // 标准日期 Date对象
            Date bzDate = format.parse(bzDateStr);
            // 加班日期 Date对象
            Date ksDate = format.parse(jbrq);

            if (ksDate.before(bzDate)) {
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("加班日期需大于等于" + bzDateStr);
                return "0";
            }
        } catch (Exception e) {
            this.writeLog("加班流程提交校验 异常： " + e);
        }
        return "1";
    }
}
