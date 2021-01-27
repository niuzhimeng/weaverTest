package com.weavernorth.zhongsha21.openweb.vo;

/**
 * 交易结果VO
 */
public class TradeResultVO {
    private String ZOAID; // 交易编号
    private String BUKRS; // 公司代码
    private String ZFKZT; // 付款状态

    public String getZOAID() {
        return ZOAID;
    }

    public void setZOAID(String ZOAID) {
        this.ZOAID = ZOAID;
    }

    public String getBUKRS() {
        return BUKRS;
    }

    public void setBUKRS(String BUKRS) {
        this.BUKRS = BUKRS;
    }

    public String getZFKZT() {
        return ZFKZT;
    }

    public void setZFKZT(String ZFKZT) {
        this.ZFKZT = ZFKZT;
    }
}
