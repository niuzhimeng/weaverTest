package com.weavernorth.hongxing.bianHao;

import com.weaver.general.TimeUtil;
import com.weavernorth.hongxing.bianHao.util.Util;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.conn.RecordSetTrans;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 支付流程编号修改
 */
public class ZhiFu extends BaseAction {
    @Override
    public String execute(RequestInfo requestInfo) {

        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        this.writeLog("---------支付流程编号修改执行-----------" + TimeUtil.getCurrentTimeString() + ", requestId:" + requestId);

        String fyzfbm = "";//费用支付编码
        String fysqbh = "";//费用申请编码

        RecordSetDataSource rs = new RecordSetDataSource("orcl");
        String selectSql = "select * from " + tableName + " where requestid = '" + requestId + "'";
        rs.execute(selectSql);
        if (rs.next()) {
            fysqbh = rs.getString("fysqbh");
            fyzfbm = rs.getString("fyzfbm");
        }
        if (fyzfbm.trim().length() > 0) {
            return "1";
        }
        fyzfbm = fysqbh + "-" + new Util().getCurrentNoZhiFu(fysqbh);
        this.writeLog("费用申请编号====================nzm---> " + fysqbh);
        //更新流程表单
        RecordSet rs1 = new RecordSet();
        String updateSql = "update " + tableName + " set fyzfbm = '" + fyzfbm + "'  where requestid = '" + requestId + "'";
        rs1.execute(updateSql);
        //更新请求名称
        RecordSetTrans updateWorkFlowSet = requestInfo.getRsTrans();
        String updateWorkFlow = "update workflow_requestbase set requestmark = '" + fyzfbm + "' WHERE requestid = '" + requestId + "'";
        try {
            updateWorkFlowSet.executeSql(updateWorkFlow);
        } catch (Exception e) {
            this.writeLog("RecordSetTrans报错" + e);
        }
        return "1";
    }
}
