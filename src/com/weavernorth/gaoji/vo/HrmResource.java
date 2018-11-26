package com.weavernorth.gaoji.vo;

import com.weaver.general.TimeUtil;
import com.weavernorth.gaoji.util.Utils;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.general.BaseBean;
import weaver.general.Util;

/**
 * 人员信息
 */
public class HrmResource {
    private static final String modelId = "19";
    private static final int yhmodelId = 101;
    private ConnStatement statement;
    private BaseBean baseBean = new BaseBean();
    private ModeRightInfo modeRightInfo = new ModeRightInfo();

    private String id;//人员id
    private String depId;//部门id
    private String subId;//分部id
    private String jobtitleId;//岗位id
    private String managerIdReal;//上级id
    private String managerstr;//所有上级

    private String workcode;//员工编号
    private String lastname;//姓名
    private String loginid;//系统登陆帐号
    private String status;//员工状态
    private String sex;//性别

    private String location;//工作地点
    private String email;//电子邮件
    private String managerCode;//直接上级员工编号
    private String seclevel;//安全级别
    private String depcode;//所属部门编码

    private String subcode;//所属分部编码
    private String jobtitlecode;//岗位编码
    private String entrydate;//入职日期
    private String annualleave;//年假（可用年假）
    private String datefield1;//生效日期

    //=================建模表=====================
    private String laborrelation;//劳动关系所属
    private String bankaccount;//收款银行账号
    private String openingbank;//开户行
    private String accountcity;//开户城市
    private String subbranchmess;//支行信息

    //=================非必填=====================
    private String mobile;//手机
    private String joblevel;//职级
    private String birthday;//生日
    private String weixincode;//微信号
    private String dsporder;//人员排序

    private String homephone;//家庭联系方式
    private String folk;//民族
    private String speciality;//专长
    private String hobby;//爱好
    private String startdate;//合同开始日期

    private String enddate;//合同结束时间
    private String certificatenum;//身份证号码
    private String createdate;//创建日期

    private String czlx;//操作类型
    private String zt;//状态
    private String sbyy;//失败原因
    private String accounttype;//账号类型
    private String belongto;//所属主账号
    private String systemlanguage;//语言
    private String passWord;//密码
    /**
     * HR唯一键
     */
    private String hrguid;

    public void insertHrmResource() {
        statement = new ConnStatement();
        try {
            String sql = "insert into hrmresource (workcode, lastname, loginid, status, sex," +
                    " locationid, email, mobile, managerid, joblevel," +
                    "seclevel, departmentid, subcompanyid1, jobtitle, birthday," +
                    "startdate, dsporder, telephone, folk, enddate," +
                    " id, password, accounttype, belongto, systemlanguage, " +
                    "certificatenum, hrguid) " +
                    "values (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,   ?, ?, ?, ?, ?,   ?, ?, ?, ?, ?,  ?,?,?,?,?, ?,?)";
            statement.setStatementSql(sql);
            long start = System.currentTimeMillis();
            baseBean.writeLog("执行insert开始时间： " + start);
            statement.setString(1, this.workcode);
            statement.setString(2, this.lastname);
            statement.setString(3, this.loginid);
            statement.setString(4, this.status);
            statement.setString(5, this.sex);

            statement.setString(6, this.location);
            statement.setString(7, this.email);
            statement.setString(8, this.mobile);
            statement.setString(9, this.managerIdReal);
            statement.setString(10, this.joblevel);

            statement.setString(11, this.seclevel);
            statement.setString(12, this.depId);
            statement.setString(13, this.subId);
            statement.setString(14, this.jobtitleId);
            statement.setString(15, this.birthday);

            statement.setString(16, this.entrydate);
            statement.setString(17, this.dsporder);
            statement.setString(18, this.homephone);
            statement.setString(19, this.folk);
            statement.setString(20, this.enddate);

            statement.setString(21, this.id);
            statement.setString(22, this.passWord);
            statement.setString(23, this.accounttype);
            statement.setString(24, this.belongto);
            statement.setString(25, this.systemlanguage);

            statement.setString(26, this.certificatenum);
            statement.setString(27, this.hrguid);
            statement.executeUpdate();
            long end = System.currentTimeMillis();
            baseBean.writeLog("执行insert结束时间： " + end);
            baseBean.writeLog("执行insert时长： " + (end-start));
            //插入自定义字段
            //insertCus_fielddata(statement);
            //插入或更新客户自定义建模
            insertOrUpdateKhModel();
        } catch (Exception e) {
            baseBean.writeLog("insert HrmResource Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                baseBean.writeLog("insert HrmResource close connect Exception :" + e);
            }
        }
    }

