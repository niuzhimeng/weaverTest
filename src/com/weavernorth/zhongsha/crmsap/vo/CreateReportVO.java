package com.weavernorth.zhongsha.crmsap.vo;

public class CreateReportVO {

    /**
     * 最终价格
     */
    private Double zzjg;
    /**
     * 报审价格
     */
    private Double bsjg;
    /**
     * 计划费用
     */
    private Double jhfy;

    public CreateReportVO() {
    }

    public Double getZzjg() {
        return zzjg;
    }

    public void setZzjg(Double zzjg) {
        this.zzjg = zzjg;
    }

    public Double getBsjg() {
        return bsjg;
    }

    public void setBsjg(Double bsjg) {
        this.bsjg = bsjg;
    }

    public Double getJhfy() {
        return jhfy;
    }

    public void setJhfy(Double jhfy) {
        this.jhfy = jhfy;
    }
}
