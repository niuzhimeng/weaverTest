package com.weavernorth.dadiyingyuan.brrowFeeForm.brrowFeeFormBean;

public class feeRecList {
    private String payEntryType;
    private String recPerson;
    private String recCity;
    private String recBankAccount;
    private String bank;
    private String recAmt;

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

    public String getRecCity() {
        return recCity;
    }

    public void setRecCity(String recCity) {
        this.recCity = recCity;
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

    public String getRecAmt() {
        return recAmt;
    }

    public void setRecAmt(String recAmt) {
        this.recAmt = recAmt;
    }

    @Override
    public String toString() {
        return "feeRecList{" +
                "payEntryType='" + payEntryType + '\'' +
                ", recPerson='" + recPerson + '\'' +
                ", recCity='" + recCity + '\'' +
                ", recBankAccount='" + recBankAccount + '\'' +
                ", bank='" + bank + '\'' +
                ", recAmt=" + recAmt +
                '}';
    }
}
