package com.weavernorth.dadiyingyuan.feeReimburse.feeBaseReimburseBean;

import java.util.Date;
import java.util.List;

public class feeBaseReimburseBean {
    private String number;
    private String pk_oa_head;
    private String applier;
    private Date appDate;
    private String appDept;
    private String appOrg;
    private String payDept;
    private String payOrg;
    private String currency;
    private String payType;
    private String describ;
    private String auditor;
    private Date auditTime;
    private String creator;
    private Date originatTime;
    private int attendCount;
    private String oaBizType;
    private List<feeReimburseExpenseListBean> expenseList;
    private List<feeReimburseRecListBean> recList;
    private List<feeReimburseRevLoanBean> revLoan;

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

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
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

    public int getAttendCount() {
        return attendCount;
    }

    public void setAttendCount(int attendCount) {
        this.attendCount = attendCount;
    }

    public String getOaBizType() {
        return oaBizType;
    }

    public void setOaBizType(String oaBizType) {
        this.oaBizType = oaBizType;
    }

    public List<feeReimburseExpenseListBean> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<feeReimburseExpenseListBean> expenseList) {
        this.expenseList = expenseList;
    }

    public List<feeReimburseRecListBean> getRecList() {
        return recList;
    }

    public void setRecList(List<feeReimburseRecListBean> recList) {
        this.recList = recList;
    }

    public List<feeReimburseRevLoanBean> getRevLoan() {
        return revLoan;
    }

    public void setRevLoan(List<feeReimburseRevLoanBean> revLoan) {
        this.revLoan = revLoan;
    }

    @Override
    public String toString() {
        return "feeBaseReimburseBean{" +
                "number='" + number + '\'' +
                ", pk_oa_head='" + pk_oa_head + '\'' +
                ", applier='" + applier + '\'' +
                ", appDate=" + appDate +
                ", appDept='" + appDept + '\'' +
                ", appOrg='" + appOrg + '\'' +
                ", payDept='" + payDept + '\'' +
                ", payOrg='" + payOrg + '\'' +
                ", currency='" + currency + '\'' +
                ", payType='" + payType + '\'' +
                ", describ='" + describ + '\'' +
                ", auditor='" + auditor + '\'' +
                ", auditTime=" + auditTime +
                ", creator='" + creator + '\'' +
                ", originatTime=" + originatTime +
                ", attendCount=" + attendCount +
                ", oaBizType='" + oaBizType + '\'' +
                ", expenseList=" + expenseList +
                ", recList=" + recList +
                ", revLoan=" + revLoan +
                '}';
    }
}
