package com.weavernorth.jiajiezb.hr;

import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

/**
 * 员工入职流程
 */
public class RuZhiWorkFlow extends BaseAction {

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

        this.writeLog("员工入职流程 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            // 查询主表
            recordSet.executeQuery("select * from " + tableName + " where requestid = '" + requestId + "'");
            if (recordSet.next()) {
                String lb = recordSet.getString(" lb"); // 类别
                // 系统表部分 ====================
                String xm = recordSet.getString("xm"); // 姓名
                String ygbh = recordSet.getString("ygbh"); // 员工编号
                String ygzt = statusChange(recordSet.getString("ygzt")); // 员工状态
                String gw = recordSet.getString("gw"); // 岗位
                String zsld = recordSet.getString("zsld"); // 直接上级

                String bm = recordSet.getString("bm"); // 部门
                String bmdd = ""; // 办公地点
                if ("0".equals(lb)) {
                    // 经营类（业务）
                    bmdd = bgddChange(recordSet.getString("bgddyw"));
                } else {
                    // 管理类（职能）
                    bmdd = bgddChange(recordSet.getString("bgddzn"));
                }

                String syjsrq = recordSet.getString("syjsrq"); // 试用期结束日期

                // 自定义表部分 ====================

                // 建模表部分 ====================
            }

            this.writeLog("员工入职流程 End ===============");
        } catch (Exception e) {
            this.writeLog("员工入职流程 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("员工入职流程 异常： " + e);
            return "0";
        }

        return "1";
    }

    private String bgddChange(String bgdd) {
        // 办公地点转换
        String returnStr = "北京";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("select * from uf_jtbgdd where id = '" + bgdd + "'");
        if (recordSet.next()) {
            returnStr = recordSet.getString("bgdd");
        }

        return returnStr;
    }

    private String statusChange(String status) {
        String returnStatus = "";
        if ("0".equals(status)) {
            // 试用期
            returnStatus = "0";
        } else if ("1".equals(status)) {
            // 正式员工
            returnStatus = "1";
        } else if ("2".equals(status)) {
            // 实习生
            returnStatus = "2";
        }

        return returnStatus;
    }
}
