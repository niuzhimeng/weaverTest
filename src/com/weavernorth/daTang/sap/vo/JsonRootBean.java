package com.weavernorth.daTang.sap.vo;

import java.util.List;

public class JsonRootBean {

    private String ReferenceDate;
    private String Memo;
    private String TaxDate;
    private String DueDate;
    private List<JournalEntryLines> JournalEntryLines;

    public String getReferenceDate() {
        return ReferenceDate;
    }

    public void setReferenceDate(String referenceDate) {
        ReferenceDate = referenceDate;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    public String getTaxDate() {
        return TaxDate;
    }

    public void setTaxDate(String taxDate) {
        TaxDate = taxDate;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public List<com.weavernorth.daTang.sap.vo.JournalEntryLines> getJournalEntryLines() {
        return JournalEntryLines;
    }

    public void setJournalEntryLines(List<com.weavernorth.daTang.sap.vo.JournalEntryLines> journalEntryLines) {
        JournalEntryLines = journalEntryLines;
    }
}
