package com.weavernorth.dadiyingyuan.travelExpenseReimBursement.travelExpenseReimBean;

public class travelRecListBean {
    private String payEntryType;
    private String recPerson;
    private String recAmt;
    private String recBankAccount;
    private String bank;
    private String recCity;

    public String getPayEntryType() {
        return payEntryType;
    }

    public void setPayEntryType(String payEntryType) {
        this.payEntryType = payEntryType;
    }

    public String getRecPerson() {
        return recPerson;
    }

    public void setRecPerson(String recPerson) {
        this.recPerson = recPerson;
    }

    public String getRecAmt() {
        return recAmt;
    }

    public void setRecAmt(String recAmt) {
        this.recAmt = recAmt;
    }

    public String getRecBankAccount() {
        return recBankAccount;
    }

    public void setRecBankAccount(String recBankAccount) {
        this.recBankAccount = recBankAccount;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getRecCity() {
        return recCity;
    }

    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }

    @Override
    public String toString() {
        return "travelRecListBean{" +
                "payEntryType='" + payEntryType + '\'' +
                ", recPerson='" + recPerson + '\'' +
                ", recAmt=" + recAmt +
                ", recBankAccount='" + recBankAccount + '\'' +
                ", bank='" + bank + '\'' +
                ", recCity='" + recCity + '\'' +
                '}';
    }
}
