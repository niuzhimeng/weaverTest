package com.weavernorth.gaoji.vo;

import com.weaver.general.TimeUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;

/**
 * 部门
 */
public class HrmDepartment extends BaseBean {

    private static final String modelId = "17";
    private ConnStatement statement;
    /**
     * 部门id
     */
    private String id;
    /**
     * 部门编码
     */
    private String depcode;
    /**
     * 部门名称
     */
    private String depname;
    /**
     * 上级分部编码
     */
    private String supsubcode;
    private String supsubid;
    /**
     * 上级部门编码
     */
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
    /**
     * 生效日期
     */
    private String effectivedate;
    /**
     * 是否门店
     */
    private String sfmd;
    /**
     * 是否行政机构
     */
    private String sfxzjg;
    /**
     * 层级
     */
    private String grade;

    private String czlx;//操作类型
    private String zt;//状态
    private String sbyy;//失败原因
    /**
     * HR唯一键
     */
    private String hrguid;

    /**
     * 插入部门信息
     */
    public boolean insertHrmDepartment() {
        boolean is_success = false;
        statement = new ConnStatement();
        try {
            String sql = "insert into HrmDepartment (departmentcode, departmentname, departmentmark," +
                    " subcompanyid1, supdepid, canceled, showorder, hrguid) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.depcode);
            statement.setString(2, this.depname);
            statement.setString(3, this.depname);
            statement.setString(4, this.supsubid);
            statement.setString(5, this.supdepid);
            statement.setString(6, this.status);
            statement.setString(7, this.showorder);
            statement.setString(8, this.hrguid);
            statement.executeUpdate();
            is_success = true;
            //插入或更新部门自定义字段
            insertOrUpdateHrmDepartmentDefined();
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
            String sql = "update HrmDepartment set  departmentname = ?, departmentmark = ?, subcompanyid1 = ?, supdepid = ?," +
                    " canceled = ?, showorder = ?, departmentcode = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.depname);
            statement.setString(2, this.depname);
            statement.setString(3, this.supsubid);
            statement.setString(4, this.supdepid);
            statement.setString(5, this.status);
            statement.setString(6, this.showorder);
            statement.setString(7, this.depcode);
            statement.setString(8, this.id);
            statement.executeUpdate();
            is_success = true;
            //插入或更新部门自定义字段
            insertOrUpdateHrmDepartmentDefined();
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
            statement.setString(2, this.sfmd);
            statement.setString(3, this.sfxzjg);
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
            statement.setString(1, this.sfmd);
            statement.setString(2, this.sfxzjg);
            statement.setString(3, this.id);
            statement.executeUpdate();
        } catch (Exception e) {
            this.writeLog("update HrmDepartmentDefined Exception :" + e);
        } finally {
            statement.close();
        }
    }
    /**
     * 插入日志
     */
    public void insertModel() {
        statement = new ConnStatement();
        try {
            String sql = "insert into uf_bm (depcode, depname, supsubcode, supdepcode, status, showorder,effectivedate, czlx, zt, sbyy," +
                    "formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime)" +
                    " values (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?,?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.depcode);
            statement.setString(2, this.depname);
            statement.setString(3, this.supsubcode);
            statement.setString(4, this.supdepcode);
            statement.setString(5, this.status);

            statement.setString(6, this.showorder);
            statement.setString(7, this.effectivedate);
            statement.setString(8, this.czlx);
            statement.setString(9, this.zt);
            statement.setString(10, this.sbyy);

            statement.setString(11, modelId);//模块id
            statement.setString(12, "1");//创建人id
            statement.setString(13, "0");//一个默认值0
            statement.setString(14, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(15, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();
            //设置主表权限
            RecordSet recordSet = new RecordSet();
            recordSet.executeSql("select max(id) id from uf_bm");
            int maxId = 0;
            if (recordSet.next()) {
                maxId = recordSet.getInt("id");
            }
            ModeRightInfo modeRightInfo = new ModeRightInfo();
            modeRightInfo.setNewRight(true);
            modeRightInfo.editModeDataShare(1, Integer.parseInt(modelId), maxId);//创建人id， 模块id， 该条数据id
        } catch (Exception e) {
            this.writeLog("insert uf_bm Exception :" + e);
        } finally {
            statement.close();
        }
    }

    public String getSfmd() {
        return sfmd;
    }

    public void setSfmd(String sfmd) {
        this.sfmd = sfmd;
    }

    public String getSfxzjg() {
        return sfxzjg;
    }

    public void setSfxzjg(String sfxzjg) {
        this.sfxzjg = sfxzjg;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public String getEffectivedate() {
        return effectivedate;
    }

    public void setEffectivedate(String effectivedate) {
        this.effectivedate = effectivedate;
    }

    public String getCzlx() {
        return czlx;
    }

    public void setCzlx(String czlx) {
        this.czlx = czlx;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getSbyy() {
        return sbyy;
    }

    public void setSbyy(String sbyy) {
        this.sbyy = sbyy;
    }

    public String getHrguid() {
        return hrguid;
    }

    public void setHrguid(String hrguid) {
        this.hrguid = hrguid;
    }
}
