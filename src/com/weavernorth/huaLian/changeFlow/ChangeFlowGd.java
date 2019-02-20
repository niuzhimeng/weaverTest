package com.weavernorth.huaLian.changeFlow;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * HR-07 调动（借调）审批流程 - 归档
 */
public class ChangeFlowGd extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("HR-07 调动（借调）审批流程 - 归档start" + TimeUtil.getCurrentTimeString());
        String tableName = requestInfo.getRequestManager().getBillTableName();
        String requestId = requestInfo.getRequestid();
        try {
            RecordSet recordSet = new RecordSet();
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");

            int xqrs = 1;  // 新增人数

            int afterYjbm = 0;  // 调入一级部门
            int beforeYjbm = 0; // 调出一级部门
            if (recordSet.next()) {
                afterYjbm = recordSet.getInt("afterYjbm");
                beforeYjbm = recordSet.getInt("beforeYjbm");
            }

            // 调入人员部门操作
            int sqzrs = 0; // 申请中人数
            int zbrs = 0; // 在编人数
            recordSet.executeQuery("select * from uf_rsbzxx where bm = " + afterYjbm);
            if (recordSet.next()) {
                sqzrs = recordSet.getInt("sqzrs") < 0 ? 0 : recordSet.getInt("sqzrs");
                zbrs = recordSet.getInt("zbrs") < 0 ? 0 : recordSet.getInt("zbrs");
            }
            sqzrs -= xqrs;
            zbrs += xqrs;
            recordSet.executeSql("update uf_rsbzxx set sqzrs = '" + sqzrs + "', zbrs = '" + zbrs + "' where bm = '" + afterYjbm + "'");

            // 调出人员部门操作
            int ksqrsdc = 0;  // 可申请人数
            int zbrsdc = 0;  //  在编人数

            RecordSet dcSet = new RecordSet();
            dcSet.executeQuery("select * from uf_rsbzxx where bm = " + beforeYjbm);
            if (dcSet.next()) {
                ksqrsdc = dcSet.getInt("ksqrs") < 0 ? 0 : dcSet.getInt("ksqrs");
                zbrsdc = dcSet.getInt("zbrs") < 0 ? 0 : dcSet.getInt("zbrs");
            }

            ksqrsdc += xqrs;
            zbrsdc -= xqrs;
            String sql = "update uf_rsbzxx set zbrs = '" + zbrsdc + "', ksqrs = '" + ksqrsdc + "' where bm = '" + beforeYjbm + "'";
            baseBean.writeLog("HR-07 调动（借调）审批流程sql： " + sql);
            dcSet.executeSql(sql);

        } catch (Exception e) {
            baseBean.writeLog("HR-07 调动（借调）审批流程 - 归档 Exception: " + e);
        }
        return "1";
    }
}
