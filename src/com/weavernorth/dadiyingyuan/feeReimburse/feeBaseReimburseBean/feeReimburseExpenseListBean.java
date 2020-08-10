package com.weavernorth.dadiyingyuan.feeReimburse.feeBaseReimburseBean;

public class feeReimburseExpenseListBean {
    private String pkOaBody;
    private String expenseType;
    private String purpose;
    private String curDate;
    private String currency;
    private Double exchangeRate;
    private String localAmtNoRate;
    private String rate;
    private String taxAmt;
    private String localAmt;
    private String amtApprovedNoRateLocal;
    private String approvedTaxAmt;
    private String amtApprovedLocal;
    private String payEntryDept;
    private String payEntryOrg;
    //新增字段
    private String feiyong;
    private String jiaona;
    private String zhifu;
    private String zaijian;
    private String product;//product	产品档案
    private String yajin;//yajin	押金性质（参照辅助核算）
    private double budgetAmountOri;
    //2019.4.4新增
    private String customer;// 客户
    private String supplier;// 供应商
    public String getZaijian() {
        return zaijian;
    }

    public void setZaijian(String zaijian) {
        this.zaijian = zaijian;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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

    public String getFeiyong() {
        return feiyong;
    }

    public void setFeiyong(String feiyong) {
        this.feiyong = feiyong;
    }

    public String getJiaona() {
        return jiaona;
    }

    public void setJiaona(String jiaona) {
        this.jiaona = jiaona;
    }

    public String getZhifu() {
        return zhifu;
    }

    public void setZhifu(String zhifu) {
        this.zhifu = zhifu;
    }

    public double getBudgetAmountOri() {
        return budgetAmountOri;
    }

    public void setBudgetAmountOri(double budgetAmountOri) {
        this.budgetAmountOri = budgetAmountOri;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getYajin() {
        return yajin;
    }

    public void setYajin(String yajin) {
        this.yajin = yajin;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "feeReimburseExpenseListBean{" +
                "pkOaBody='" + pkOaBody + '\'' +
                ", expenseType='" + expenseType + '\'' +
                ", purpose='" + purpose + '\'' +
                ", curDate='" + curDate + '\'' +
                ", currency='" + currency + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", localAmtNoRate='" + localAmtNoRate + '\'' +
                ", rate='" + rate + '\'' +
                ", taxAmt='" + taxAmt + '\'' +
                ", localAmt='" + localAmt + '\'' +
                ", amtApprovedNoRateLocal='" + amtApprovedNoRateLocal + '\'' +
                ", approvedTaxAmt='" + approvedTaxAmt + '\'' +
                ", amtApprovedLocal='" + amtApprovedLocal + '\'' +
                ", payEntryDept='" + payEntryDept + '\'' +
                ", payEntryOrg='" + payEntryOrg + '\'' +
                ", feiyong='" + feiyong + '\'' +
                ", jiaona='" + jiaona + '\'' +
                ", zhifu='" + zhifu + '\'' +
                ", zaijian='" + zaijian + '\'' +
                ", budgetAmountOri=" + budgetAmountOri +
                '}';
    }
}
