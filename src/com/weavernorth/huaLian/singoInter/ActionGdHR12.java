package com.weavernorth.huaLian.singoInter;

import com.weavernorth.huaLian.util.HlConnUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * HR 12 - 终止合同流程 - 归档
 */
public class ActionGdHR12 extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("HR 12 - 终止合同流程 - 归档 start " + TimeUtil.getCurrentTimeString());
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

            // 所属分部
            int fb = 0;
            if (recordSet.next()) {
                fb = recordSet.getInt("fb");
            }

            if (fb == HlConnUtil.getZbId()) {
                // 总部不做校验
                return "1";
            }

            // 在编人数
            int zbrs = 0;
            // 可申请人数
            int ksqrs = 0;
            recordSet.executeQuery("select * from uf_rsbzxx where fb = " + fb);
            if (recordSet.next()) {
                zbrs = recordSet.getInt("zbrs") < 0 ? 0 : recordSet.getInt("zbrs");
                ksqrs = recordSet.getInt("ksqrs") < 0 ? 0 : recordSet.getInt("ksqrs");
            }
            ksqrs += 1;
            zbrs -= 1;
            recordSet.executeSql("update uf_rsbzxx set ksqrs = '" + ksqrs + "', zbrs = '" + zbrs + "' where fb = '" + fb + "'");
            baseBean.writeLog("HR 12 - 终止合同流程 - 归档 end " + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            baseBean.writeLog("HR 12 - 终止合同流程 - 归档Exception: " + TimeUtil.getCurrentTimeString() + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。");
            return "0";
        }

        return "1";
    }
}
