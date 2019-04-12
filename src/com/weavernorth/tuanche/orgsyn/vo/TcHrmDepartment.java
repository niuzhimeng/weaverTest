package com.weavernorth.tuanche.orgsyn.vo;

import com.google.gson.annotations.SerializedName;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

/**
 * 部门
 */
public class TcHrmDepartment extends BaseBean {

    /**
     * 部门id
     */
    private String id;
    /**
     * 部门编码
     */
    @SerializedName("depCode")
    private String depcode;
    /**
     * 部门名称
     */
    @SerializedName("depName")
    private String depname;
    /**
     * 上级分部编码
     */
    private String supsubcode;
    private String supsubid;
    /**
     * 上级部门编码
     */
    @SerializedName("supsubCode")
    private String supdepcode;
    private String supdepid;
    /**
     * 封存标识
     */
    private String status;
    /**
     * 显示顺序
     */
    private String showorder;


    private void insertOrUpdateHrmDepartmentDefined() {
        RecordSet recordSet = new RecordSet();
        recordSet.execute("select * from HrmDepartmentDefined where deptid = '" + this.id + "'");
        if (recordSet.next()) {
            updateHrmDepartmentDefined();
        } else {
            insertHrmDepartmentDefined();
        }
    }


    /**
     * 插入部门自定义表
     */
    private void insertHrmDepartmentDefined() {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "insert into HrmDepartmentDefined (deptid, sfmd, sfxzjg) values (?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.id);

            statement.executeUpdate();
        } catch (Exception e) {
            this.writeLog("insert HrmDepartmentDefined Exception :" + e);
        } finally {
            statement.close();
        }
    }

    /**
     * 更新部门自定义表
     */
    private void updateHrmDepartmentDefined() {
        ConnStatement statement = new ConnStatement();
        try {
            String sql = "update HrmDepartmentDefined set sfmd = ?, sfxzjg = ? where deptid = ?";
            statement.setStatementSql(sql);

            statement.setString(3, this.id);
            statement.executeUpdate();
        } catch (Exception e) {
            this.writeLog("update HrmDepartmentDefined Exception :" + e);
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

    public String getSupdepcode() {
        return supdepcode;
    }

    public void setSupdepcode(String supdepcode) {
        this.supdepcode = supdepcode;
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
