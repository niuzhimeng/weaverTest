package com.weavernorth.zhouji.vo;

public class ProjectVo {
    /**
     * 项目编码
     */
    private String xmbm;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 科目编码
     */
    private String kmbm;
    /**
     * 科目名称
     */
    private String kmmc;
    /**
     * 合同号
     */
    private String hth;
    /**
     * 金额
     */
    private String je;
    /**
     * 模块id
     */
    private String modeId;
    /**
     * 项目层级
     */
    private Integer xmcj;
    /**
     * 主表更新sql
     */
    private String mainUpdateSql;

    /**
     * 主表插入sql
     */
    private String mainInsertSql;

    /**
     * 所属一级项目名称
     */
    private String ssyjxmmc;
    /**
     * 所属一级项目编码
     */
    private String ssyjxmbm;

    /**
     * 所属二级项目名称
     */
    private String ssejxmmc;
    /**
     * 所属二级项目编码
     */
    private String ssejxmbm;

    /**
     * 参数数组
     */
    private Object[] args;

    @Override
    public String toString() {
        return "ProjectVo{" +
                "xmbm='" + xmbm + '\'' +
                ", xmmc='" + xmmc + '\'' +
                ", kmbm='" + kmbm + '\'' +
                ", kmmc='" + kmmc + '\'' +
                ", hth='" + hth + '\'' +
                ", je='" + je + '\'' +
                ", modeId='" + modeId + '\'' +
                '}';
    }

    public String getSsejxmmc() {
        return ssejxmmc;
    }

    public void setSsejxmmc(String ssejxmmc) {
        this.ssejxmmc = ssejxmmc;
    }

    public String getSsejxmbm() {
        return ssejxmbm;
    }

    public void setSsejxmbm(String ssejxmbm) {
        this.ssejxmbm = ssejxmbm;
    }

    public String getSsyjxmmc() {
        return ssyjxmmc;
    }

    public void setSsyjxmmc(String ssyjxmmc) {
        this.ssyjxmmc = ssyjxmmc;
    }

    public String getSsyjxmbm() {
        return ssyjxmbm;
    }

    public void setSsyjxmbm(String ssyjxmbm) {
        this.ssyjxmbm = ssyjxmbm;
    }

    public String getMainUpdateSql() {
        return mainUpdateSql;
    }

    public void setMainUpdateSql(String mainUpdateSql) {
        this.mainUpdateSql = mainUpdateSql;
    }

    public String getMainInsertSql() {
        return mainInsertSql;
    }

    public void setMainInsertSql(String mainInsertSql) {
        this.mainInsertSql = mainInsertSql;
    }

    public Integer getXmcj() {
        return xmcj;
    }

    public void setXmcj(Integer xmcj) {
        this.xmcj = xmcj;
    }

    public String getModeId() {
        return modeId;
    }

    public void setModeId(String modeId) {
        this.modeId = modeId;
    }

    public String getXmbm() {
        return xmbm;
    }

    public void setXmbm(String xmbm) {
        this.xmbm = xmbm;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getKmbm() {
        return kmbm;
    }

    public void setKmbm(String kmbm) {
        this.kmbm = kmbm;
    }

    public String getKmmc() {
        return kmmc;
    }

    public void setKmmc(String kmmc) {
        this.kmmc = kmmc;
    }

    public String getHth() {
        return hth;
    }

    public void setHth(String hth) {
        this.hth = hth;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }
}
