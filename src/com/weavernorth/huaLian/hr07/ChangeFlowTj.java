package com.weavernorth.huaLian.hr07;

import com.weavernorth.huaLian.util.HlConnUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * HR-07 调动（借调）审批流程 - 提交
 */
public class ChangeFlowTj extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("HR-07 调动（借调）审批流程 - 提交 start" + TimeUtil.getCurrentTimeString());
        try {
            int formId = requestInfo.getRequestManager().getFormid();
            String tableName = "";
            RecordSet recordSet = new RecordSet();
            recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
            if (recordSet.next()) {
                tableName = recordSet.getString("tablename");
            }
            String requestId = requestInfo.getRequestid();

            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            // 新增人数
            int xqrs = 1;

            // 调入分部 ========================
            int drfb = 0;
            if (recordSet.next()) {
                drfb = recordSet.getInt("drfb");
            }

            // 可申请人数
            int ksqrs = 0;
            // 申请中人数
            int sqzrs = 0;
            if (drfb != HlConnUtil.getZbId()) {
                recordSet.executeQuery("select * from uf_rsbzxx where fb = " + drfb);
                if (recordSet.next()) {
                    ksqrs = recordSet.getInt("ksqrs") < 0 ? 0 : recordSet.getInt("ksqrs");
                    sqzrs = recordSet.getInt("sqzrs") < 0 ? 0 : recordSet.getInt("sqzrs");
                }
                sqzrs += xqrs;
                ksqrs -= xqrs;
                recordSet.executeSql("update uf_rsbzxx set sqzrs = '" + sqzrs + "', ksqrs = '" + ksqrs + "' where fb = '" + drfb + "'");
            }
            baseBean.writeLog("HR-07 调动（借调）审批流程 - 提交 end" + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            baseBean.writeLog("HR-07 调动（借调）审批流程 - 提交Exception: " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。");
            return "0";
        }
        return "1";
    }
}
