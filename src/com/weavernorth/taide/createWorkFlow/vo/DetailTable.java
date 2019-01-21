package com.weavernorth.taide.createWorkFlow.vo;

public class DetailTable {

    private String tx;

    private String teacher;

    public DetailTable() {
    }

    public DetailTable(String tx, String teacher) {
        this.tx = tx;
        this.teacher = teacher;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