    public void updateHrmResource() {
        baseBean.writeLog("执行更新人员： " + this.toString());
        statement = new ConnStatement();
        try {
            String sql = "update hrmresource set workcode = ?, lastname = ?, status = ?, sex = ?," +
                    " locationid = ?, mobile = ?, managerid = ?, joblevel = ?," +
                    "departmentid = ?, subcompanyid1 = ?, jobtitle = ?, birthday = ?," +
                    "startdate = ?, dsporder = ?, telephone = ?, folk = ?, enddate = ?," +
                    " certificatenum = ?" +
                    " where id = ?";
            statement.setStatementSql(sql);
            long start = System.currentTimeMillis();
            baseBean.writeLog("执行Update开始时间： " + start);
            statement.setString(1, this.workcode);
            statement.setString(2, this.lastname);
            statement.setString(3, this.status);
            statement.setString(4, this.sex);

            statement.setString(5, this.location);
            statement.setString(6, this.mobile);
            statement.setString(7, this.managerIdReal);
            statement.setString(8, this.joblevel);

            statement.setString(9, this.depId);
            statement.setString(10, this.subId);
            statement.setString(11, this.jobtitleId);
            statement.setString(12, this.birthday);

            statement.setString(13, this.entrydate);
            statement.setString(14, this.dsporder);
            statement.setString(15, this.homephone);
            statement.setString(16, this.folk);
            statement.setString(17, this.enddate);

            statement.setString(18, this.certificatenum);
            statement.setString(19, this.id);
            statement.executeUpdate();

            long end = System.currentTimeMillis();
            baseBean.writeLog("执行Update结束时间： " + end);
            baseBean.writeLog("执行Update时长： " + (end-start));
            //更新自定义字段
            //updateCus_fielddata(statement);
            //插入或更新客户自定义建模
            insertOrUpdateKhModel();
        } catch (Exception e) {
            baseBean.writeLog("update HrmResource Exception :" + e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                baseBean.writeLog("update HrmResource close connect Exception :" + e);
            }
        }
    }

    /**
     * 插入日志
     */
    public void insertModel() {
        statement = new ConnStatement();
        try {
            String sql = "insert into uf_ry (workcode, lastname, loginid, status, location," +
                    " managerid, depcode, subcode, jobtitlecode, czlx," +
                    " zt, sbyy," +
                    "formmodeid, modedatacreater, modedatacreatertype, modedatacreatedate, modedatacreatetime)" +
                    " values (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?,?,   ?,?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.workcode);
            statement.setString(2, this.lastname);
            statement.setString(3, this.loginid);
            statement.setString(4, this.status);
            statement.setString(5, this.location);

            statement.setString(6, this.managerCode);
            statement.setString(7, this.depcode);
            statement.setString(8, this.subcode);
            statement.setString(9, this.jobtitlecode);
            statement.setString(10, this.czlx);

            statement.setString(11, this.zt);
            statement.setString(12, this.sbyy);

            statement.setString(13, modelId);//模块id
            statement.setString(14, "1");//创建人id
            statement.setString(15, "0");//一个默认值0
            statement.setString(16, TimeUtil.getCurrentTimeString().substring(0, 10));
            statement.setString(17, TimeUtil.getCurrentTimeString().substring(11));
            statement.executeUpdate();
            //设置主表权限
            RecordSet recordSet = new RecordSet();
            recordSet.executeSql("select max(id) id from uf_ry");
            int maxId = 0;
            if (recordSet.next()) {
                maxId = recordSet.getInt("id");
            }
            ModeRightInfo modeRightInfo = new ModeRightInfo();
            modeRightInfo.setNewRight(true);
            modeRightInfo.editModeDataShare(1, Integer.parseInt(modelId), maxId);//创建人id， 模块id， 该条数据id
        } catch (Exception e) {
            baseBean.writeLog("insert uf_ry Exception :" + e);
        } finally {
            statement.close();
        }
    }

    /**
     * 插入自定义字段
     */
    private void insertCus_fielddata(ConnStatement statement) {
        try {
            String sql = "insert into cus_fielddata (scope, scopeid, id, field0, field1, field9, field10) values (?,?,?,?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, "HrmCustomFieldByInfoType");
            statement.setString(2, "1");//不一定是多少
            statement.setString(3, this.getId());

            statement.setString(4, this.weixincode);//微信号
            statement.setString(5, this.annualleave);//可用年假
            statement.setString(6, this.speciality);//专长
            statement.setString(7, this.hobby);//爱好
            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("insert Cus_fielddata Exception :" + e);
        }
    }

