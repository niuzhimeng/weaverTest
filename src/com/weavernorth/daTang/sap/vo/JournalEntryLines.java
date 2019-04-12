package com.weavernorth.daTang.sap.vo;

import java.util.List;

public class JournalEntryLines {
    private String Line_ID;
    private String AccountCode;
    private String Debit;
    private String Credit;
    private String LineMemo;
    private String CostingCode;
    private String CostingCode2;
    private String CostingCode3;
    private String CostingCode4;
    private String CostingCode5;
    private List<CashFlowAssignments> CashFlowAssignments;

    public String getLine_ID() {
        return Line_ID;
    }

    public void setLine_ID(String line_ID) {
        Line_ID = line_ID;
    }

    public String getAccountCode() {
        return AccountCode;
    }

    public void setAccountCode(String accountCode) {
        AccountCode = accountCode;
    }

    public String getDebit() {
        return Debit;
    }

    public void setDebit(String debit) {
        Debit = debit;
    }

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }

    public String getLineMemo() {
        return LineMemo;
    }

    public void setLineMemo(String lineMemo) {
        LineMemo = lineMemo;
    }

    public String getCostingCode() {
        return CostingCode;
    }

    public void setCostingCode(String costingCode) {
        CostingCode = costingCode;
    }

    public String getCostingCode2() {
        return CostingCode2;
    }

    public void setCostingCode2(String costingCode2) {
        CostingCode2 = costingCode2;
    }

    public String getCostingCode3() {
        return CostingCode3;
    }

    public void setCostingCode3(String costingCode3) {
        CostingCode3 = costingCode3;
    }

    public String getCostingCode4() {
        return CostingCode4;
    }

    public void setCostingCode4(String costingCode4) {
        CostingCode4 = costingCode4;
    }

    public String getCostingCode5() {
        return CostingCode5;
    }

    public void setCostingCode5(String costingCode5) {
        CostingCode5 = costingCode5;
    }

    public List<com.weavernorth.daTang.sap.vo.CashFlowAssignments> getCashFlowAssignments() {
        return CashFlowAssignments;
    }

    public void setCashFlowAssignments(List<com.weavernorth.daTang.sap.vo.CashFlowAssignments> cashFlowAssignments) {
        CashFlowAssignments = cashFlowAssignments;
    }
}
