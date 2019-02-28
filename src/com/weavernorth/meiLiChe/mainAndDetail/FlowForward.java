package com.weavernorth.meiLiChe.mainAndDetail;

import com.weaver.general.TimeUtil;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class FlowForward extends BaseAction {

    private String details; // 明细表后缀(逗号分隔) dt1,dt2,dt3
    private String mainZd; // 主表字段
    private String detailZd; // 明细表字段
    private String fuZhiZd; // 赋值表字段
    private String fuZhiVal; // 赋值表字段

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }
        this.writeLog("主表 - 明细表 字段对比赋值 执行 " + TimeUtil.getCurrentTimeString() + " requestid: " + requestId + " tableName: " + tableName);

        int mainZdVal = 0; // 主表字段值
        recordSet.executeQuery("select " + mainZd + " from " + tableName + " where requestid = '" + requestId + "'");
        if (recordSet.next()) {
            mainZdVal = recordSet.getInt(mainZd);
        }

        // 获取明细表名称数组
        String[] detailNames = details.split(",");
        RecordSet detailSet = new RecordSet();
        for (String detailName : detailNames) {
            detailSet.executeQuery("select d.* from " + tableName + "_" + detailName + " d left join " + tableName + " m on m.id = d.mainid where m.requestId = '" + requestId + "'");
            while (detailSet.next()) {
                if (mainZdVal != detailSet.getInt(detailZd)) {
                    return "1";
                }
            }
        }
        recordSet.execute("update " + tableName + " set " + fuZhiZd + " = " + fuZhiVal + " where requestid = " + requestId);
        return "1";
    }

    public String getFuZhiVal() {
        return fuZhiVal;
    }

    public void setFuZhiVal(String fuZhiVal) {
        this.fuZhiVal = fuZhiVal;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMainZd() {
        return mainZd;
    }

    public void setMainZd(String mainZd) {
        this.mainZd = mainZd;
    }

    public String getDetailZd() {
        return detailZd;
    }

    public void setDetailZd(String detailZd) {
        this.detailZd = detailZd;
    }

    public String getFuZhiZd() {
        return fuZhiZd;
    }

    public void setFuZhiZd(String fuZhiZd) {
        this.fuZhiZd = fuZhiZd;
    }
}
