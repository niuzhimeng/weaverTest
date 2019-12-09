package com.weavernorth.jiajiezb.hr.vo;

public class ShiXiShengVo {
    // ============系统表============
    private String ygbh; // 员工编号
    private String gw; // 岗位
    private String zsld; // 直属领导
    private String bm; // 部门
    private String syqjsrq; // 试用期结束日期

    // ============自定义表============
    private String zj; // 职级
    private String gwlx; // 岗位类型
    private String bps; // BPS审批人
    private String bgdd; // 办公地点
    private String wxyj; //  五险一金缴纳地

    private String cwou; // 财务OU
    private String rzrq; // 入职日期
    private String ldhtqs; // 劳动合同签署主体
    private String ldhtksrq; // 劳动合同签约开始日期
    private String ldhtjsrq; // 劳动合同签约结束日期

    // ============建模表============
    private String jbgz; // 基本工资
    private String jjbl; // 奖金比例
    private String sbjs; // 社保基数

    public String getJbgz() {
        return jbgz;
    }

    public void setJbgz(String jbgz) {
        this.jbgz = jbgz;
    }

    public String getJjbl() {
        return jjbl;
    }

    public void setJjbl(String jjbl) {
        this.jjbl = jjbl;
    }

    public String getSbjs() {
        return sbjs;
    }

    public void setSbjs(String sbjs) {
        this.sbjs = sbjs;
    }

    public ShiXiShengVo() {
    }

    public String getYgbh() {
        return ygbh;
    }

    public void setYgbh(String ygbh) {
        this.ygbh = ygbh;
    }

    public String getGw() {
        return gw;
    }

    public void setGw(String gw) {
        this.gw = gw;
    }

    public String getZsld() {
        return zsld;
    }

    public void setZsld(String zsld) {
        this.zsld = zsld;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getSyqjsrq() {
        return syqjsrq;
    }

    public void setSyqjsrq(String syqjsrq) {
        this.syqjsrq = syqjsrq;
    }

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
    }

    public String getGwlx() {
        return gwlx;
    }

    public void setGwlx(String gwlx) {
        this.gwlx = gwlx;
    }

    public String getBps() {
        return bps;
    }

    public void setBps(String bps) {
        this.bps = bps;
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

    public String getCwou() {
        return cwou;
    }

    public void setCwou(String cwou) {
        this.cwou = cwou;
    }

    public String getRzrq() {
        return rzrq;
    }

    public void setRzrq(String rzrq) {
        this.rzrq = rzrq;
    }

    public String getLdhtqs() {
        return ldhtqs;
    }

    public void setLdhtqs(String ldhtqs) {
        this.ldhtqs = ldhtqs;
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
}
