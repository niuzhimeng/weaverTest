package com.weavernorth.gaoji.vo;

/**
 * 成本中心vo
 */
public class CostCenterVo {
    /**
     * 编码
     */
    private String CODE;
    /**
     * 父编码
     */
    private String COMPANYCODE;
    /**
     * 机构全称
     */
    private String FULLNAME;
    /**
     * 是否是门店
     */
    private String SFMD;
    /**
     * 可用状态
     */
    private String STATUS;// 1：可用  0：不可用
    /**
     * HR唯一键
     */
    private String GUIDKEY;

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getCOMPANYCODE() {
        return COMPANYCODE;
    }

    public void setCOMPANYCODE(String COMPANYCODE) {
        this.COMPANYCODE = COMPANYCODE;
    }

    public String getFULLNAME() {
        return FULLNAME;
    }

    public void setFULLNAME(String FULLNAME) {
        this.FULLNAME = FULLNAME;
    }

    public String getSFMD() {
        return SFMD;
    }

    public void setSFMD(String SFMD) {
        this.SFMD = SFMD;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getGUIDKEY() {
        return GUIDKEY;
    }

    public void setGUIDKEY(String GUIDKEY) {
        this.GUIDKEY = GUIDKEY;
    }

    @Override
    public String toString() {
        return "CostCenterVo{" +
                "CODE='" + CODE + '\'' +
                ", COMPANYCODE='" + COMPANYCODE + '\'' +
                ", FULLNAME='" + FULLNAME + '\'' +
                ", SFMD='" + SFMD + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", GUIDKEY='" + GUIDKEY + '\'' +
                '}';
    }
}
