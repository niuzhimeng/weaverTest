package com.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("root")
public class ChangeVoTest {
    /**
     * 工作地点
     */
    private String gzdd;
    /**
     * 法人体
     */
    private String frt;
    /**
     * 部门
     */
    private String bm;
    /**
     * 岗位名称
     */
    private String gwmc;
    /**
     * 岗位类别
     */
    private String gwlb;
    /**
     * 岗位级别
     */
    private String gwjb;
    /**
     * 岗位地图
     */
    private String gwdt;

    public ChangeVoTest() {
    }

    @Override
    public String toString() {
        return "ChangeVo{" +
                "gzdd='" + gzdd + '\'' +
                ", frt='" + frt + '\'' +
                ", bm='" + bm + '\'' +
                ", gwmc='" + gwmc + '\'' +
                ", gwlb='" + gwlb + '\'' +
                ", gwjb='" + gwjb + '\'' +
                ", gwdt='" + gwdt + '\'' +
                '}';
    }

    public String getGwdt() {
        return gwdt;
    }

    public void setGwdt(String gwdt) {
        this.gwdt = gwdt;
    }

    public String getGzdd() {
        return gzdd;
    }

    public void setGzdd(String gzdd) {
        this.gzdd = gzdd;
    }

    public String getFrt() {
        return frt;
    }

    public void setFrt(String frt) {
        this.frt = frt;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getGwmc() {
        return gwmc;
    }

    public void setGwmc(String gwmc) {
        this.gwmc = gwmc;
    }

    public String getGwlb() {
        return gwlb;
    }

    public void setGwlb(String gwlb) {
        this.gwlb = gwlb;
    }

    public String getGwjb() {
        return gwjb;
    }

    public void setGwjb(String gwjb) {
        this.gwjb = gwjb;
    }
}
