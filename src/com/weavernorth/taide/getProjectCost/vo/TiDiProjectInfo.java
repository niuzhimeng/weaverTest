package com.weavernorth.taide.getProjectCost.vo;

public class TiDiProjectInfo {
    /**
     * 主流程ID
     */
    private String requestId;
    /**
     * 明细ID
     */
    private String mid;
    /**
     * 员工工号
     */
    private String workCode;
    /**
     * 预算日期
     */
    private String ysrq;
    /**
     * 费用承担部门
     */
    private String cbzxbm;
    /**
     * 研发项目号
     */
    private String rdh;
    /**
     * 科目代码
     */
    private String kmdm;
    /**
     * 里程碑代码
     */
    private String yflcb;
    /**
     * 不含税金额（元）
     */
    private String jermb;

    /**
     * 最后审批完日期
     */
    private String lastOperateDate;
    /**
     * 报销原有
     */
    private String bxyy;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getYsrq() {
        return ysrq;
    }

    public void setYsrq(String ysrq) {
        this.ysrq = ysrq;
    }

    public String getCbzxbm() {
        return cbzxbm;
    }

    public void setCbzxbm(String cbzxbm) {
        this.cbzxbm = cbzxbm;
    }

    public String getRdh() {
        return rdh;
    }

    public void setRdh(String rdh) {
        this.rdh = rdh;
    }

    public String getKmdm() {
        return kmdm;
    }

    public void setKmdm(String kmdm) {
        this.kmdm = kmdm;
    }

    public String getYflcb() {
        return yflcb;
    }

    public void setYflcb(String yflcb) {
        this.yflcb = yflcb;
    }

    public String getJermb() {
        return jermb;
    }

    public void setJermb(String jermb) {
        this.jermb = jermb;
    }

    public String getLastOperateDate() {
        return lastOperateDate;
    }

    public void setLastOperateDate(String lastOperateDate) {
        this.lastOperateDate = lastOperateDate;
    }

    public String getBxyy() {
        return bxyy;
    }

    public void setBxyy(String bxyy) {
        this.bxyy = bxyy;
    }
}
