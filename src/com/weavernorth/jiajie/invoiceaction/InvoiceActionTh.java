package com.weavernorth.jiajie.invoiceaction;

import weaver.conn.RecordSet;
import weaver.general.Util;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

public class InvoiceActionTh extends BaseAction {
    /**
     * 明细表
     */
    private String mxb;
    /**
     * 发票字段名
     */
    private String fpzd;

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

        this.writeLog("发票验证接口退回Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            recordSet.executeQuery("select id from " + tableName + " where requestid = '" + requestId + "'");
            recordSet.next();
            int mainId = recordSet.getInt("id");

            String mxSql = "select * from " + tableName + mxb + " where mainid = " + mainId;
            this.writeLog("明细表查询sql： " + mxSql);
            recordSet.executeQuery(mxSql);
            StringBuilder fpBuilder = new StringBuilder();
            while (recordSet.next()) {
                String trim = Util.null2String(recordSet.getString(fpzd)).trim();
                // 过滤空的发票号码
                if (trim.length() > 0) {
                    fpBuilder.append(trim).append(",");
                }
            }
            if (fpBuilder.length() <= 0) {
                this.writeLog("表单未填写发票号");
                return "1";
            }
            fpBuilder.deleteCharAt(fpBuilder.length() - 1);
            this.writeLog("表单发票号码： " + fpBuilder.toString());

            RecordSet fpSet = new RecordSet();
            fpSet.executeUpdate("delete from uf_fpyc where fph in (" + fpBuilder.toString() + ")");

            this.writeLog("发票验证接口退回End ===============");
        } catch (Exception e) {
            this.writeLog("发票验证接口退回异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("发票验证接口退回异常： " + e);
            return "0";
        }

        return "1";
    }

    public String getMxb() {
        return mxb;
    }

    public void setMxb(String mxb) {
        this.mxb = mxb;
    }

    public String getFpzd() {
        return fpzd;
    }

    public void setFpzd(String fpzd) {
        this.fpzd = fpzd;
    }
}
