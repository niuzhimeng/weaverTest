package com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean;

public class travelExpenseListBean {
    private String pkOaBody;
    private String expenseType;
    private String purpose;
    private String curDate;
    private Double exchangeRate;
    private String localAmtNoRate;
    private String taxAmt;
    private String localAmt;
    private String amtApprovedNoRateLocal;
    private String approvedTaxAmt;
    private String amtApprovedLocal;
    private String payEntryDept;
    private String payEntryOrg;
    private String endDate;
    private String from;
    private String toArea;
    private String vehicle;
    private String transfer;
    private String transfee;
    private String stayAmt;
    private String otherAmt;

    //新增字段
    private String zhifu;
    private Double budgetAmountOri;

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

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getLocalAmtNoRate() {
        return localAmtNoRate;
    }

    public void setLocalAmtNoRate(String localAmtNoRate) {
        this.localAmtNoRate = localAmtNoRate;
    }

    public String getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(String taxAmt) {
        this.taxAmt = taxAmt;
    }

    public String getLocalAmt() {
        return localAmt;
    }

    public void setLocalAmt(String localAmt) {
        this.localAmt = localAmt;
    }

    public String getAmtApprovedNoRateLocal() {
        return amtApprovedNoRateLocal;
    }

    public void setAmtApprovedNoRateLocal(String amtApprovedNoRateLocal) {
        this.amtApprovedNoRateLocal = amtApprovedNoRateLocal;
    }

    public String getApprovedTaxAmt() {
        return approvedTaxAmt;
    }

    public void setApprovedTaxAmt(String approvedTaxAmt) {
        this.approvedTaxAmt = approvedTaxAmt;
    }

    public String getAmtApprovedLocal() {
        return amtApprovedLocal;
    }

    public void setAmtApprovedLocal(String amtApprovedLocal) {
        this.amtApprovedLocal = amtApprovedLocal;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getToArea() {
        return toArea;
    }

    public void setToArea(String toArea) {
        this.toArea = toArea;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getTransfee() {
        return transfee;
    }

    public void setTransfee(String transfee) {
        this.transfee = transfee;
    }

    public String getStayAmt() {
        return stayAmt;
    }

    public void setStayAmt(String stayAmt) {
        this.stayAmt = stayAmt;
    }

    public String getOtherAmt() {
        return otherAmt;
    }

    public void setOtherAmt(String otherAmt) {
        this.otherAmt = otherAmt;
    }

    public String getZhifu() {
        return zhifu;
    }

    public void setZhifu(String zhifu) {
        this.zhifu = zhifu;
    }

    public Double getBudgetAmountOri() {
        return budgetAmountOri;
    }

    public void setBudgetAmountOri(Double budgetAmountOri) {
        this.budgetAmountOri = budgetAmountOri;
    }

    @Override
    public String toString() {
        return "travelExpenseListBean{" +
                "pkOaBody='" + pkOaBody + '\'' +
                ", expenseType='" + expenseType + '\'' +
                ", purpose='" + purpose + '\'' +
                ", curDate='" + curDate + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", localAmtNoRate='" + localAmtNoRate + '\'' +
                ", taxAmt='" + taxAmt + '\'' +
                ", localAmt='" + localAmt + '\'' +
                ", amtApprovedNoRateLocal='" + amtApprovedNoRateLocal + '\'' +
                ", approvedTaxAmt='" + approvedTaxAmt + '\'' +
                ", amtApprovedLocal='" + amtApprovedLocal + '\'' +
                ", payEntryDept='" + payEntryDept + '\'' +
                ", payEntryOrg='" + payEntryOrg + '\'' +
                ", endDate='" + endDate + '\'' +
                ", from='" + from + '\'' +
                ", toArea='" + toArea + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", transfer='" + transfer + '\'' +
                ", transfee='" + transfee + '\'' +
                ", stayAmt='" + stayAmt + '\'' +
                ", otherAmt='" + otherAmt + '\'' +
                ", zhifu='" + zhifu + '\'' +
                ", budgetAmountOri=" + budgetAmountOri +
                '}';
    }
}
