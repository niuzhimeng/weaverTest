package com.weavernorth.gaoji.sap.action.vo;

import weaver.general.TimeUtil;

import java.util.UUID;

public class POTableVo {

    private String bguid;//唯一ID
    private String btype;//枚举全部类型分类
    private String bsource;//源系统
    private String bdestination;//目标系统
    private String bdatetime;//消息发送时间

    private String bstatus;//消息处理状态
    private String bcallback;//回调地址
    private String bversion;//版本号
    private String bdatahash;//数据重复校验字符串
    private String bkeys;//业务查询使用的关键字段

    private String bdata;//json

    public POTableVo() {
    }

    public POTableVo(String flag){
        String uid = "OA901" + UUID.randomUUID().toString().replace("-", "").toUpperCase();
        this.setBguid(uid);
        this.setBsource("901");
        this.setBdestination("101");
        this.setBdatetime(TimeUtil.getCurrentTimeString());
        this.setBstatus("1");
        this.setBcallback("http://124.251.118.4/workflow/request/gaoji/CallBack.jsp");
        this.setBversion("1.0");
        this.setBdatahash("");
        this.setBkeys(uid);
        if("BX".equals(flag)){
            this.setBtype("9008");
        } else if ("DGFK".equals(flag)){
            this.setBtype("9057");
        }
    }

    @Override
    public String toString() {
        return "POTableVo{" +
                "bguid='" + bguid + '\'' +
                ", btype='" + btype + '\'' +
                ", bsource='" + bsource + '\'' +
                ", bdestination='" + bdestination + '\'' +
                ", bdatetime='" + bdatetime + '\'' +
                ", bstatus='" + bstatus + '\'' +
                ", bcallback='" + bcallback + '\'' +
                ", bversion='" + bversion + '\'' +
                ", bdatahash='" + bdatahash + '\'' +
                ", bkeys='" + bkeys + '\'' +
                ", bdata='" + bdata + '\'' +
                '}';
    }

    public String getBguid() {
        return bguid;
    }

    public void setBguid(String bguid) {
        this.bguid = bguid;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public String getBsource() {
        return bsource;
    }

    public void setBsource(String bsource) {
        this.bsource = bsource;
    }

    public String getBdestination() {
        return bdestination;
    }

    public void setBdestination(String bdestination) {
        this.bdestination = bdestination;
    }

    public String getBdatetime() {
        return bdatetime;
    }

    public void setBdatetime(String bdatetime) {
        this.bdatetime = bdatetime;
    }

    public String getBstatus() {
        return bstatus;
    }

    public void setBstatus(String bstatus) {
        this.bstatus = bstatus;
    }

    public String getBcallback() {
        return bcallback;
    }

    public void setBcallback(String bcallback) {
        this.bcallback = bcallback;
    }

    public String getBversion() {
        return bversion;
    }

    public void setBversion(String bversion) {
        this.bversion = bversion;
    }

    public String getBdatahash() {
        return bdatahash;
    }

    public void setBdatahash(String bdatahash) {
        this.bdatahash = bdatahash;
    }

    public String getBkeys() {
        return bkeys;
    }

    public void setBkeys(String bkeys) {
        this.bkeys = bkeys;
    }

    public String getBdata() {
        return bdata;
    }

    public void setBdata(String bdata) {
        this.bdata = bdata;
    }
}
