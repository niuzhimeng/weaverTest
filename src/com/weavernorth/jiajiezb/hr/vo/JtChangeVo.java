package com.weavernorth.jiajiezb.hr.vo;

public class JtChangeVo {
    /**
     * 部门
     */
    private String bm;
    /**
     * 岗位
     */
    private String gw;
    /**
     * 调动日期
     */
    private String ddrq;
    /**
     * 岗位类型
     */
    private String gwlx;
    /**
     * 职级
     */
    private String zz;

    /**
     * 办公地点
     */
    private String bgdd;
    /**
     * 五险一金缴纳地
     */
    private String wxyj;
    /**
     * 劳动合同签署主体
     */
    private String ldhtqs;
    /**
     * BPS审批人
     */
    private String bps;
    /**
     * 财务OU
     */
    private String cwou;
    private String ldhtksrq; // 劳动合同签约开始日期
    private String ldhtjsrq; // 劳动合同签约结束日期
    private String qscs; // 劳动合同签署次数

    private String syqdz; // 试用期是否打折
    private String htcfd; // 合同存放地

    public JtChangeVo() {
    }

    @Override
    public String toString() {
        return "ChangeVo{" +
                "bm='" + bm + '\'' +
                ", gw='" + gw + '\'' +
                ", ddrq='" + ddrq + '\'' +
                ", gwlx='" + gwlx + '\'' +
                ", zz='" + zz + '\'' +
                ", bgdd='" + bgdd + '\'' +
                ", wxyj='" + wxyj + '\'' +
                ", ldhtqs='" + ldhtqs + '\'' +
                ", bps='" + bps + '\'' +
                ", cwou='" + cwou + '\'' +
                '}';
    }

    public String getSyqdz() {
        return syqdz;
    }

    public void setSyqdz(String syqdz) {
        this.syqdz = syqdz;
    }

    public String getHtcfd() {
        return htcfd;
    }

    public void setHtcfd(String htcfd) {
        this.htcfd = htcfd;
    }

    public String getQscs() {
        return qscs;
    }

    public void setQscs(String qscs) {
        this.qscs = qscs;
    }

    public String getLdhtksrq() {
        return ldhtksrq;
    }

    public void setLdhtksrq(String ldhtksrq) {
        this.ldhtksrq = ldhtksrq;
    }

    public String getLdhtjsrq() {
        return ldhtjsrq;
    }

    public void setLdhtjsrq(String ldhtjsrq) {
        this.ldhtjsrq = ldhtjsrq;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
    }

    public String getDdrq() {
        return ddrq;
    }

    public void setDdrq(String ddrq) {
        this.ddrq = ddrq;
    }

    public String getGwlx() {
        return gwlx;
    }

    public void setGwlx(String gwlx) {
        this.gwlx = gwlx;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public String getBgdd() {
        return bgdd;
    }

    public void setBgdd(String bgdd) {
        this.bgdd = bgdd;
    }

    public String getWxyj() {
        return wxyj;
    }

    public void setWxyj(String wxyj) {
        this.wxyj = wxyj;
    }

    public String getLdhtqs() {
        return ldhtqs;
    }

    public void setLdhtqs(String ldhtqs) {
        this.ldhtqs = ldhtqs;
    }

    public String getBps() {
        return bps;
    }

    public void setBps(String bps) {
        this.bps = bps;
    }

    public String getCwou() {
        return cwou;
    }

    public void setCwou(String cwou) {
        this.cwou = cwou;
    }
}
