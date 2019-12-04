package com.weavernorth.jiajiezb.hr;

import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import weaver.conn.RecordSet;
import weaver.hrm.resource.ResourceComInfo;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 转正流程
 */
public class ZhuanZhengWorkFlow extends BaseAction {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("转正流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            if (recordSet.next()) {
                // 转正人
                String xm = recordSet.getString("xm");
                // 转正日期
                String zzrq = recordSet.getString("zzrq");
                this.writeLog("转正日期: " + zzrq);

                // 查询字段原值
                String oldZzrq = JiaJieConnUtil.getCusById(xm, JiaJieConfigInfo.zzrq.getValue()); // 原始【转正日期】

                // 插入自定义表三条数据的基本字段
                JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
                // 更新此流程中字段
                recordSet.executeUpdate("update CUS_FIELDDATA set " + JiaJieConfigInfo.zzrq.getValue() + " = ? where id = ?", zzrq, xm);

                // 插入日志
                JiaJieConnUtil.insertPerCord(xm, requestId, "转正日期", oldZzrq, zzrq);

                // 变更人员状态
                recordSet.executeUpdate("update hrmresource set status = 1 where id = '" + xm + "'");
                // 清除人员缓存
                new ResourceComInfo().removeResourceCache();
            }
            this.writeLog("转正流程 End ===============");
        } catch (Exception e) {
            this.writeLog("转正流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("转正流程 异常： " + e);
            return "0";
        }

        return "1";
    }
}
