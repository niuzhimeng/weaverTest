package com.weavernorth.dadiyingyuan.feeOutForm.feeOutBean;

public class feeOutExpenseList {
    private String rate;
    private String pkOaBody;
    private String expenseType;
    private String purpose;
    private String billDate;
    private Double exchangeRate;
    private String localAmtNoRate;
    private String rateAmt;
    private String localAmt;
    private String amtApprovedNoRateLocal;
    private String approvedTaxAmt;
    private String amtApprovedLocal;
    private String payEntryDept;
    private String payEntryOrg;
    private String accItem;

    //新增
    private String jiaona;//缴纳地
    private Boolean dishui;//dishui	是否抵扣（是/否）
    private String shuifei;//shuifei	科目（参照科目档案）
    private String zaijian;//zaijian  在建影院（参照辅助核算）
    private String yajin;//yajin	押金性质（参照辅助核算）
    private String gongcheng;//工程项目（参照辅助核算）
    private String feiyong;//feiyong	费用项目（参照辅助核算）
    private String zhifu;//zhifu	支付款类型
    private String product;//product	产品档案


    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
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

    public String getRateAmt() {
        return rateAmt;
    }

    public void setRateAmt(String rateAmt) {
        this.rateAmt = rateAmt;
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

    public String getAccItem() {
        return accItem;
    }

    public void setAccItem(String accItem) {
        this.accItem = accItem;
    }

    public String getJiaona() {
        return jiaona;
    }

    public void setJiaona(String jiaona) {
        this.jiaona = jiaona;
    }

    public Boolean getDishui() {
        return dishui;
    }

    public void setDishui(Boolean dishui) {
        this.dishui = dishui;
    }

    public String getShuifei() {
        return shuifei;
    }

    public void setShuifei(String shuifei) {
        this.shuifei = shuifei;
    }

    public String getZaijian() {
        return zaijian;
    }

    public void setZaijian(String zaijian) {
        this.zaijian = zaijian;
    }

    public String getYajin() {
        return yajin;
    }

    public void setYajin(String yajin) {
        this.yajin = yajin;
    }

    public String getGongcheng() {
        return gongcheng;
    }

    public void setGongcheng(String gongcheng) {
        this.gongcheng = gongcheng;
    }

    public String getFeiyong() {
        return feiyong;
    }

    public void setFeiyong(String feiyong) {
        this.feiyong = feiyong;
    }

    public String getZhifu() {
        return zhifu;
    }

    public void setZhifu(String zhifu) {
        this.zhifu = zhifu;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "feeOutExpenseList{" +
                "rate=" + rate +
                ", pkOaBody='" + pkOaBody + '\'' +
                ", expenseType='" + expenseType + '\'' +
                ", purpose='" + purpose + '\'' +
                ", billDate='" + billDate + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", localAmtNoRate='" + localAmtNoRate + '\'' +
                ", rateAmt='" + rateAmt + '\'' +
                ", localAmt='" + localAmt + '\'' +
                ", amtApprovedNoRateLocal='" + amtApprovedNoRateLocal + '\'' +
                ", approvedTaxAmt='" + approvedTaxAmt + '\'' +
                ", amtApprovedLocal='" + amtApprovedLocal + '\'' +
                ", payEntryDept='" + payEntryDept + '\'' +
                ", payEntryOrg='" + payEntryOrg + '\'' +
                ", accItem='" + accItem + '\'' +
                ", jiaona='" + jiaona + '\'' +
                ", dishui=" + dishui +
                ", shuifei='" + shuifei + '\'' +
                ", zaijian='" + zaijian + '\'' +
                ", yajin='" + yajin + '\'' +
                ", gongcheng='" + gongcheng + '\'' +
                ", feiyong='" + feiyong + '\'' +
                ", zhifu='" + zhifu + '\'' +
                '}';
    }
}
