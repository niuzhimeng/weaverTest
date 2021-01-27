package com.weavernorth.zhongsha21.openweb.vo;

/**
 * 明细表实体类
 */
public class DetailTable {

    private String ZOAID; // OA单号
    private String ZDEPT; // 部门
    private String ZANLF; // 业务类型
    private String ZHTBH; // 合同编号
    private String ZPAYD; // 付款日期

    private String KOINH; // 供应商名称
    private String ZJJSX; // 经济事项
    private String ZPAYN; // 应付金额
    private String WAERS; // 货币单位

    public String getZOAID() {
        return ZOAID;
    }

    public void setZOAID(String ZOAID) {
        this.ZOAID = ZOAID;
    }

    public String getZDEPT() {
        return ZDEPT;
    }

    public void setZDEPT(String ZDEPT) {
        this.ZDEPT = ZDEPT;
    }

    public String getZANLF() {
        return ZANLF;
    }

    public void setZANLF(String ZANLF) {
        this.ZANLF = ZANLF;
    }

    public String getZHTBH() {
        return ZHTBH;
    }

    public void setZHTBH(String ZHTBH) {
        this.ZHTBH = ZHTBH;
    }

    public String getZPAYD() {
        return ZPAYD;
    }

    public void setZPAYD(String ZPAYD) {
        this.ZPAYD = ZPAYD;
    }

    public String getKOINH() {
        return KOINH;
    }

    public void setKOINH(String KOINH) {
        this.KOINH = KOINH;
    }

    public String getZJJSX() {
        return ZJJSX;
    }

    public void setZJJSX(String ZJJSX) {
        this.ZJJSX = ZJJSX;
    }

    public String getZPAYN() {
        return ZPAYN;
    }

    public void setZPAYN(String ZPAYN) {
        this.ZPAYN = ZPAYN;
    }

    public String getWAERS() {
        return WAERS;
    }

    public void setWAERS(String WAERS) {
        this.WAERS = WAERS;
    }
}
