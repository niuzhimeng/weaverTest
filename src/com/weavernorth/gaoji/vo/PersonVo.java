package com.weavernorth.gaoji.vo;

/**
 * 人员接收vo
 */
public class PersonVo {
    /**
     * 员工编号
     */
    private String STAFFNUMBER;
    /**
     * 员工姓名
     */
    private String LASTNAME;
    /**
     * 员工性别
     */
    private String SEX;
    /**
     * 系统登录号
     */
    private String LOGINID;
    /**
     * 身份证号
     */
    private String IDCARD;
    /**
     * 手机号码
     */
    private String PHONE;
    /**
     * 状态
     */
    private String STATUS;
    /**
     * 工作地点
     */
    private String LOCATION;
    /**
     * 邮箱
     */
    private String EMAIL;
    /**
     * 直接上级
     */
    private String DIRECTLEADER;
    /**
     * 职位名称
     */
    private String JOBACTIVITIENAME;
    /**
     * 职位等级
     */
    private String JOBLEVEL;
    /**
     * 年假结余
     */
    private String ANNUALLEAVE;
    /**
     * 行政管理的 单位
     */
    private String COMPANY;
    /**
     * 安全等级
     */
    private String SECLEVEL;
    /**
     * 入职时间
     */
    private String ENTRYDATE;
    /**
     * 合同生效时间
     */
    private String DATEFIELD;
    /**
     * 合同结束时间
     */
    private String ENDDATE;
    /**
     * 合同主体（合同签订单位）
     */
    private String LABORRELATION;
    /**
     * 银行账户
     */
    private String BANKACCOUNT;
    /**
     * 开户城市
     */
    private String ACCOUNTCITY;
    /**
     * 开户行
     */
    private String OPENINGBANK;
    /**
     * 支行信息
     */
    private String SUBBRANCHMESS;
    /**
     * 所属部门编号
     */
    private String DEPCODE;
    /**
     * 岗位编码
     */
    private String JOBTITLECODE;
    /**
     * 成本中心对应SAP 
     */
    private String SAPCOST;
    /**
     * HR唯一键
     */
    private String GUIDKEY;

    @Override
    public String toString() {
        return "PersonVo{" +
                "STAFFNUMBER='" + STAFFNUMBER + '\'' +
                ", LASTNAME='" + LASTNAME + '\'' +
                ", SEX='" + SEX + '\'' +
                ", LOGINID='" + LOGINID + '\'' +
                ", IDCARD='" + IDCARD + '\'' +
                ", PHONE='" + PHONE + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", LOCATION='" + LOCATION + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", DIRECTLEADER='" + DIRECTLEADER + '\'' +
                ", JOBACTIVITIENAME='" + JOBACTIVITIENAME + '\'' +
                ", JOBLEVEL='" + JOBLEVEL + '\'' +
                ", ANNUALLEAVE='" + ANNUALLEAVE + '\'' +
                ", COMPANY='" + COMPANY + '\'' +
                ", SECLEVEL='" + SECLEVEL + '\'' +
                ", ENTRYDATE='" + ENTRYDATE + '\'' +
                ", DATEFIELD='" + DATEFIELD + '\'' +
                ", ENDDATE='" + ENDDATE + '\'' +
                ", LABORRELATION='" + LABORRELATION + '\'' +
                ", BANKACCOUNT='" + BANKACCOUNT + '\'' +
                ", ACCOUNTCITY='" + ACCOUNTCITY + '\'' +
                ", OPENINGBANK='" + OPENINGBANK + '\'' +
                ", SUBBRANCHMESS='" + SUBBRANCHMESS + '\'' +
                ", DEPCODE='" + DEPCODE + '\'' +
                ", JOBTITLECODE='" + JOBTITLECODE + '\'' +
                ", SAPCOST='" + SAPCOST + '\'' +
                ", GUIDKEY='" + GUIDKEY + '\'' +
                '}';
    }

    public String getSTAFFNUMBER() {
        return STAFFNUMBER;
    }

