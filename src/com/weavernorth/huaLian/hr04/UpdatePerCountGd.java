package com.weavernorth.huaLian.hr04;

import com.weavernorth.huaLian.util.HlConnUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * HR-04 人力需求申请流程  - 归档
 */
public class UpdatePerCountGd extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("HR-04 人力需求申请流程  - 归档 start" + TimeUtil.getCurrentTimeString());
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
            // 本次需求人数
            int xqrs = 0;
            // 所属分部
            int ssfb = 0;
            if (recordSet.next()) {
                xqrs = recordSet.getInt("xqrs") < 0 ? 0 : recordSet.getInt("xqrs");
                ssfb = recordSet.getInt("ssfb");
            }
            if (ssfb == HlConnUtil.getZbId()) {
                // 总部不做校验
                return "1";
            }
            // 招聘中人数
            int zpzrs = 0;
            // 申请中人数
            int sqzrs = 0;
            recordSet.executeQuery("select * from uf_rsbzxx where fb = " + ssfb);
            if (recordSet.next()) {
                zpzrs = recordSet.getInt("zpzrs") < 0 ? 0 : recordSet.getInt("zpzrs");
                sqzrs = recordSet.getInt("sqzrs") < 0 ? 0 : recordSet.getInt("sqzrs");
            }
            baseBean.writeLog("申请中人数： " + sqzrs);
            baseBean.writeLog("招聘中人数： " + zpzrs);
            sqzrs -= xqrs;
            zpzrs += xqrs;
            recordSet.executeSql("update uf_rsbzxx set sqzrs = '" + sqzrs + "', zpzrs = '" + zpzrs + "' where fb = '" + ssfb + "'");
            baseBean.writeLog("HR-04 人力需求申请流程  - 归档 end" + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            baseBean.writeLog("HR-04 人力需求申请流程  - 归档 Exception: " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。");
            return "0";
        }

        return "1";
    }
}
