package com.weavernorth.taide.createWorkFlow.vo;

public class MainTalbe {
    private String name;

    private String age;

    private DetailTable[] detailTables;

    public DetailTable[] getDetailTables() {
        return detailTables;
    }

    public void setDetailTables(DetailTable[] detailTables) {
        this.detailTables = detailTables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
