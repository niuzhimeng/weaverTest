package com.weavernorth.saiwen.action.yfk;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 预付款
 */
public class AdvancePayment extends BaseAction {

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

        this.writeLog("预付款 Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            if (recordSet.next()) {
                // 科目
                String km = recordSet.getString("km");
                // 申请人
                String sqr = recordSet.getString("sqr");
                // 借款金额
                String jkje = recordSet.getString("jkje");
                // 借款日期
                String jkrq = recordSet.getString("jkrq");
                // 借款类型
                String jklx = recordSet.getString("jklx");

                // 公司账户
                String gszh = recordSet.getString("gszh");
                // 借款事由
                String jksy = recordSet.getString("jksy");
                // 供应商
                String gys = recordSet.getString("gys");
                // 流程编号
                String lcbh = recordSet.getString("lcbh");


            }

            this.writeLog("预付款 End ===============");
        } catch (Exception e) {
            this.writeLog("预付款 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("预付款 异常： " + e);
            return "0";
        }

        return "1";
    }
}
