package com.weavernorth.taide.createWorkFlow.vo;

/**
 * 明细表实体类
 */
public class DetailTable {

    private String BANFN; // 采购申请号

    private String BNFPO; // 行项目

    private String KNTTP; // 科目分配类别

    private String MATNR; // 物料编码

    private String WERKS; // 工厂

    private String MENGE; // 申请数量

    private String MEINS; // 基本计量单位

    private String AFNAM; // 申请人

    private String TEXT01; // 需求项目文本

    private String TEXT02; // 研发项目文本

    private String LFDAT; // 需求日期

    private String LIFNR; // 所需供应商

    public String getAFNAM() {
        return AFNAM;
    }

    public void setAFNAM(String AFNAM) {
        this.AFNAM = AFNAM;
    }

    public String getBANFN() {
        return BANFN;
    }

    public void setBANFN(String BANFN) {
        this.BANFN = BANFN;
    }

    public String getBNFPO() {
        return BNFPO;
    }

    public void setBNFPO(String BNFPO) {
        this.BNFPO = BNFPO;
    }

    public String getKNTTP() {
        return KNTTP;
    }

    public void setKNTTP(String KNTTP) {
        this.KNTTP = KNTTP;
    }

    public String getMATNR() {
        return MATNR;
    }

    public void setMATNR(String MATNR) {
        this.MATNR = MATNR;
    }

    public String getWERKS() {
        return WERKS;
    }

    public void setWERKS(String WERKS) {
        this.WERKS = WERKS;
    }

    public String getMENGE() {
        return MENGE;
    }

    public void setMENGE(String MENGE) {
        this.MENGE = MENGE;
    }

    public String getMEINS() {
        return MEINS;
    }

    public void setMEINS(String MEINS) {
        this.MEINS = MEINS;
    }

    public String getTEXT01() {
        return TEXT01;
    }

    public void setTEXT01(String TEXT01) {
        this.TEXT01 = TEXT01;
    }

    public String getTEXT02() {
        return TEXT02;
    }

    public void setTEXT02(String TEXT02) {
        this.TEXT02 = TEXT02;
    }

    public String getLFDAT() {
        return LFDAT;
    }

    public void setLFDAT(String LFDAT) {
        this.LFDAT = LFDAT;
    }

    public String getLIFNR() {
        return LIFNR;
    }

    public void setLIFNR(String LIFNR) {
        this.LIFNR = LIFNR;
    }
}
