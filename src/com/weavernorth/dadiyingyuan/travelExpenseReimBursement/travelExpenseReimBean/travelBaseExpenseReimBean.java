package com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean;

import java.util.List;

public class travelBaseExpenseReimBean {
    private String number;
    private String pk_oa_head;
    private String applier;
    private String appDate;
    private String payDept;
    private String payOrg;
    private String bearDept;
    private String bearOrg;
    private String payType;
    private String auditor;
    private String auditTime;
    private String creator;
    private String originatTime;
    private String attendCount;
    private String oaBizType;
    private List<travelExpenseListBean> expenseList;
    private List<travelRecListBean> recList;
    private List<travelRevLoanBean> revLoan;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPk_oa_head() {
        return pk_oa_head;
    }

    public void setPk_oa_head(String pk_oa_head) {
        this.pk_oa_head = pk_oa_head;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getPayDept() {
        return payDept;
    }

    public void setPayDept(String payDept) {
        this.payDept = payDept;
    }

    public String getPayOrg() {
        return payOrg;
    }

    public void setPayOrg(String payOrg) {
        this.payOrg = payOrg;
    }

    public String getBearDept() {
        return bearDept;
    }

    public void setBearDept(String bearDept) {
        this.bearDept = bearDept;
    }

    public String getBearOrg() {
        return bearOrg;
    }

    public void setBearOrg(String bearOrg) {
        this.bearOrg = bearOrg;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOriginatTime() {
        return originatTime;
    }

    public void setOriginatTime(String originatTime) {
        this.originatTime = originatTime;
    }

    public String getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(String attendCount) {
        this.attendCount = attendCount;
    }

    public String getOaBizType() {
        return oaBizType;
    }

    public void setOaBizType(String oaBizType) {
        this.oaBizType = oaBizType;
    }

    public List<travelExpenseListBean> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<travelExpenseListBean> expenseList) {
        this.expenseList = expenseList;
    }

    public List<travelRecListBean> getRecList() {
        return recList;
    }

    public void setRecList(List<travelRecListBean> recList) {
        this.recList = recList;
    }

    public List<travelRevLoanBean> getRevLoan() {
        return revLoan;
    }

    public void setRevLoan(List<travelRevLoanBean> revLoan) {
        this.revLoan = revLoan;
    }

    @Override
    public String toString() {
        return "travelBaseExpenseReimBean{" +
                "number='" + number + '\'' +
                ", pk_oa_head='" + pk_oa_head + '\'' +
                ", applier='" + applier + '\'' +
                ", appDate='" + appDate + '\'' +
                ", payDept='" + payDept + '\'' +
                ", payOrg='" + payOrg + '\'' +
                ", bearDept='" + bearDept + '\'' +
                ", bearOrg='" + bearOrg + '\'' +
                ", payType='" + payType + '\'' +
                ", auditor='" + auditor + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", creator='" + creator + '\'' +
                ", originatTime='" + originatTime + '\'' +
                ", attendCount='" + attendCount + '\'' +
                ", oaBizType='" + oaBizType + '\'' +
                ", expenseList=" + expenseList +
                ", recList=" + recList +
                ", revLoan=" + revLoan +
                '}';
    }
}
