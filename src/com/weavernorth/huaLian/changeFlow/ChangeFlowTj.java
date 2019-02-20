package com.weavernorth.huaLian.changeFlow;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 *  HR-07 调动（借调）审批流程 - 提交
 */
public class ChangeFlowTj extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("HR-07 调动（借调）审批流程 - 提交 start" + TimeUtil.getCurrentTimeString());
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            // 新增人数
            int xqrs = 1;
            // 变动后一级部门
            int afterYjbm = 0;
            if (recordSet.next()) {
                afterYjbm = recordSet.getInt("afterYjbm");
            }

            //可申请人数
            int ksqrs = 0;
            //申请中人数
            int sqzrs = 0;
            recordSet.executeQuery("select * from uf_rsbzxx where bm = " + afterYjbm);
            if (recordSet.next()) {
                ksqrs = recordSet.getInt("ksqrs") < 0 ? 0 : recordSet.getInt("ksqrs");
                sqzrs = recordSet.getInt("sqzrs") < 0 ? 0 : recordSet.getInt("sqzrs");
            }
            sqzrs += xqrs;
            ksqrs -= xqrs;
            recordSet.executeSql("update uf_rsbzxx set sqzrs = '" + sqzrs + "', ksqrs = '" + ksqrs + "' where bm = '" + afterYjbm + "'");
        } catch (Exception e) {
            baseBean.writeLog("HR-07 调动（借调）审批流程 - 提交Exception: " + e);
        }
        return "1";
    }
}
