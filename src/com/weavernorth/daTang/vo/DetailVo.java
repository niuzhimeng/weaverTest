package com.weavernorth.daTang.vo;

/**
 * 明细表vo
 */
public class DetailVo {

    private String id;//明细表id

    private String ejbm;//二级部门
    private String sjbm;//三级部门
    private String xm;//姓名
    private String gh;//工号
    private String rylb;//人员类别

    private String bmgg;//部门公共
    private String xmbh;//项目编号
    private String xmmc;//项目名称
    private String ywfx;//业务方向
    private String gsqzzj; //工时权重总计

    private String sm;//说明
    private String xmjl;//项目经理

    @Override
    public String toString() {
        return "DetailVo{" +
                "ejbm='" + ejbm + '\'' +
                ", sjbm='" + sjbm + '\'' +
                ", xm='" + xm + '\'' +
                ", gh='" + gh + '\'' +
                ", rylb='" + rylb + '\'' +
                ", bmgg='" + bmgg + '\'' +
                ", xmbh='" + xmbh + '\'' +
                ", xmmc='" + xmmc + '\'' +
                ", ywfx='" + ywfx + '\'' +
                ", gsqzzj='" + gsqzzj + '\'' +
                ", sm='" + sm + '\'' +
                ", xmjl='" + xmjl + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEjbm() {
        return ejbm;
    }

    public void setEjbm(String ejbm) {
        this.ejbm = ejbm;
    }

    public String getSjbm() {
        return sjbm;
    }

    public void setSjbm(String sjbm) {
        this.sjbm = sjbm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getRylb() {
        return rylb;
    }

    public void setRylb(String rylb) {
        this.rylb = rylb;
    }

    public String getBmgg() {
        return bmgg;
    }

    public void setBmgg(String bmgg) {
        this.bmgg = bmgg;
    }

    public String getXmbh() {
        return xmbh;
    }

    public void setXmbh(String xmbh) {
        this.xmbh = xmbh;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getYwfx() {
        return ywfx;
    }

    public void setYwfx(String ywfx) {
        this.ywfx = ywfx;
    }

    public String getGsqzzj() {
        return gsqzzj;
    }

    public void setGsqzzj(String gsqzzj) {
        this.gsqzzj = gsqzzj;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getXmjl() {
        return xmjl;
    }

    public void setXmjl(String xmjl) {
        this.xmjl = xmjl;
    }
}