    public void setSTAFFNUMBER(String STAFFNUMBER) {
        this.STAFFNUMBER = STAFFNUMBER;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getLOGINID() {
        return LOGINID;
    }

    public void setLOGINID(String LOGINID) {
        this.LOGINID = LOGINID;
    }

    public String getIDCARD() {
        return IDCARD;
    }

    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDIRECTLEADER() {
        return DIRECTLEADER;
    }

    public void setDIRECTLEADER(String DIRECTLEADER) {
        this.DIRECTLEADER = DIRECTLEADER;
    }

    public String getJOBACTIVITIENAME() {
        return JOBACTIVITIENAME;
    }

    public void setJOBACTIVITIENAME(String JOBACTIVITIENAME) {
        this.JOBACTIVITIENAME = JOBACTIVITIENAME;
    }

    public String getJOBLEVEL() {
        return JOBLEVEL;
    }

    public void setJOBLEVEL(String JOBLEVEL) {
        this.JOBLEVEL = JOBLEVEL;
    }

    public String getANNUALLEAVE() {
        return ANNUALLEAVE;
    }

    public void setANNUALLEAVE(String ANNUALLEAVE) {
        this.ANNUALLEAVE = ANNUALLEAVE;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getSECLEVEL() {
        return SECLEVEL;
    }

    public void setSECLEVEL(String SECLEVEL) {
        this.SECLEVEL = SECLEVEL;
    }

    public String getENTRYDATE() {
        return ENTRYDATE;
    }

    public String getENTRYDATEChange() {
        if (ENTRYDATE != null && ENTRYDATE.length() > 0) {
            return ENTRYDATE.substring(0, 10);
        }
        return "";
    }

    public void setENTRYDATE(String ENTRYDATE) {
        this.ENTRYDATE = ENTRYDATE;
    }

    public String getDATEFIELD() {
        return DATEFIELD;
    }

    public String getDATEFIELDChange(){
        if (DATEFIELD != null && DATEFIELD.length() > 0) {
            return DATEFIELD.substring(0, 10);
        }
        return "";
    }


    public void setDATEFIELD(String DATEFIELD) {
        this.DATEFIELD = DATEFIELD;
    }

    public String getENDDATE() {
        return ENDDATE;
    }

    public String getENDDATEChange(){
        if (ENDDATE != null && ENDDATE.length() > 0) {
            return ENDDATE.substring(0, 10);
        }
        return "";
    }


    public void setENDDATE(String ENDDATE) {
        this.ENDDATE = ENDDATE;
    }

    public String getLABORRELATION() {
        return LABORRELATION;
    }

    public void setLABORRELATION(String LABORRELATION) {
        this.LABORRELATION = LABORRELATION;
    }

    public String getBANKACCOUNT() {
        return BANKACCOUNT;
    }

    public void setBANKACCOUNT(String BANKACCOUNT) {
        this.BANKACCOUNT = BANKACCOUNT;
    }

    public String getACCOUNTCITY() {
        return ACCOUNTCITY;
    }

    public void setACCOUNTCITY(String ACCOUNTCITY) {
        this.ACCOUNTCITY = ACCOUNTCITY;
    }

    public String getOPENINGBANK() {
        return OPENINGBANK;
    }

    public void setOPENINGBANK(String OPENINGBANK) {
        this.OPENINGBANK = OPENINGBANK;
    }

    public String getSUBBRANCHMESS() {
        return SUBBRANCHMESS;
    }

    public void setSUBBRANCHMESS(String SUBBRANCHMESS) {
        this.SUBBRANCHMESS = SUBBRANCHMESS;
    }

    public String getDEPCODE() {
        return DEPCODE;
    }

    public void setDEPCODE(String DEPCODE) {
        this.DEPCODE = DEPCODE;
    }

    public String getJOBTITLECODE() {
        return JOBTITLECODE;
    }

    public void setJOBTITLECODE(String JOBTITLECODE) {
        this.JOBTITLECODE = JOBTITLECODE;
    }

    public String getSAPCOST() {
        return SAPCOST;
    }

    public void setSAPCOST(String SAPCOST) {
        this.SAPCOST = SAPCOST;
    }

    public String getGUIDKEY() {
        return GUIDKEY;
    }

    public void setGUIDKEY(String GUIDKEY) {
        this.GUIDKEY = GUIDKEY;
    }
}