    /**
     * 更新自定义字段
     */
    private void updateCus_fielddata(ConnStatement statement) {
        try {
            String sql = "update cus_fielddata set field0 = ?, field1 = ?, field9 = ?, field10 = ? where id = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.weixincode);//微信号
            statement.setString(2, this.annualleave);//可用年假
            statement.setString(3, this.speciality);//专长
            statement.setString(4, this.hobby);//爱好
            statement.setString(5, this.id);
            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("update Cus_fielddata Exception :" + e);
        }
    }

    public void insertOrUpdateKhModel() {
        RecordSet recordSet = new RecordSet();
        recordSet.execute("select * from uf_fkyhzhxx where yx = '" + this.email + "'");
        if (recordSet.next()) {
            updateKhModel();
        } else {
            insertKhModel();
        }
    }

    /**
     * 更新客户建模表
     */
    private void updateKhModel() {
        statement = new ConnStatement();
        try {
            String sql = "update uf_fkyhzhxx set fptt = ?, skyhzh = ?, khh = ?, khcs = ?," +
                    " zhxx = ? where yx = ?";
            statement.setStatementSql(sql);
            statement.setString(1, this.laborrelation);
            statement.setString(2, this.bankaccount);
            statement.setString(3, this.openingbank);
            statement.setString(4, this.accountcity);
            statement.setString(5, this.subbranchmess);
            statement.setString(6, this.email);

            statement.executeUpdate();
        } catch (Exception e) {
            baseBean.writeLog("update uf_fkyhzhxx Exception :" + e);
        } finally {
            statement.close();
        }
    }

    /**
     * 插入客户建模表
     */
    private void insertKhModel() {
        statement = new ConnStatement();
        try {
            long start1 = System.currentTimeMillis();
            baseBean.writeLog("执行新增建模开始时间： " + start1);
            String sql = "insert into  uf_fkyhzhxx (fptt, skyhzh, khh, khcs, zhxx, yx) values (?,?,?,?,?,?)";
            statement.setStatementSql(sql);
            statement.setString(1, this.laborrelation);
            statement.setString(2, this.bankaccount);
            statement.setString(3, this.openingbank);
            statement.setString(4, this.accountcity);
            statement.setString(5, this.subbranchmess);
            statement.setString(6, this.email);
            statement.executeUpdate();
            long start2 = System.currentTimeMillis();
            baseBean.writeLog("执行新增建模插入结束时间： " + (start2-start1));
            //设置权限
            RecordSet maxSet = new RecordSet();
            modeRightInfo.setNewRight(true);
            maxSet.executeSql("select max(id) id from uf_fkyhzhxx where yx ='" + email + "'");
            int maxId = 0;
            if (maxSet.next()) {
                maxId = maxSet.getInt("id");
            }
            long start3 = System.currentTimeMillis();
            baseBean.writeLog("建模授权开始时间： " + start3);
            modeRightInfo.editModeDataShare(1, yhmodelId, maxId);//创建人id， 模块id， 该条数据id
            long start4 = System.currentTimeMillis();
            baseBean.writeLog("执行新增建模授权结束时间： " + (start4-start3));
        } catch (Exception e) {
            baseBean.writeLog("insert uf_fkyhzhxx Exception :" + e);
        } finally {
            statement.close();
        }
    }

