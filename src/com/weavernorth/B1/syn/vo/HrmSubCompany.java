package com.weavernorth.B1.syn.vo;

import weaver.conn.ConnStatement;
import weaver.general.BaseBean;

/**
 * 分部
 */
public class HrmSubCompany extends BaseBean {

    private String id;
    /**
     * 分部编码
     */
    private String subcompanycode;
    /**
     * 分部简称
     */
    private String subcompanyname;
    /**
     * 上级分部id
     */
    private String supsubcomid;
    /**
     * 封存标识 1：封存
     */
    private String canceled;
    /**
     * 显示顺序
     */
    private String showorder;

    /**
     * 更新分部信息
     */
    public void updateHrmSubCompany() {

        ConnStatement statement = new ConnStatement();
        try {
            String sql = "update HrmSubCompany set subcompanyname = ?, subcompanydesc = ?, canceled = ?, " +
                    "showorder = ? where subcompanycode = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.subcompanyname);
            statement.setString(2, this.subcompanyname);
            statement.setString(3, this.canceled);
            statement.setString(4, this.showorder);
            statement.setString(5, this.subcompanycode);
            statement.executeUpdate();
        } catch (Exception e) {
            this.writeLog("update SubCompany Exception :" + e);
        } finally {
            statement.close();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcompanycode() {
        return subcompanycode;
    }

    public void setSubcompanycode(String subcompanycode) {
        this.subcompanycode = subcompanycode;
    }

    public String getSubcompanyname() {
        return subcompanyname;
    }

    public void setSubcompanyname(String subcompanyname) {
        this.subcompanyname = subcompanyname;
    }

    public String getSupsubcomid() {
        return supsubcomid;
    }

    public void setSupsubcomid(String supsubcomid) {
        this.supsubcomid = supsubcomid;
    }

    public String getCanceled() {
        return canceled;
    }

    public void setCanceled(String canceled) {
        this.canceled = canceled;
    }

    public String getShoworder() {
        return showorder;
    }

    public void setShoworder(String showorder) {
        this.showorder = showorder;
    }

}
