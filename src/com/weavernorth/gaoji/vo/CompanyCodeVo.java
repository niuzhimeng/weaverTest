package com.weavernorth.gaoji.vo;

/**
 * 公司代码vo
 */
public class CompanyCodeVo {
    /**
     * 公司代码
     */
    private String CODE;
    /**
     * 公司名称
     */
    private String NAME;
    /**
     * 可用状态 1：可用  0：不可用
     */
    private String STATUS;

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public String toString() {
        return "CompanyCodeVo{" +
                "CODE='" + CODE + '\'' +
                ", NAME='" + NAME + '\'' +
                ", STATUS='" + STATUS + '\'' +
                '}';
    }
}