    public boolean deleteHrmResource5(String hrmid, String status) {
        if (null == hrmid || "".equals(hrmid)) {
            return false;
        }

        if (null == status || "".equals(status)) {
            return false;
        }

        if ("0".equals(status) || "1".equals(status) || "2".equals(status) || "3".equals(status)) {
            return false;
        }

        RecordSet rs = null;
        try {
            rs = new RecordSet();
            rs.executeSql("delete from hrmrolemembers where resourceid= " + hrmid);
            rs.executeSql("delete from PluginLicenseUser where plugintype='mobile' and sharetype='0' and sharevalue='" + hrmid + "'");
            rs.executeSql("update HrmResource set status = " + status + ", loginid='',password='' ,account='' where id = " + hrmid);
            rs.executeSql("delete hrmgroupmembers where userid= " + hrmid);
            rs.executeSql("select max(id) from HrmStatusHistory");
            rs.next();
            rs.executeSql("update HrmStatusHistory set isdispose = 1 where id= " + rs.getInt(1));
        } catch (Exception e) {
            baseBean.writeLog("update HrmResource canceled Exception :" + e);
        }
        return true;
    }

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
                if (managerstr.equals("")) {
                    managerstr = "" + managerid;
                } else {
                    managerstr = managerstr + "," + managerid;
                }
            }
            returnStr = Utils.replaceStr(managerstr) + ",";
        } catch (Exception e) {
            baseBean.writeLog("get hrmresource all manager Exception :" + e);
        }
        return returnStr;
    }

    @Override
    public String toString() {
        return "HrmResource{" +
                "id='" + id + '\'' +
                ", depId='" + depId + '\'' +
                ", subId='" + subId + '\'' +
                ", jobtitleId='" + jobtitleId + '\'' +
                ", managerIdReal='" + managerIdReal + '\'' +
                ", managerstr='" + managerstr + '\'' +
                ", workcode='" + workcode + '\'' +
                ", lastname='" + lastname + '\'' +
                ", loginid='" + loginid + '\'' +
                ", status='" + status + '\'' +
                ", sex='" + sex + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", managerCode='" + managerCode + '\'' +
                ", seclevel='" + seclevel + '\'' +
                ", depcode='" + depcode + '\'' +
                ", subcode='" + subcode + '\'' +
                ", jobtitlecode='" + jobtitlecode + '\'' +
                ", entrydate='" + entrydate + '\'' +
                ", annualleave='" + annualleave + '\'' +
                ", datefield1='" + datefield1 + '\'' +
                ", laborrelation='" + laborrelation + '\'' +
                ", bankaccount='" + bankaccount + '\'' +
                ", openingbank='" + openingbank + '\'' +
                ", accountcity='" + accountcity + '\'' +
                ", subbranchmess='" + subbranchmess + '\'' +
                ", mobile='" + mobile + '\'' +
                ", joblevel='" + joblevel + '\'' +
                ", birthday='" + birthday + '\'' +
                ", weixincode='" + weixincode + '\'' +
                ", dsporder='" + dsporder + '\'' +
                ", homephone='" + homephone + '\'' +
                ", folk='" + folk + '\'' +
                ", speciality='" + speciality + '\'' +
                ", hobby='" + hobby + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", certificatenum='" + certificatenum + '\'' +
                ", createdate='" + createdate + '\'' +
                ", czlx='" + czlx + '\'' +
                ", zt='" + zt + '\'' +
                ", sbyy='" + sbyy + '\'' +
                ", accounttype='" + accounttype + '\'' +
                ", belongto='" + belongto + '\'' +
                ", systemlanguage='" + systemlanguage + '\'' +
                ", passWord='" + passWord + '\'' +
                ", hrguid='" + hrguid + '\'' +
                '}';
    }

    public String getCertificatenum() {
        return certificatenum;
    }

    public void setCertificatenum(String certificatenum) {
        this.certificatenum = certificatenum;
    }

    public String getManagerIdReal() {
        return managerIdReal;
    }

    public void setManagerIdReal(String managerIdReal) {
        this.managerIdReal = managerIdReal;
    }

    public String getJobtitleId() {
        return jobtitleId;
    }

    public void setJobtitleId(String jobtitleId) {
        this.jobtitleId = jobtitleId;
    }

    public String getManagerstr() {
        return managerstr;
    }

    public void setManagerstr(String managerstr) {
        this.managerstr = managerstr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public String getSeclevel() {
        return seclevel;
    }

    public void setSeclevel(String seclevel) {
        this.seclevel = seclevel;
    }

    public String getDepcode() {
        return depcode;
    }

    public void setDepcode(String depcode) {
        this.depcode = depcode;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getJobtitlecode() {
        return jobtitlecode;
    }

    public void setJobtitlecode(String jobtitlecode) {
        this.jobtitlecode = jobtitlecode;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getAnnualleave() {
        return annualleave;
    }

    public void setAnnualleave(String annualleave) {
        this.annualleave = annualleave;
    }

    public String getDatefield1() {
        return datefield1;
    }

    public void setDatefield1(String datefield1) {
        this.datefield1 = datefield1;
    }

    public String getLaborrelation() {
        return laborrelation;
    }

    public void setLaborrelation(String laborrelation) {
        this.laborrelation = laborrelation;
    }

    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    public String getOpeningbank() {
        return openingbank;
    }

    public void setOpeningbank(String openingbank) {
        this.openingbank = openingbank;
    }

    public String getAccountcity() {
        return accountcity;
    }

    public void setAccountcity(String accountcity) {
        this.accountcity = accountcity;
    }

    public String getSubbranchmess() {
        return subbranchmess;
    }

    public void setSubbranchmess(String subbranchmess) {
        this.subbranchmess = subbranchmess;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getJoblevel() {
        return joblevel;
    }

    public void setJoblevel(String joblevel) {
        this.joblevel = joblevel;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeixincode() {
        return weixincode;
    }

    public void setWeixincode(String weixincode) {
        this.weixincode = weixincode;
    }

    public String getDsporder() {
        return dsporder;
    }

    public void setDsporder(String dsporder) {
        this.dsporder = dsporder;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getFolk() {
        return folk;
    }

    public void setFolk(String folk) {
        this.folk = folk;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
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

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getHrguid() {
        return hrguid;
    }

    public void setHrguid(String hrguid) {
        this.hrguid = hrguid;
    }
}
