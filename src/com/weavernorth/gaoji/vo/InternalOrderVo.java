package com.weavernorth.gaoji.vo;

/**
 * 内部订单vo
 */
public class InternalOrderVo {
    /**
     * 内部订单号
     */
    private String AUFNR;
    /**
     * 内部订单描述
     */
    private String KTEXT;
    /**
     * 订单类型
     */
    private String AUART;
    /**
     * 成本控制范围
     */
    private String KOKRS;
    /**
     * 公司代码
     */
    private String BUKRS;
    /**
     * 对象类
     */
    private String SCOPE;
    /**
     * 利润中心
     */
    private String PRCTR;
    /**
     * 统计订单
     */
    private String ASTKZ;

    public String getAUFNR() {
        return AUFNR;
    }

    public void setAUFNR(String AUFNR) {
        this.AUFNR = AUFNR;
    }

    public String getKTEXT() {
        return KTEXT;
    }

    public void setKTEXT(String KTEXT) {
        this.KTEXT = KTEXT;
    }

    public String getAUART() {
        return AUART;
    }

    public void setAUART(String AUART) {
        this.AUART = AUART;
    }

    public String getKOKRS() {
        return KOKRS;
    }

    public void setKOKRS(String KOKRS) {
        this.KOKRS = KOKRS;
    }

    public String getBUKRS() {
        return BUKRS;
    }

    public void setBUKRS(String BUKRS) {
        this.BUKRS = BUKRS;
    }

    public String getSCOPE() {
        return SCOPE;
    }

    public void setSCOPE(String SCOPE) {
        this.SCOPE = SCOPE;
    }

    public String getPRCTR() {
        return PRCTR;
    }

    public void setPRCTR(String PRCTR) {
        this.PRCTR = PRCTR;
    }

    public String getASTKZ() {
        return ASTKZ;
    }

    public void setASTKZ(String ASTKZ) {
        this.ASTKZ = ASTKZ;
    }

    @Override
    public String toString() {
        return "InternalOrderVo{" +
                "AUFNR='" + AUFNR + '\'' +
                ", KTEXT='" + KTEXT + '\'' +
                ", AUART='" + AUART + '\'' +
                ", KOKRS='" + KOKRS + '\'' +
                ", BUKRS='" + BUKRS + '\'' +
                ", SCOPE='" + SCOPE + '\'' +
                ", PRCTR='" + PRCTR + '\'' +
                ", ASTKZ='" + ASTKZ + '\'' +
                '}';
    }
}
