package com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormBean;

import java.util.Date;
import java.util.List;

public class brrowBaseFeeFormBean {
    private String pkOaHead;
    private String number;
    private String applier;
    private Date appDate;
    private Date repaymentDate;
    private String appDept;
    private String appOrg;
    private String payDept;
    private String payOrg;
    private String payType;
    private String oaBizType;
    private String auditor;
    private Date auditTime;
    private String creator;
    private Date originatTime;
    private String attendCount;
    private List<feeExpenseList> expenseList;
    private List<feeRecList> recList;

    public String pkOaHead() {
        return pkOaHead;
    }

    public void pkOaHead(String pkOaHead) {
        this.pkOaHead = pkOaHead;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getApplier() {
        return applier;
    }

    public void setApplier(String applier) {
        this.applier = applier;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getAppDept() {
        return appDept;
    }

    public void setAppDept(String appDept) {
        this.appDept = appDept;
    }

    public String getAppOrg() {
        return appOrg;
    }

    public void setAppOrg(String appOrg) {
        this.appOrg = appOrg;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOaBizType() {
        return oaBizType;
    }

    public void setOaBizType(String oaBizType) {
        this.oaBizType = oaBizType;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getOriginatTime() {
        return originatTime;
    }

    public void setOriginatTime(Date originatTime) {
        this.originatTime = originatTime;
    }

    public String getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(String attendCount) {
        this.attendCount = attendCount;
    }

    public List<feeExpenseList> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<feeExpenseList> expenseList) {
        this.expenseList = expenseList;
    }

    public List<feeRecList> getRecList() {
        return recList;
    }

    public void setRecList(List<feeRecList> recList) {
        this.recList = recList;
    }

    @Override
    public String toString() {
        return "brrowBaseFeeFormBean{" +
                "pkOaHead='" + pkOaHead + '\'' +
                ", number='" + number + '\'' +
                ", applier='" + applier + '\'' +
                ", appDate=" + appDate +
                ", repaymentDate=" + repaymentDate +
                ", appDept='" + appDept + '\'' +
                ", appOrg='" + appOrg + '\'' +
                ", payDept='" + payDept + '\'' +
                ", payOrg='" + payOrg + '\'' +
                ", payType='" + payType + '\'' +
                ", oaBizType='" + oaBizType + '\'' +
                ", auditor='" + auditor + '\'' +
                ", auditTime=" + auditTime +
                ", creator='" + creator + '\'' +
                ", originatTime=" + originatTime +
                ", attendCount='" + attendCount + '\'' +
                ", expenseList=" + expenseList +
                ", recList=" + recList +
                '}';
    }
}
