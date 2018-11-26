package com.weavernorth.daTang.vo;

public class NameAndValue {
    /**
     * 人员id
     */
    private Integer nameId;
    /**
     * 工时
     */
    private Double gs;

    @Override
    public String toString() {
        return "NameAndValue{" +
                "nameId=" + nameId +
                ", gs=" + gs +
                '}';
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }

    public Double getGs() {
        return gs;
    }

    public void setGs(Double gs) {
        this.gs = gs;
    }
}
