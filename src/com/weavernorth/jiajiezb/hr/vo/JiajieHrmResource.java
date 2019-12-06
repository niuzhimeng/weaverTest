package com.weavernorth.jiajiezb.hr.vo;

import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 人员信息
 */
public class JiajieHrmResource {

    private BaseBean baseBean = new BaseBean();

    private String workcode;//员工编号
    private String lastname;//姓名
    private String loginid;//系统登陆帐号
    private String status;//员工状态
    private String sex;//性别

    private String location;//工作地点
    private String email;//电子邮件
    private String mobile;//手机
    private String depId;//部门id
    private String subId;//分部id

    private String jobtitleId;//岗位id
    private String managerIdReal;//上级id
    private String managerstr;//所有上级
    private String seclevel;//安全级别
    private String accounttype;//账号类型

    private String belongto;//所属主账号
    private String systemlanguage;//语言
    private String passWord;//密码
    private String id;
    private String statusOa; //员工状态

    private String locationId; // 工作地点
    private String probationenddate; // 试用期结束日期
    private String dsporder; // 显示顺序
    private String telephone; // 座机

    /**
     * 获取所有上级String
     *
     * @param managerid 上级id
     * @return 所有上级id
     */
    public String getManagerIdAndStr(String managerid) {
        RecordSet rs = new RecordSet();
        String returnStr = "";
        if (null == managerid || "".equals(managerid)) {
            return returnStr;
        }
        try {
            rs.executeSql("select managerstr from HrmResource where id = " + managerid);
            String managerstr = "";
            if (rs.next()) {
                managerstr = Util.null2String(rs.getString("managerstr"));
                if ("".equals(managerstr)) {
                    managerstr = "" + managerid;
                } else {
                    managerstr = managerstr + "," + managerid;
                }
            }
            returnStr = replaceStr(managerstr) + ",";
        } catch (Exception e) {
            baseBean.writeLog("get hrmresource all manager Exception :" + e);
        }
        return returnStr;
    }

    private static String replaceStr(String str) {
        ArrayList list = Util.TokenizerString(str, ",");
        StringBuilder temp = new StringBuilder();
        for (Object o : list) {
            if ("".equals(temp.toString())) {
                temp = new StringBuilder((String) o);
            } else {
                temp.append(",").append((String) o);
            }
        }
        return temp.toString();
    }

    /**
     * 新增人员初始化权限设置
     */
    public void updaterights(String maxid) {
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
            baseBean.writeLog("update rights Exception :" + e);
        }
    }

    public String getProbationenddate() {
        return probationenddate;
    }

    public void setProbationenddate(String probationenddate) {
        this.probationenddate = probationenddate;
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

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDsporder() {
        return dsporder;
    }

    public void setDsporder(String dsporder) {
        this.dsporder = dsporder;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getJobtitleId() {
        return jobtitleId;
    }

    public void setJobtitleId(String jobtitleId) {
        this.jobtitleId = jobtitleId;
    }

    public String getManagerIdReal() {
        return managerIdReal;
    }

    public void setManagerIdReal(String managerIdReal) {
        this.managerIdReal = managerIdReal;
    }

    public String getManagerstr() {
        return managerstr;
    }

    public void setManagerstr(String managerstr) {
        this.managerstr = managerstr;
    }

    public String getSeclevel() {
        return seclevel;
    }

    public void setSeclevel(String seclevel) {
        this.seclevel = seclevel;
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

    public String getSystemlanguage() {
        return systemlanguage;
    }

    public void setSystemlanguage(String systemlanguage) {
        this.systemlanguage = systemlanguage;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusOa() {
        return statusOa;
    }

    public void setStatusOa(String statusOa) {
        this.statusOa = statusOa;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
