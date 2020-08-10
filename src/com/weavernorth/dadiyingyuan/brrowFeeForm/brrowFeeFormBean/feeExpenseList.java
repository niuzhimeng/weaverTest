package com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormBean;

public class feeExpenseList {
    private String pkOaBody;
    private String expenseType;
    private String purpose;
    //private double amt;
    private String localAmt;
    private String approvedAmt;
    private String payEntryDept;
    private String payEntryOrg;
    //新增 xianjin
    private String zhifu;
    public String getZhifu() {
        return zhifu;
    }

    public void setZhifu(String zhifu) {
        this.zhifu = zhifu;
    }

    public String getXianjin() {
        return xianjin;
    }

    public void setXianjin(String xianjin) {
        this.xianjin = xianjin;
    }

    private String xianjin;
    public String getPkOaBody() {
        return pkOaBody;
    }

    public void setPkOaBody(String pkOaBody) {
        this.pkOaBody = pkOaBody;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /*public double getAmt() {
        return amt;
    }*/

    /*public void setAmt(double amt) {
        amt = amt;
    }*/

    public String getLocalAmt() {
        return localAmt;
    }

    public void setLocalAmt(String localAmt) {
        this.localAmt = localAmt;
    }

    public String getApprovedAmt() {
        return approvedAmt;
    }

    public void setApprovedAmt(String approvedAmt) {
        this.approvedAmt = approvedAmt;
    }

    public String getPayEntryDept() {
        return payEntryDept;
    }

    public void setPayEntryDept(String payEntryDept) {
        this.payEntryDept = payEntryDept;
    }

    public String getPayEntryOrg() {
        return payEntryOrg;
    }

    public void setPayEntryOrg(String payEntryOrg) {
        this.payEntryOrg = payEntryOrg;
    }

    @Override
    public String toString() {
        return "feeExpenseList{" +
                "pkOaBody='" + pkOaBody + '\'' +
                ", expenseType='" + expenseType + '\'' +
                ", purpose='" + purpose + '\'' +
                ", localAmt=" + localAmt +
                ", approvedAmt=" + approvedAmt +
                ", payEntryDept='" + payEntryDept + '\'' +
                ", payEntryOrg='" + payEntryOrg + '\'' +
                '}';
    }
}
