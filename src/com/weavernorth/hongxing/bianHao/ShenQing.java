package com.weavernorth.hongxing.bianHao;

import com.weaver.general.TimeUtil;
import com.weavernorth.hongxing.bianHao.util.Util;
import weaver.conn.RecordSetDataSource;
import weaver.conn.RecordSetTrans;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 申请流程编号修改
 */
public class ShenQing extends BaseAction {
    @Override
    public String execute(RequestInfo requestInfo) {

        String requestId = requestInfo.getRequestid();
        RecordSetDataSource hrmrs = new RecordSetDataSource("orcl");
        String tableSql = "SELECT tablename FROM workflow_bill WHERE id = (SELECT formid FROM workflow_base WHERE id = (SELECT workflowid FROM workflow_requestbase WHERE requestid = '" + requestId + "'))";

        String tableName = "";
        hrmrs.execute(tableSql);
        if (hrmrs.next()) {
            tableName = hrmrs.getString("tablename");
        }

        this.writeLog("----------申请流程编号修改执行-------------" + TimeUtil.getCurrentTimeString() + ", requestId:" + requestId + ",  tablename: " + tableName);

        String fysqbh = "";//费用申请编号
        String lxbm = "";//立项编码

        String selectSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
        hrmrs.execute(selectSql);
        if (hrmrs.next()) {
            lxbm = hrmrs.getString("lxbm");
            fysqbh = hrmrs.getString("fysqbh");
        }
        if (fysqbh.trim().length() > 0) {
            return "1";
        }
        fysqbh = lxbm + "-" + new Util().getCurrentNoShenQing(lxbm);
        this.writeLog("费用申请编号====================nzm---> " + fysqbh);
        //更新流程表单
        String updateSql = "update " + tableName + " set fysqbh = '" + fysqbh + "'  where requestid = '" + requestId + "'";
        hrmrs.execute(updateSql);
        //更新请求名称
        RecordSetTrans updateWorkFlowSet = requestInfo.getRsTrans();
        String updateWorkFlow = "update workflow_requestbase set requestmark = '" + fysqbh + "' WHERE requestid = '" + requestId + "'";
        try {
            updateWorkFlowSet.executeSql(updateWorkFlow);
        } catch (Exception e) {
            this.writeLog("RecordSetTrans报错" + e);
        }
        return "1";
    }
}
