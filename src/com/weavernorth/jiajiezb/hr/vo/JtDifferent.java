package com.weavernorth.jiajiezb.hr.vo;

public class JtDifferent {
    /**
     * 字段名
     */
    private String fieldId;
    /**
     * 旧值
     */
    private String beforeValue;
    /**
     * 新值
     */
    private String alterValue;

    public JtDifferent() {
    }

    @Override
    public String toString() {
        return "Different{" +
                "fieldId='" + fieldId + '\'' +
                ", beforeValue='" + beforeValue + '\'' +
                ", alterValue='" + alterValue + '\'' +
                '}';
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getBeforeValue() {
        return beforeValue;
    }

    public void setBeforeValue(String beforeValue) {
        this.beforeValue = beforeValue;
    }

    public String getAlterValue() {
        return alterValue;
    }

    public void setAlterValue(String alterValue) {
        this.alterValue = alterValue;
    }
}
