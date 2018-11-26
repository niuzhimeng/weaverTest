package com.weavernorth.B1.syn.vo;


import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

import java.util.Calendar;

public class HrmResource extends BaseBean {

    private ConnStatement statement;
    /**
     * 用户密级字段名称
     */
    private static final String GREAD_FIELD_NAME = "field0";

    private String id;
    private String workcode;
    private String lastname;
    private String subcompanyid1;
    private String departmentid;

    private String sex;
    private String status;
    private String loginid;
    private String password;
    private String seclevel;

    private String accounttype;
    private String belongto;
    private String systemlanguage;
    /**
     * 用户密级：内部 秘密 机密
     */
    private String grade;

    public Integer insertHrmResource() {
        int is_success;
        statement = new ConnStatement();
        try {
            String sql = "insert into HrmResource (workcode, lastname, loginid, status, password," +
                    "  accounttype, belongto, departmentid, subcompanyid1, systemlanguage," +
                    "seclevel, id, sex) values (?,?,?,?,?, ?,?,?,?,?, ?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.getWorkcode());
            statement.setString(2, this.getLastname());
            statement.setString(3, this.getLoginid());
            statement.setString(4, this.getStatus());
            statement.setString(5, this.getPassword());

            statement.setString(6, this.getAccounttype());
            statement.setString(7, this.getBelongto());
            statement.setString(8, this.getDepartmentid());
            statement.setString(9, this.getSubcompanyid1());
            statement.setString(10, this.getSystemlanguage());

            statement.setString(11, "0");
            statement.setString(12, this.getId());
            statement.setString(13, this.getSex(this.sex));

            statement.executeUpdate();
            is_success = insertCus_fielddata(statement);
        } catch (Exception e) {
            //应用系统Webservice服务异常
            is_success = -804;
            this.writeLog("insert HrmResource Exception :" + e);
        } finally {
            try {
                statement.close();
                this.UpdateRights(this.getId());
            } catch (Exception e) {
                this.writeLog("insert HrmResource close connect Exception :" + e);
            }
        }
        return is_success;
    }

    /**
     * 更新人员
     */
    public Integer updateHrmResource() {
        int flag = 1;
        statement = new ConnStatement();
        try {
            String sql = "update HrmResource set lastname = ?, departmentid = ?, sex = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.getLastname());
            statement.setString(2, this.getDepartmentid());
            statement.setString(3, this.getSex(this.sex));
            statement.setString(4, this.getId());
            statement.executeUpdate();

            flag = updateCusFieldData(statement);
        } catch (Exception e) {
            this.writeLog("update HrmResource Exception :" + e);
        } finally {
            statement.close();
            this.UpdateRights(this.getId());
        }
        return flag;
    }

