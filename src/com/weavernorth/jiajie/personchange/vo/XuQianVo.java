package com.weavernorth.jiajie.personchange.vo;

/**
 * 续签流程 - 字段对比vo
 */
public class XuQianVo {

    private String frt;

    private String startDate;

    private String endDate;

    @Override
    public String toString() {
        return "XuQianVo{" +
                "frt='" + frt + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public String getFrt() {
        return frt;
    }

    public void setFrt(String frt) {
        this.frt = frt;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
