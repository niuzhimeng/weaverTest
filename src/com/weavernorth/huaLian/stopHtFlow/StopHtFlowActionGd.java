package com.weavernorth.huaLian.stopHtFlow;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 12 - 终止合同流程 - 归档
 */
public class StopHtFlowActionGd extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("12 - 终止合同流程 - 归档start " + TimeUtil.getCurrentTimeString());
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");

            // 一级部门
            int yjbm = 0;
            if (recordSet.next()) {
                yjbm = recordSet.getInt("yjbm");
            }

            // 在编人数
            int zbrs = 0;
            // 可申请人数
            int ksqrs = 0;
            recordSet.executeQuery("select * from uf_rsbzxx where bm = " + yjbm);
            if (recordSet.next()) {
                zbrs = recordSet.getInt("zbrs") < 0 ? 0 : recordSet.getInt("zbrs");
                ksqrs = recordSet.getInt("ksqrs") < 0 ? 0 : recordSet.getInt("ksqrs");
            }
            ksqrs += 1;
            zbrs -= 1;
            recordSet.executeSql("update uf_rsbzxx set ksqrs = '" + ksqrs + "', zbrs = '" + zbrs + "' where bm = '" + yjbm + "'");
        } catch (Exception e) {
            baseBean.writeLog("12 - 终止合同流程 - 归档Exception: " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。");
            return "0";
        }

        return "1";
    }
}
