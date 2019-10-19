package com.weavernorth.gaodeng.orgsyn.vo;

import weaver.general.BaseBean;

/**
 * 部门
 */
public class GdHrmDepartment extends BaseBean {

    /**
     * 部门编码
     */
    private String depcode;
    /**
     * 部门名称
     */
    private String depname;
    /**
     * 上级组织编码
     */
    private String supsubcode;
    /**
     * 封存标识
     */
    private String status;
    private String statusOa;
    /**
     * 显示顺序
     */
    private String showorder;

    /**
     * 上级分部id
     */
    private String supsubid;
    /**
     * 上级部门id
     */
    private String supdepid;

    @Override
    public String toString() {
        return "GdHrmDepartment{" +
                "depcode='" + depcode + '\'' +
                ", depname='" + depname + '\'' +
                ", supsubcode='" + supsubcode + '\'' +
                ", status='" + status + '\'' +
                ", statusOa='" + statusOa + '\'' +
                ", showorder='" + showorder + '\'' +
                ", supsubid='" + supsubid + '\'' +
                ", supdepid='" + supdepid + '\'' +
                '}';
    }

    public String getStatusOa() {
        return statusOa;
    }

    public void setStatusOa(String statusOa) {
        this.statusOa = statusOa;
    }

    public GdHrmDepartment() {
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getSupsubcode() {
        return supsubcode;
    }

    public void setSupsubcode(String supsubcode) {
        this.supsubcode = supsubcode;
    }

    public String getSupsubid() {
        return supsubid;
    }

    public void setSupsubid(String supsubid) {
        this.supsubid = supsubid;
    }

    public String getSupdepid() {
        return supdepid;
    }

    public void setSupdepid(String supdepid) {
        this.supdepid = supdepid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShoworder() {
        return showorder;
    }

    public void setShoworder(String showorder) {
        this.showorder = showorder;
    }
}
