package com.weavernorth.jiajie.personchange;

import com.weaver.general.TimeUtil;
import com.weavernorth.jiajie.util.JiaJieUtil;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 转正申请-wdd
 */
public class ZhuanZhengAction extends BaseAction {
    /**
     * 转正日期
     */
    private static final String ZZRQ_FIELD = "field17";


    @Override
    public String execute(RequestInfo requestInfo) {
        String currentTimeString = TimeUtil.getCurrentTimeString();
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = '" + formId + "'");
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("转正申请-wdd Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            // 转正人
            String xm = recordSet.getString("xm");
            // 建议
            String jy = recordSet.getString("jy");
            // 转正日期
            String zzrq = recordSet.getString("zzrq");
            // 计划转正日期
            String jhzzrq = recordSet.getString("jhzzrq");
            String zzDate;
            if ("0".equals(jy)) {
                zzDate = jhzzrq;
            } else if ("3".equals(jy)) {
                zzDate = zzrq;
            } else {
                this.writeLog("该人员没有转正，action不做操作");
                return "1";
            }

            // 变更人员卡片
            String oldZzDate = "";
            RecordSet updateSet = new RecordSet();
            recordSet.executeQuery("select " + ZZRQ_FIELD + " from CUS_FIELDDATA where id = " + xm);
            if (recordSet.next()) {
                // 更新
                oldZzDate = recordSet.getString(ZZRQ_FIELD);
                String updateSql = "update CUS_FIELDDATA set " + ZZRQ_FIELD + " = '" + zzDate + "' where id = " + xm;
                this.writeLog("转正申请updateSql: " + updateSql);
                updateSet.executeUpdate(updateSql);
            } else {
                // 新增
                String insertSql = "insert into CUS_FIELDDATA(" + ZZRQ_FIELD + ", scope, scopeid, id) values(?,?,?,?)";
                this.writeLog("转正申请insertSql： " + insertSql);
                updateSet.executeUpdate(insertSql,
                        zzDate, "HrmCustomFieldByInfoType", "-1", xm);
            }

            // 插入人员信息变更记录表
            JiaJieUtil.insertPerCord(requestId, oldZzDate, zzDate, "转正日期", xm);
            this.writeLog("建模授权开始=====");
            JiaJieUtil.rebuildModeDataShare(currentTimeString);

            this.writeLog("转正申请-wdd End ===============");
        } catch (Exception e) {
            this.writeLog("转正申请-wdd 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("转正申请-wdd 异常： " + e);
            return "0";
        }

        return "1";
    }


}
