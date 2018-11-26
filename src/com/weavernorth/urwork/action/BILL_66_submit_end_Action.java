

package com.weavernorth.urwork.action;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class BILL_66_submit_end_Action extends BaseAction {

    public String execute(RequestInfo request) {
        RecordSet rs = new RecordSet();
        String requestId = Util.null2String(request.getRequestid());
        String tableName = request.getRequestManager().getBillTableName();
        String requestWriteLog = " 员工异动审批流程 ";
        this.writeLog(requestWriteLog + " start-------------- 开始修改！ requestId=" + requestId);
        boolean flag = true;

        label55: {
            String var8;
            try {
                String sql = "update uf_cbzxb set C02 = t2.H46 , C05 = t2.H65 , C04 = t2.H64 , C06 = t2.H002  from uf_cbzxb t1  inner join " + tableName + " t2 on t1.C01 = t2.H03 where t2.requestId = '" + requestId + "' ";
                this.writeLog(requestWriteLog + "执行SQL语句：" + sql);
                flag = rs.executeSql(sql);
                break label55;
            } catch (Exception var12) {
                this.writeLog(requestWriteLog + "出现异常：" + var12);
                request.getRequestManager().setMessage("111000");
                request.getRequestManager().setMessagecontent(requestWriteLog + "出现异常，请联系系统管理员：" + var12);
                var8 = "0";
            } finally {
                if (!flag) {
                    request.getRequestManager().setMessage("111000");
                    request.getRequestManager().setMessagecontent(requestWriteLog + "出现异常，请联系系统管理员");
                    return "0";
                }

            }

            return var8;
        }

        this.writeLog(requestWriteLog + " end ---完成修改!");
        return "1";
    }
}
