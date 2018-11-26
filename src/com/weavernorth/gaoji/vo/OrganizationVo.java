package com.weavernorth.gaoji.vo;

/**
 * 组织接收VO
 */
public class OrganizationVo {
    /**
     * 名称
     */
    private String CODEITEMDESC;
    /**
     * 是否门店 1：是  2：否
     */
    private String SFMD;
    /**
     * 编码
     */
    private String CODEITEMID;
    /**
     * 是否行政机构 1：是  2：否
     */
    private String SFXZJG;
    /**
     * 层级
     */
    private String GRADE;
    /**
     * 类型 UN：单位，UM：部门， @K：岗位
     */
    private String CODESETID;
    /**
     * 可用状态 可用：1  非可用：0
     */
    private String STATUS;
    /**
     * 父编码
     */
    private String PARENTID;
    /**
     * HR唯一键
     */
    private String GUIDKEY;

    @Override
    public String toString() {
        return "OrganizationVo{" +
                "CODEITEMDESC='" + CODEITEMDESC + '\'' +
                ", SFMD='" + SFMD + '\'' +
                ", CODEITEMID='" + CODEITEMID + '\'' +
                ", SFXZJG='" + SFXZJG + '\'' +
                ", GRADE='" + GRADE + '\'' +
                ", CODESETID='" + CODESETID + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", PARENTID='" + PARENTID + '\'' +
                ", GUIDKEY='" + GUIDKEY + '\'' +
                '}';
    }

    public String getCODEITEMDESC() {
        return CODEITEMDESC;
    }

    public void setCODEITEMDESC(String CODEITEMDESC) {
        this.CODEITEMDESC = CODEITEMDESC;
    }

    public String getSFMD() {
        return SFMD;
    }

    public void setSFMD(String SFMD) {
        this.SFMD = SFMD;
    }

    public String getCODEITEMID() {
        return CODEITEMID;
    }

    public void setCODEITEMID(String CODEITEMID) {
        this.CODEITEMID = CODEITEMID;
    }

    public String getSFXZJG() {
        return SFXZJG;
    }

    public void setSFXZJG(String SFXZJG) {
        this.SFXZJG = SFXZJG;
    }

    public String getGRADE() {
        return GRADE;
    }

    public void setGRADE(String GRADE) {
        this.GRADE = GRADE;
    }

    public String getCODESETID() {
        return CODESETID;
    }

    public void setCODESETID(String CODESETID) {
        this.CODESETID = CODESETID;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getStatusChange() {
        if("0".equals(STATUS.trim())){
            return "1";
        }else {
            return "0";
        }
    }

    public String getPARENTID() {
        return PARENTID;
    }

    public void setPARENTID(String PARENTID) {
        this.PARENTID = PARENTID;
    }

    public String getGUIDKEY() {
        return GUIDKEY;
    }

    public void setGUIDKEY(String GUIDKEY) {
        this.GUIDKEY = GUIDKEY;
    }
}
