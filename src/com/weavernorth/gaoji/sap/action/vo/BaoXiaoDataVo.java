package com.weavernorth.gaoji.sap.action.vo;

import java.util.List;

public class BaoXiaoDataVo {

    private String BUKRS;//费用承担主体
    private String PERNR;//员工编号
    private String NAME1;//员工姓名
    private String HBKID;//收款银行
    private String BANKN;//银行账号

    private String ZTYPE;//类型
    private String KOSTL;//费用承担部门
    private String WAERS;//货币
    private String ZFYLX;//费用类型
    private String ZJK;//借款余额

    private String ZBX;//报销金额（费用金额合计）
    private String ZSE;//税额（增值税税额）
    private String WRBTR;//支付金额
    private String SGTXT;//发票号
    private String ZDJBH;//单据编号
    private String ZOAID;//OA的requestId

    private String ZDJZT;//单据状态
    private String AUFNR;//内部订单
    private String ZFKFS;//付款方式
    private String ZTBNA;//流程表表名

    private List<BaoXiaoDetailVo> DETAILVOLIST;

    public List<BaoXiaoDetailVo> getDETAILVOLIST() {
        return DETAILVOLIST;
    }

    public void setDETAILVOLIST(List<BaoXiaoDetailVo> DETAILVOLIST) {
        this.DETAILVOLIST = DETAILVOLIST;
    }

    public String getZJK() {
        return ZJK;
    }

    public void setZJK(String ZJK) {
        this.ZJK = ZJK;
    }

    public String getWRBTR() {
        return WRBTR;
    }

    public void setWRBTR(String WRBTR) {
        this.WRBTR = WRBTR;
    }

    public String getAUFNR() {
        return AUFNR;
    }

    public void setAUFNR(String AUFNR) {
        this.AUFNR = AUFNR;
    }

    public String getZFKFS() {
        return ZFKFS;
    }

    public void setZFKFS(String ZFKFS) {
        this.ZFKFS = ZFKFS;
    }

    public String getBUKRS() {
        return BUKRS;
    }

    public void setBUKRS(String BUKRS) {
        this.BUKRS = BUKRS;
    }

    public String getPERNR() {
        return PERNR;
    }

    public void setPERNR(String PERNR) {
        this.PERNR = PERNR;
    }

    public String getNAME1() {
        return NAME1;
    }

    public void setNAME1(String NAME1) {
        this.NAME1 = NAME1;
    }

    public String getHBKID() {
        return HBKID;
    }

    public void setHBKID(String HBKID) {
        this.HBKID = HBKID;
    }

    public String getBANKN() {
        return BANKN;
    }

    public void setBANKN(String BANKN) {
        this.BANKN = BANKN;
    }

    public String getZTYPE() {
        return ZTYPE;
    }

    public void setZTYPE(String ZTYPE) {
        this.ZTYPE = ZTYPE;
    }

    public String getKOSTL() {
        return KOSTL;
    }

    public void setKOSTL(String KOSTL) {
        this.KOSTL = KOSTL;
    }

    public String getWAERS() {
        return WAERS;
    }

    public void setWAERS(String WAERS) {
        this.WAERS = WAERS;
    }

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

    public String getZDJZT() {
        return ZDJZT;
    }

    public String getZOAID() {
        return ZOAID;
    }

    public void setZOAID(String ZOAID) {
        this.ZOAID = ZOAID;
    }

    public void setZDJZT(String ZDJZT) {
        this.ZDJZT = ZDJZT;
    }

    public String getZTBNA() {
        return ZTBNA;
    }

    public void setZTBNA(String ZTBNA) {
        this.ZTBNA = ZTBNA;
    }
}
