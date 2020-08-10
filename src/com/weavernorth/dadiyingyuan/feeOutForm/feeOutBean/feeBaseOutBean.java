package com.weavernorth.dadiyingyuan.feeOutForm.feeOutBean;

import java.util.List;

public class feeBaseOutBean {
    private String number;
    private String pk_oa_head;
    private String applier;
    private String appDate;
    private String payDept;
    private String payOrg;
    private String payType;
    private String recType;
    private String recSuplier;
    private String recBank;
    private String recBankAccount;
    private String retailCus;
    private String retailBank;
    private String retailBankAccount;
    private String auditor;
    private String auditTime;
    private String creator;
    private String originatTime;
    private String contractNo;
    private String contractName;
    private String contractType;
    private String contractAmt;
    private String paiedAmt;
    private String unPayAmt;
    private String attendCount;
    private String oaBizType;
    private String deleted;
    private List<feeOutExpenseList> expenseList;

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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    public String getRecSuplier() {
        return recSuplier;
    }

    public void setRecSuplier(String recSuplier) {
        this.recSuplier = recSuplier;
    }

    public String getRecBank() {
        return recBank;
    }

    public void setRecBank(String recBank) {
        this.recBank = recBank;
    }

    public String getRecBankAccount() {
        return recBankAccount;
    }

    public void setRecBankAccount(String recBankAccount) {
        this.recBankAccount = recBankAccount;
    }

    public String getRetailCus() {
        return retailCus;
    }

    public void setRetailCus(String retailCus) {
        this.retailCus = retailCus;
    }

    public String getRetailBank() {
        return retailBank;
    }

    public void setRetailBank(String retailBank) {
        this.retailBank = retailBank;
    }

    public String getRetailBankAccount() {
        return retailBankAccount;
    }

    public void setRetailBankAccount(String retailBankAccount) {
        this.retailBankAccount = retailBankAccount;
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

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractAmt() {
        return contractAmt;
    }

    public void setContractAmt(String contractAmt) {
        this.contractAmt = contractAmt;
    }

    public String getPaiedAmt() {
        return paiedAmt;
    }

    public void setPaiedAmt(String paiedAmt) {
        this.paiedAmt = paiedAmt;
    }

    public String getUnPayAmt() {
        return unPayAmt;
    }

    public void setUnPayAmt(String unPayAmt) {
        this.unPayAmt = unPayAmt;
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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public List<feeOutExpenseList> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<feeOutExpenseList> expenseList) {
        this.expenseList = expenseList;
    }

    @Override
    public String toString() {
        return "feeBaseOutBean{" +
                "number='" + number + '\'' +
                ", pk_oa_head='" + pk_oa_head + '\'' +
                ", applier='" + applier + '\'' +
                ", appDate='" + appDate + '\'' +
                ", payDept='" + payDept + '\'' +
                ", payOrg='" + payOrg + '\'' +
                ", payType='" + payType + '\'' +
                ", recType='" + recType + '\'' +
                ", recSuplier='" + recSuplier + '\'' +
                ", recBank='" + recBank + '\'' +
                ", recBankAccount='" + recBankAccount + '\'' +
                ", retailCus='" + retailCus + '\'' +
                ", retailBank='" + retailBank + '\'' +
                ", retailBankAccount='" + retailBankAccount + '\'' +
                ", auditor='" + auditor + '\'' +
                ", auditTime='" + auditTime + '\'' +
                ", creator='" + creator + '\'' +
                ", originatTime='" + originatTime + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", contractName='" + contractName + '\'' +
                ", contractType='" + contractType + '\'' +
                ", contractAmt='" + contractAmt + '\'' +
                ", paiedAmt='" + paiedAmt + '\'' +
                ", unPayAmt='" + unPayAmt + '\'' +
                ", attendCount='" + attendCount + '\'' +
                ", oaBizType='" + oaBizType + '\'' +
                ", deleted='" + deleted + '\'' +
                ", expenseList=" + expenseList +
                '}';
    }
}
