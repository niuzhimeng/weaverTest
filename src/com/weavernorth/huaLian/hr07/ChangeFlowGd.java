package com.weavernorth.huaLian.hr07;

import com.weavernorth.huaLian.util.HlConnUtil;
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
        baseBean.writeLog("HR-07 调动（借调）审批流程 - 归档 start" + TimeUtil.getCurrentTimeString());
        int formId = requestInfo.getRequestManager().getFormid();
        try {
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

            // 调出分部 ========================
            int dcfb = 0;
            // 调入分部
            int drfb = 0;
            if (recordSet.next()) {
                dcfb = recordSet.getInt("dcfb");
                drfb = recordSet.getInt("drfb");
            }

            // 在编人数
            int zbrs = 0;
            // 可申请人数
            int ksqrs = 0;
            if (dcfb != HlConnUtil.getZbId()) {
                recordSet.executeQuery("select * from uf_rsbzxx where fb = " + dcfb);
                if (recordSet.next()) {
                    zbrs = recordSet.getInt("zbrs") < 0 ? 0 : recordSet.getInt("zbrs");
                    ksqrs = recordSet.getInt("ksqrs") < 0 ? 0 : recordSet.getInt("ksqrs");
                }
                zbrs -= xqrs;
                ksqrs += xqrs;
                recordSet.executeSql("update uf_rsbzxx set ksqrs = '" + ksqrs + "', zbrs = '" + zbrs + "' where fb = '" + dcfb + "'");
            }

            // 调入分部 ========================

            // 在编人数
            zbrs = 0;
            // 申请中人数
            int sqzrs = 0;
            if (drfb != HlConnUtil.getZbId()) {
                recordSet.executeQuery("select * from uf_rsbzxx where fb = " + drfb);
                if (recordSet.next()) {
                    zbrs = recordSet.getInt("zbrs") < 0 ? 0 : recordSet.getInt("zbrs");
                    sqzrs = recordSet.getInt("sqzrs") < 0 ? 0 : recordSet.getInt("sqzrs");
                }
                zbrs += xqrs;
                sqzrs -= xqrs;
                recordSet.executeSql("update uf_rsbzxx set sqzrs = '" + sqzrs + "', zbrs = '" + zbrs + "' where fb = '" + drfb + "'");
            }
            baseBean.writeLog("HR-07 调动（借调）审批流程 - 归档 end" + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            baseBean.writeLog("HR-07 调动（借调）审批流程 - 归档 Exception: " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。");
            return "0";
        }
        return "1";
    }
}
