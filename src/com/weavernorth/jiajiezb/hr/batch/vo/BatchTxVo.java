package com.weavernorth.jiajiezb.hr.batch.vo;

/**
 * 群体调薪VO
 */
public class BatchTxVo {

    private String zj; // 职级
    private String tzrq; // 调整日期
    private String jbgz; // 基本工资

    public BatchTxVo() {
    }

    public String getZj() {
        return zj;
    }

    public void setZj(String zj) {
        this.zj = zj;
    }

    public String getTzrq() {
        return tzrq;
    }

    public void setTzrq(String tzrq) {
        this.tzrq = tzrq;
    }

    public String getJbgz() {
        return jbgz;
    }

    public void setJbgz(String jbgz) {
        this.jbgz = jbgz;
    }
}
