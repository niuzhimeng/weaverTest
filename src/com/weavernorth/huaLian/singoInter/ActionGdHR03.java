package com.weavernorth.huaLian.singoInter;

import com.weavernorth.huaLian.util.HlConnUtil;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * HR - 03 编制架构调整申请流程 - 归档
 */
public class ActionGdHR03 extends BaseAction {

    private BaseBean baseBean = new BaseBean();

    @Override
    public String execute(RequestInfo requestInfo) {
        baseBean.writeLog("HR - 03 编制架构调整申请流程start" + TimeUtil.getCurrentTimeString());
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
            int xzjsgwsl = 0;
            // 分部
            int ssfb = 0;
            if (recordSet.next()) {
                xzjsgwsl = recordSet.getInt("xzjsgwsl");
                ssfb = recordSet.getInt("ssfb");
            }

            baseBean.writeLog("获取到的分部： " + ssfb);
            if (ssfb == HlConnUtil.getZbId()) {
                // 总部不做校验
                return "1";
            }
            //可申请人数
            int ksqrs = 0;
            //可编制人员总数
            int kbzrszs = 0;
            recordSet.executeQuery("select * from uf_rsbzxx where fb = " + ssfb);
            if (recordSet.next()) {
                ksqrs = recordSet.getInt("ksqrs") < 0 ? 0 : recordSet.getInt("ksqrs");
                kbzrszs = recordSet.getInt("kbzrszs") < 0 ? 0 : recordSet.getInt("kbzrszs");

            }
            baseBean.writeLog("本次需求人数： " + xzjsgwsl);
            kbzrszs += xzjsgwsl;
            ksqrs += xzjsgwsl;

            String sql = "update uf_rsbzxx set kbzrszs = '" + kbzrszs + "', ksqrs = '" + ksqrs + "' where fb = '" + ssfb + "'";
            baseBean.writeLog("HR - 03 编制架构调整申请流程sql： " + sql);
            recordSet.executeSql(sql);
            baseBean.writeLog("HR - 03 编制架构调整申请流程 end" + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            baseBean.writeLog("HR - 03 编制架构调整申请流程 Exception: " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。");
            return "0";
        }
        return "1";
    }
}
