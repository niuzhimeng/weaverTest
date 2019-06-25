package com.weavernorth.renhuan.action;

import com.weavernorth.renhuan.util.RhConnUtil;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.util.HashMap;
import java.util.Map;

public class InsertInvoice extends BaseAction {
    /**
     * 明细表名称
     */
    private String detailTableName;
    /**
     * 发票字段名
     */
    private String invoiceName;
    /**
     * 发票金额字段名
     */
    private String invoiceMoneyName;

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

        this.writeLog("插入发票建模 Start requestid --- " + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            Map<String, String> fpMap = new HashMap<String, String>();
            // 主表id
            recordSet.executeQuery("select id from " + tableName + " where requestid = " + requestId);
            recordSet.next();
            String mainId = recordSet.getString("id");

            recordSet.executeQuery("select " + invoiceName + "," + invoiceMoneyName + " from " + detailTableName + " where mainid = " + mainId);
            while (recordSet.next()) {
                fpMap.put(recordSet.getString(invoiceName), recordSet.getString(invoiceMoneyName));
            }

            // 插入建模
            RhConnUtil.insertTimedLog(fpMap);
            this.writeLog("插入发票建模 End");
        } catch (Exception e) {
            this.writeLog("插入发票建模表异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("接口异常，请联系管理员。" + e);
            return "0";
        }

        return "1";
    }


    public String getDetailTableName() {
        return detailTableName;
    }

    public void setDetailTableName(String detailTableName) {
        this.detailTableName = detailTableName;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoiceMoneyName() {
        return invoiceMoneyName;
    }

    public void setInvoiceMoneyName(String invoiceMoneyName) {
        this.invoiceMoneyName = invoiceMoneyName;
    }
}
