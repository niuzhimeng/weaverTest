package com.weavernorth.B1.syn.vo;

import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

/**
 * 部门
 */
public class HrmDepartment extends BaseBean {
    private ConnStatement statement;

    private String id;
    /**
     * 部门编码
     */
    private String departmentcode;
    /**
     * 部门名称
     */
    private String departmentname;
    /**
     * 上级分部
     */
    private String subcompanyid1;
    /**
     * 上级部门
     */
    private String supdepid;
    /**
     * 封存标识
     */
    private String canceled;
    /**
     * 显示顺序
     */
    private String showorder;
    /**
     * 生效日期
     */
    private String effectivedate;

    /**
     * 插入部门信息
     */
    public boolean insertHrmDepartment() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "insert into HrmDepartment (departmentcode, departmentname, departmentmark, subcompanyid1, supdepid, canceled, showorder) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.departmentcode);
            statement.setString(2, this.departmentname);
            statement.setString(3, this.departmentname);
            statement.setString(4, this.subcompanyid1);
            statement.setString(5, this.supdepid);
            statement.setString(6, this.canceled);
            statement.setString(7, this.showorder);
            statement.executeUpdate();
            is_success = true;
        } catch (Exception e) {
            this.writeLog("insert department Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("insert department close connect Exception :" + e);
            }
        }
        return is_success;
    }

    /**
     * 更新部门
     */
    public boolean updateHrmDepartment() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "update HrmDepartment set departmentname = ?, departmentmark = ?, subcompanyid1 = ?, supdepid = ?," +
                    " canceled = ?, showorder = ?  where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.departmentname);
            statement.setString(2, this.departmentname);
            statement.setString(3, this.subcompanyid1);
            statement.setString(4, this.supdepid);
            statement.setString(5, this.canceled);
            statement.setString(6, this.showorder);
            statement.setString(7, this.id);
            statement.executeUpdate();
            is_success = true;
        } catch (Exception e) {
            this.writeLog("update department Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                this.writeLog("update department close connect Exception :" + e);
            }
        }
        return is_success;
    }

    /**
     * 删除机构
     */
    public Integer deleteHrmDepartment() {
        RecordSet recordSet = new RecordSet();
        recordSet.execute("select * from hrmdepartment where supdepid = '" + this.id + "'");
        if (recordSet.next()) {
            //删除的机构下存在子机构
            return -202;
        }
        recordSet.execute("select * from hrmresource where departmentid =  '" + this.id + "'");
        if (recordSet.next()) {
            //删除的机构下存在用户
            return -203;
        }
        recordSet.execute("delete from hrmdepartment where id = '" + this.id + "'");
        return 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getSubcompanyid1() {
        return subcompanyid1;
    }

    public void setSubcompanyid1(String subcompanyid1) {
        this.subcompanyid1 = subcompanyid1;
    }

    public String getSupdepid() {
        return supdepid;
    }

    public void setSupdepid(String supdepid) {
        this.supdepid = supdepid;
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

    public String getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate;
    }
}
