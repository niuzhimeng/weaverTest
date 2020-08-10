package com.weavernorth.dadiyingyuan.feeReimburse.feeBaseReimburseBean;

public class feeReimburseRevLoanBean {
    private String loanNumber;
    private String pkOaHead;
    private String pkOaBody;
    private String amount;
    private String loanDate;
    private String loanReason;
    private String expenseType;
    private String balance;

    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getPkOaHead() {
        return pkOaHead;
    }

    public void setPkOaHead(String pkOaHead) {
        this.pkOaHead = pkOaHead;
    }

    public String getPkOaBody() {
        return pkOaBody;
    }

    public void setPkOaBody(String pkOaBody) {
        this.pkOaBody = pkOaBody;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanReason() {
        return loanReason;
    }

    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "feeReimburseRevLoanBean{" +
                "loanNumber='" + loanNumber + '\'' +
                ", pkOaHead='" + pkOaHead + '\'' +
                ", pkOaBody='" + pkOaBody + '\'' +
                ", amount=" + amount +
                ", loanDate='" + loanDate + '\'' +
                ", loanReason='" + loanReason + '\'' +
                ", expenseType='" + expenseType + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
