package com.test.webserviceTest.vo;

public enum  MyEnum {
//
    STATUS_FORMAL_USE("正常在用"),
    STATUS_PHONE_STOP("停机"),
    STATUS_ONLINE_NOT_USE("在网但不可用"),
    STATUS_OFF_LINE("不在网"),
    STATUS_LOG_OUT("销号");

    private String status;

    MyEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
