package com.weavernorth.gaoji.sap.action.vo;

/**
 * 明细表实体
 */
public class BaoXiaoDetailVo {

    private String ZDJBH;//单据编号
    private String ZFYLX;//费用类型
    private String ZBX;//报销金额
    private String ZSE;//税额（增值税税额）
    private String WRBTR;//借款金额
    private String SGTXT;//发票号
    private String WAERS;//货币
    private String AUFNR;//费用承担项目

    public String getZFYLX() {
        return ZFYLX;
    }

    public void setZFYLX(String ZFYLX) {
        this.ZFYLX = ZFYLX;
    }

    public String getZBX() {
        return ZBX;
    }

    public void setZBX(String ZBX) {
        this.ZBX = ZBX;
    }

    public String getZSE() {
        return ZSE;
    }

    public void setZSE(String ZSE) {
        this.ZSE = ZSE;
    }

    public String getWRBTR() {
        return WRBTR;
    }

    public void setWRBTR(String WRBTR) {
        this.WRBTR = WRBTR;
    }

    public String getSGTXT() {
        return SGTXT;
    }

    public void setSGTXT(String SGTXT) {
        this.SGTXT = SGTXT;
    }

    public String getZDJBH() {
        return ZDJBH;
    }

    public void setZDJBH(String ZDJBH) {
        this.ZDJBH = ZDJBH;
    }

    public String getWAERS() {
        return WAERS;
    }

    public void setWAERS(String WAERS) {
        this.WAERS = WAERS;
    }

    public String getAUFNR() {
        return AUFNR;
    }

    public void setAUFNR(String AUFNR) {
        this.AUFNR = AUFNR;
    }
}
