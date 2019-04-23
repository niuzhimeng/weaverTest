package com.weavernorth.huaLian.vo;

/**
 * 人事编制vo
 */
public class ModelVo {
    /**
     * 分部id
     */
    private int fb;
    /**
     * 可编制人员总数
     */
    private int kbzrszs;
    /**
     * 在编人数
     */
    private int zbrs;
    /**
     * 可申请人数
     */
    private int ksqrs;
    /**
     * 申请中人数
     */
    private int sqzrs;
    /**
     * 招聘中人数
     */
    private int zpzrs;

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }

    public int getKbzrszs() {
        return kbzrszs;
    }

    public void setKbzrszs(int kbzrszs) {
        this.kbzrszs = kbzrszs;
    }

    public int getZbrs() {
        return zbrs;
    }

    public void setZbrs(int zbrs) {
        this.zbrs = zbrs;
    }

    public int getKsqrs() {
        return ksqrs;
    }

    public void setKsqrs(int ksqrs) {
        this.ksqrs = ksqrs;
    }

    public int getSqzrs() {
        return sqzrs;
    }

    public void setSqzrs(int sqzrs) {
        this.sqzrs = sqzrs;
    }

    public int getZpzrs() {
        return zpzrs;
    }

    public void setZpzrs(int zpzrs) {
        this.zpzrs = zpzrs;
    }
}
