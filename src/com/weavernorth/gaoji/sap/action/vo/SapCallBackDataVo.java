package com.weavernorth.gaoji.sap.action.vo;

public class SapCallBackDataVo {

    private String ZYFKDH;//SAP付款单据编号
    private String STATUS;//状态
    private String MESSAGE_TEXT;//备注

    public String getZYFKDH() {
        return ZYFKDH;
    }

    public void setZYFKDH(String ZYFKDH) {
        this.ZYFKDH = ZYFKDH;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getMESSAGE_TEXT() {
        return MESSAGE_TEXT;
    }

    public void setMESSAGE_TEXT(String MESSAGE_TEXT) {
        this.MESSAGE_TEXT = MESSAGE_TEXT;
    }
}
