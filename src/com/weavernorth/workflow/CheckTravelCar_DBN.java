package com.weavernorth.workflow;

import weaver.conn.RecordSet;
import weaver.formmode.interfaces.action.BaseAction;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;

public class CheckTravelCar_DBN extends BaseAction {

    @Override
    public String execute(RequestInfo request) {
        this.writeLog("DBN差旅及用车申请表 节点后验证action Start======" + TimeUtil.getCurrentTimeString());
        RecordSet rsselecttabel = new RecordSet();
        RecordSet rsselect = new RecordSet();
        RecordSet rscheck = new RecordSet();
        // 是否允许提交     yes:允许提交    no:不允许提交
        String strFlag = "yes";
        //差旅人
        String strHrmId = "";
        //差旅开始日期时间
        String strStartTime = "";
        //差旅结束日期时间
        String strEndTime = "";
        // 流程请求id
        String requestid = request.getRequestid();
        // 流程id
        String workflowid = request.getWorkflowid();
        // 流程表名
        String strTablename = "";
        // 获取表名
        String strSearchFormid = "select tablename from workflow_bill where id=(select formid from workflow_base where id=" + workflowid + ")";
        rsselecttabel.executeSql(strSearchFormid);
        if (rsselecttabel.next()) {
            strTablename = Util.null2String(rsselecttabel.getString("tablename"));
        }

        try {
            //查询本条流程信息
            String selectSql = "select * from " + strTablename + "  where requestid=" + requestid;
            this.writeLog("主表查询sql： " + selectSql);
            rsselect.execute(selectSql);
            if (rsselect.first()) {
                // 员工姓名/员工号
                strHrmId = rsselect.getString("Name_Number");
                // 差旅日期 开始时间
                strStartTime = rsselect.getString("Travel_Date") + " " + rsselect.getString("cnvb");
                // 差旅结束日期
                strEndTime = rsselect.getString("Travel_End_Date") + " " + rsselect.getString("end_time ");
                //查询开始日期时间是否重复(已有时间是否包含本条流程时间)
                String strCheckStartSql = "select * from " + strTablename + " where requestid<>" + requestid + " and Name_Number=" + strHrmId
                        + " and '" + strStartTime + "' between  Travel_Date+' '+cnvb and Travel_End_Date+' '+ end_time ";

                this.writeLog("======查询开始日期时间是否重复(已有时间是否包含本条流程时间)======" + strCheckStartSql);
                rscheck.execute(strCheckStartSql);
                if (rscheck.first()) {
                    strFlag = "no";
                }

                //查询结束日期时间是否重复(已有时间是否包含本条流程时间)
                String strCheckEndSql = "select * from " + strTablename + " where requestid<>" + requestid + " and Name_Number=" + strHrmId
                        + " and '" + strEndTime + "' between  Travel_Date+' '+cnvb and Travel_End_Date+' '+ end_time ";

                this.writeLog("======查询结束日期时间是否重复(已有时间是否包含本条流程时间)======" + strCheckEndSql);
                rscheck.execute(strCheckEndSql);
                if (rscheck.first()) {
                    strFlag = "no";
                }
            }

            //查询开始日期时间是否重复(本条流程时间是否包含已有时间)
            String strCheckStartBackSql = "select * from " + strTablename + " where requestid<>" + requestid + " and Name_Number=" + strHrmId
                    + " and Travel_Date+' '+cnvb between '" + strStartTime + "'  and '" + strEndTime + "'";
            this.writeLog("======查询开始日期时间是否重复(本条流程时间是否包含已有时间)======" + strCheckStartBackSql);
            rscheck.execute(strCheckStartBackSql);
            if (rscheck.first()) {
                strFlag = "no";
            }
            //查询结束日期时间是否重复(本条流程时间是否包含已有时间)
            String strCheckEndBackSql = "select * from " + strTablename + " where requestid<>" + requestid + " and Name_Number=" + strHrmId
                    + " and Travel_End_Date+' '+ end_time  between '" + strStartTime + "'  and '" + strEndTime + "'";

            this.writeLog("======查询结束日期时间是否重复(本条流程时间是否包含已有时间)======" + strCheckEndBackSql);
            rscheck.execute(strCheckEndBackSql);
            if (rscheck.first()) {
                strFlag = "no";
            }
            this.writeLog("====是否允许提交======" + strFlag);
            //禁止提交流程
            if ("no".equals(strFlag)) {
                request.getRequestManager().setMessageid("12500");
                request.getRequestManager().setMessagecontent("差旅时间已经重复，请核对。");
                return "0";
            }
            this.writeLog("DBN差旅及用车申请表 节点后验证action End======" + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            this.writeLog("DBN差旅及用车申请表 error: " + e);
        }
        return SUCCESS;
    }
}
