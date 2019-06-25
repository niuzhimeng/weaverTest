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
     * 表名
     */
    private String tableName;
    /**
     * 项目层级
     */
    private Integer xmcj;

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
                ", tableName='" + tableName + '\'' +
                '}';
    }

    public Integer getXmcj() {
        return xmcj;
    }

    public void setXmcj(Integer xmcj) {
        this.xmcj = xmcj;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
