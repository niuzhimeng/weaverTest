package com.weavernorth.zhongsha21.action;

import com.weavernorth.zhongsha21.util.CreateWorkflowUtil;
import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 付款流程错误提醒
 */
public class FuKuanRemindAction extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int creator = requestInfo.getRequestManager().getCreater();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = " + formId);
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("付款流程错误提醒 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName +
                "流程创建人： " + creator);
        try {
            // 查询主表
            recordSet.executeQuery("select b.* from " + tableName + " a inner join " + tableName + "_dt6 b on a.id = b.mainid where a.requestid = '" + requestId + "' order by id desc");
            if (recordSet.next()) {
                String xxlx = Util.null2String(recordSet.getString("xxlx")); // 消息类型
                String xxwb = recordSet.getString("xxwb"); // 消息文本
                if ("E".equalsIgnoreCase(xxlx)) {
                    CreateWorkflowUtil.createFlow("1", requestId, xxwb, String.valueOf(creator));
                }
            }

            this.writeLog("付款流程错误提醒 End ===============");
        } catch (Exception e) {
            this.writeLog("付款流程错误提醒 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("付款流程错误提醒 异常： " + e);
            return "0";
        }

        return "1";
    }
}