    /**
     * 单独更新人员部门
     */
    public void updateHrmResourceDepartment() {
        statement = new ConnStatement();
        try {
            String sql = "update HrmResource set departmentid = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.getDepartmentid());
            statement.setString(2, this.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            this.writeLog("update HrmResourceDepartment Exception :" + e);
        } finally {
            statement.close();
            this.UpdateRights(this.getId());
        }
    }

    /**
     * 插入自定义字段
     */
    private Integer insertCus_fielddata(ConnStatement statement) {
        int is_success = 1;
        try {
            String sql = "insert into cus_fielddata (scope, scopeid, id, " + GREAD_FIELD_NAME + ") values (?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, "HrmCustomFieldByInfoType");
            statement.setString(2, "-1");
            statement.setString(3, this.getId());
            statement.setString(4, this.getGrade());
            statement.executeUpdate();
        } catch (Exception e) {
            //应用系统Webservice服务异常
            is_success = -804;
            this.writeLog("insert Cus_fielddata Exception :" + e);
        }
        return is_success;
    }


    private Integer updateCusFieldData(ConnStatement statement) {
        int is_success = 1;
        try {
            String sql = "update cus_fielddata set " + GREAD_FIELD_NAME + " = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, getGrade());
            statement.setString(2, this.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            is_success = -804;
            this.writeLog("update cus_fielddata Exception :" + e);
        }
        return is_success;
    }

    public boolean deleteHrmResource5(String hrmid, String status) {
        boolean is_success = false;
        if (null == hrmid || "".equals(hrmid)) {
            return false;
        }

        if (null == status || "".equals(status)) {
            return false;
        }

        if ("0".equals(status) || "1".equals(status) || "2".equals(status) || "3".equals(status)) {
            return false;
        }

        try {
            RecordSet rs = new RecordSet();
            rs.executeSql("delete from hrmrolemembers where resourceid= " + hrmid);
            rs.executeSql("delete from PluginLicenseUser where plugintype='mobile' and sharetype='0' and sharevalue='" + hrmid + "'");
            rs.executeSql("update HrmResource set status = " + status + ", loginid='',password='' ,account='' where id = " + hrmid);
            rs.executeSql("delete hrmgroupmembers where userid= " + hrmid);
            rs.executeSql("select max(id) from HrmStatusHistory");
            rs.next();
            rs.executeSql("update HrmStatusHistory set isdispose = 1 where id= " + rs.getInt(1));
            is_success = true;
        } catch (Exception e) {
            this.writeLog("update HrmResource canceled Exception :" + e);
        }
        return is_success;
    }

    /**
     * 新增人员初始化权限设置
     */
    private void UpdateRights(String maxid) {
        try {
            char separator = Util.getSeparator();
            Calendar todaycal = Calendar.getInstance();
            String today = Util.add0(todaycal.get(Calendar.YEAR), 4) + "-" + Util.add0(todaycal.get(Calendar.MONTH) + 1, 2) + "-" + Util.add0(todaycal.get(Calendar.DAY_OF_MONTH), 2);
            String userpara = "" + 1 + separator + today;
            RecordSet rs = new RecordSet();
            rs.executeProc("HrmResource_CreateInfo", "" + maxid + separator + userpara + separator + userpara);
            rs.executeSql("select hrmid from HrmInfoStatus where hrmid=" + maxid);
            if (!rs.next()) {
                String sql_1 = "insert into HrmInfoStatus (itemid,hrmid,status) values(1," + maxid + ",1)";
                rs.executeSql(sql_1);
                String sql_2 = "insert into HrmInfoStatus (itemid,hrmid) values(2," + maxid + ")";
                rs.executeSql(sql_2);
                String sql_3 = "insert into HrmInfoStatus (itemid,hrmid) values(3," + maxid + ")";
                rs.executeSql(sql_3);
                String sql_10 = "insert into HrmInfoStatus (itemid,hrmid) values(10," + maxid + ")";
                rs.executeSql(sql_10);
            }
        } catch (Exception e) {
            this.writeLog("update rights Exception :" + e);
        }
    }

    private String getSex(String sex) {
        String returnValue;
        if ("1".equals(sex)) {
            returnValue = "0";
        } else {
            returnValue = "1";
        }
        return returnValue;
    }

    public String getSystemlanguage() {
        return systemlanguage;
    }

    public void setSystemlanguage(String systemlanguage) {
        this.systemlanguage = systemlanguage;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSeclevel() {
        return seclevel;
    }

    public void setSeclevel(String seclevel) {
        this.seclevel = seclevel;
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

    public String getWorkcode() {
        return workcode;
    }

    public void setWorkcode(String workcode) {
        this.workcode = workcode;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getSubcompanyid1() {
        return subcompanyid1;
    }

    public void setSubcompanyid1(String subcompanyid1) {
        this.subcompanyid1 = subcompanyid1;
    }

    public String getLoginid() {
        return loginid;
    }


    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getAccounttype() {
        return accounttype;
    }


    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }


    public String getBelongto() {
        return belongto;
    }


    public void setBelongto(String belongto) {
        this.belongto = belongto;
    }
}
