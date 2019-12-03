package com.weavernorth.jiajiezb.hr;

import com.weavernorth.jiajiezb.hr.util.JiaJieConfigInfo;
import com.weavernorth.jiajiezb.hr.util.JiaJieConnUtil;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 离职流程
 */
public class LiZhiWorkFlow extends BaseAction {

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

        this.writeLog("离职流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            if (recordSet.next()) {
                // 离职人
                String xm = recordSet.getString("xm");
                // 是否有经济补偿金
                String jjbcjm = recordSet.getString("jjbcjm");
                // 离职日期（工资结算日）
                String gzjsr = recordSet.getString("gzjsr");
                this.writeLog("是否有经济补偿金: " + jjbcjm + ", 离职日期（工资结算日）: " + gzjsr);

                jjbcjm = JiaJieConnUtil.yesOrNoChange(jjbcjm);

                // 插入自定义表三条数据的基本字段
                JiaJieConnUtil.insertBaseCus(Integer.parseInt(xm));
                // 更新此流程中字段
                recordSet.executeUpdate("update CUS_FIELDDATA set " + JiaJieConfigInfo.sfyjjbc.getValue() + " = ?, " +
                        JiaJieConfigInfo.lzrq.getValue() + " = ? where id = ?", jjbcjm, gzjsr, xm);

                // 插入日志
                JiaJieConnUtil.insertPerCord(xm, requestId, "离职日期", "", gzjsr);
                JiaJieConnUtil.insertPerCord(xm, requestId, "是否有经济补偿金", "", jjbcjm);
            }
            this.writeLog("离职流程 End ===============");
        } catch (Exception e) {
            this.writeLog("离职流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("离职流程 异常： " + e);
            return "0";
        }

        return "1";
    }
}
