package com.weavernorth.saiwen.action.fybx;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 费用报销
 */
public class Reimbursement extends BaseAction {

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

        this.writeLog("费用报销 Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            if (recordSet.next()) {
                // 申请人
                String sqr = recordSet.getString("sqr");
                // 费用科目
                String fykm = recordSet.getString("fykm");
                // 付款金额
                String fkje = recordSet.getString("fkje");
                // 付款时间
                String fksj = recordSet.getString("fksj");
                // 付款方式
                String fkfs = recordSet.getString("fkfs");

                // 费用承担部门
                String fycdbm = recordSet.getString("fycdbm");
                // 报销说明
                String bxsm = recordSet.getString("bxsm");
                // 供应商
                String gys = recordSet.getString("gys");
                // 流程编号
                String lcbh = recordSet.getString("lcbh");
                // 未税金额
                String wsje = recordSet.getString("wsje");

                // 税额
                String se = recordSet.getString("se");
                // 价税合计
                String jshj = recordSet.getString("jshj");
                // 本次冲销金额
                String bccxje = recordSet.getString("bccxje");
                // 付款账户
                String fkzh = recordSet.getString("fpsl");



            }

            this.writeLog("费用报销 End ===============");
        } catch (Exception e) {
            this.writeLog("费用报销 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("费用报销 异常： " + e);
            return "0";
        }

        return "1";
    }
}
