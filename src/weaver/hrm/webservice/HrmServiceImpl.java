//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package weaver.hrm.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.xfire.transport.http.XFireServletController;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.Util;
import weaver.hrm.appdetach.AppDetachComInfo;
import weaver.hrm.company.DepartmentComInfo;
import weaver.hrm.company.SubCompanyComInfo;
import weaver.hrm.job.JobTitlesComInfo;
import weaver.hrm.resource.ResourceComInfo;
import weaver.hrm.webservice.*;
import weaver.join.hrm.in.IHrmImportProcess;
import weaver.join.hrm.in.ImportLog;

public class HrmServiceImpl extends BaseBean implements HrmService {
    private HashMap h_orgInfo = new HashMap();
    private HashMap h_orgInfo_add = new HashMap();
    private HashMap h_orgInfo_update = new HashMap();
    private List h_addOrg = new ArrayList();
    private List h_updateOrg = new ArrayList();
    private List h_delOrg = new ArrayList();
    private String configip = "," + this.getPropValue("HrmWebserviceIP", "ipaddress") + ",";

    public HrmServiceImpl() {
    }

    public String SynSubCompany(String var1, String var2) throws Exception {
        var1 = this.getClientIpXfire();
        String var3 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>";
        if (("," + this.configip + ",").indexOf("," + var1 + ",") < 0) {
            this.writeLog("IP" + var1 + "不在设定的范围内,无权限访问!");
            var3 = var3 + "<message value=\"2\">IP不在设定的范围内,无权限访问</message></result>";
            return var3;
        } else {
            ParseXml var4 = new ParseXml();
            HrmServiceAction var5 = new HrmServiceAction();

            try {
                String var7 = "";
                var4.parseOrg(var2);
                this.h_orgInfo = var4.getH_orgInfo();
                this.h_addOrg = var4.getH_addOrg();
                this.h_updateOrg = var4.getH_updateOrg();
                this.h_delOrg = var4.getH_delOrg();

                int var6;
                OrgXmlBean var8;
                boolean var9;
                for (var6 = 0; var6 < this.h_addOrg.size(); ++var6) {
                    var7 = (String) this.h_addOrg.get(var6);
                    var8 = (OrgXmlBean) this.h_orgInfo.get(var7);
                    var9 = var5.addSubCompany(var1, var8);
                    if (var9) {
                        var3 = var3 + "<message value=\"1\">插入成功</message>";
                    } else {
                        var3 = var3 + "<message value=\"0\">插入失败</message>";
                    }
                }

                for (var6 = 0; var6 < this.h_updateOrg.size(); ++var6) {
                    var7 = (String) this.h_updateOrg.get(var6);
                    var8 = (OrgXmlBean) this.h_orgInfo.get(var7);
                    var9 = var5.editSubCompany(var1, var8);
                    if (var9) {
                        var3 = var3 + "<message value=\"1\">编辑成功</message>";
                    } else {
                        var3 = var3 + "<message value=\"0\">编辑失败</message>";
                    }
                }

                for (var6 = 0; var6 < this.h_delOrg.size(); ++var6) {
                    var7 = (String) this.h_delOrg.get(var6);
                    var8 = (OrgXmlBean) this.h_orgInfo.get(var7);
                    var9 = var5.delSubCompany(var1, var8);
                    if (var9) {
                        var3 = var3 + "<message value=\"1\">删除成功</message>";
                    } else {
                        var3 = var3 + "<message value=\"0\">删除失败</message>";
                    }
                }

                try {
                    SubCompanyComInfo var12 = new SubCompanyComInfo();
                    var12.removeCompanyCache();
                    (new AppDetachComInfo()).initSubDepAppData();
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            } catch (Exception var11) {
                var11.printStackTrace();
                var3 = var3 + "<message value=\"0\">数据异常</message>";
            }

            var3 = var3 + "</result>";
            return var3;
        }
    }

    public String SynDepartment(String var1, String var2) throws Exception {
        var1 = this.getClientIpXfire();
        String var3 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>";
        if (("," + this.configip + ",").indexOf("," + var1 + ",") < 0) {
            this.writeLog("IP" + var1 + "不在设定的范围内,无权限访问!");
            var3 = var3 + "<message value=\"2\">IP不在设定的范围内,无权限访问</message></result>";
            return var3;
        } else {
            ParseXml var4 = new ParseXml();
            HrmServiceAction var5 = new HrmServiceAction();

            try {
                String var7 = "";
                var4.parseOrg(var2);
                this.h_orgInfo = var4.getH_orgInfo();
                this.h_addOrg = var4.getH_addOrg();
                this.h_updateOrg = var4.getH_updateOrg();
                this.h_delOrg = var4.getH_delOrg();

                int var6;
                OrgXmlBean var8;
                boolean var9;
                for (var6 = 0; var6 < this.h_addOrg.size(); ++var6) {
                    var7 = (String) this.h_addOrg.get(var6);
                    var8 = (OrgXmlBean) this.h_orgInfo.get(var7);
                    var9 = var5.addDepartment(var1, var8);
                    if (var9) {
                        var3 = var3 + "<message value=\"1\">插入成功</message>";
                    } else {
                        var3 = var3 + "<message value=\"0\">插入失败</message>";
                    }
                }

                for (var6 = 0; var6 < this.h_updateOrg.size(); ++var6) {
                    var7 = (String) this.h_updateOrg.get(var6);
                    var8 = (OrgXmlBean) this.h_orgInfo.get(var7);
                    var9 = var5.editDepartment(var1, var8);
                    if (var9) {
                        var3 = var3 + "<message value=\"1\">编辑成功</message>";
                    } else {
                        var3 = var3 + "<message value=\"0\">编辑失败</message>";
                    }
                }

                for (var6 = 0; var6 < this.h_delOrg.size(); ++var6) {
                    var7 = (String) this.h_delOrg.get(var6);
                    var8 = (OrgXmlBean) this.h_orgInfo.get(var7);
                    var9 = var5.delDepartment(var1, var8);
                    if (var9) {
                        var3 = var3 + "<message value=\"1\">删除成功</message>";
                    } else {
                        var3 = var3 + "<message value=\"0\">删除失败</message>";
                    }
                }

                try {
                    DepartmentComInfo var12 = new DepartmentComInfo();
                    var12.removeCompanyCache();
                    (new AppDetachComInfo()).initSubDepAppData();
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            } catch (Exception var11) {
                var11.printStackTrace();
                var3 = var3 + "<message value=\"0\">数据异常</message>";
            }

            var3 = var3 + "</result>";
            return var3;
        }
    }

    public String SynJobtitle(String var1, String var2) throws Exception {
        var1 = this.getClientIpXfire();
        String var3 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>";
        if (("," + this.configip + ",").indexOf("," + var1 + ",") < 0) {
            this.writeLog("IP" + var1 + "不在设定的范围内,无权限访问!");
            var3 = var3 + "<message value=\"2\">IP不在设定的范围内,无权限访问</message></result>";
            return var3;
        } else {
            ParseXml var4 = new ParseXml();
            HrmServiceAction var5 = new HrmServiceAction();

            try {
                String var7 = "";
                var4.parseJobTitle(var2);
                this.h_orgInfo = var4.getH_orgInfo();
                this.h_addOrg = var4.getH_addOrg();
                this.h_updateOrg = var4.getH_updateOrg();
                this.h_delOrg = var4.getH_delOrg();

                int var6;
                JobTitleBean var8;
                boolean var9;
                for (var6 = 0; var6 < this.h_addOrg.size(); ++var6) {
                    var7 = (String) this.h_addOrg.get(var6);
                    var8 = (JobTitleBean) this.h_orgInfo.get(var7);
                    var9 = var5.addJobTitle(var1, var8);
                    if (var9) {
                        var3 = var3 + "<message value=\"1\">插入成功</message>";
                    } else {
                        var3 = var3 + "<message value=\"0\">插入失败</message>";
                    }
                }

                for (var6 = 0; var6 < this.h_updateOrg.size(); ++var6) {
                    var7 = (String) this.h_updateOrg.get(var6);
                    var8 = (JobTitleBean) this.h_orgInfo.get(var7);
                    var9 = var5.editJobTitle(var1, var8);
                    if (var9) {
                        var3 = var3 + "<message value=\"1\">编辑成功</message>";
                    } else {
                        var3 = var3 + "<message value=\"0\">编辑失败</message>";
                    }
                }

                for (var6 = 0; var6 < this.h_delOrg.size(); ++var6) {
                    var7 = (String) this.h_delOrg.get(var6);
                    var8 = (JobTitleBean) this.h_orgInfo.get(var7);
                    var9 = var5.delJobTitle(var1, var8);
                    if (var9) {
                        var3 = var3 + "<message value=\"1\">删除成功</message>";
                    } else {
                        var3 = var3 + "<message value=\"0\">删除失败</message>";
                    }
                }

                try {
                    JobTitlesComInfo var12 = new JobTitlesComInfo();
                    var12.removeJobTitlesCache();
                } catch (Exception var10) {
                    var10.printStackTrace();
                }
            } catch (Exception var11) {
                var11.printStackTrace();
                var3 = var3 + "<message value=\"0\">数据异常</message>";
            }

            var3 = var3 + "</result>";
            return var3;
        }
    }

    public String SynHrmResource(String var1, String var2) throws Exception {
        var1 = this.getClientIpXfire();
        String var3 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>";
        if (("," + this.configip + ",").indexOf("," + var1 + ",") < 0) {
            this.writeLog("IP" + var1 + "不在设定的范围内,无权限访问!");
            var3 = var3 + "<message value=\"2\">IP不在设定的范围内,无权限访问</message></result>";
            return var3;
        } else {
            ParseXml var4 = new ParseXml();

            try {
                IHrmImportProcess var5 = (IHrmImportProcess) Class.forName("weaver.join.hrm.in.processImpl.HrmImportProcess").newInstance();
                var4.parseHrmResource(var2);
                this.h_orgInfo_add = var4.getH_orgInfo_add();
                this.h_orgInfo_update = var4.getH_orgInfo_update();
                List var6 = var5.processMap("workcode", this.h_orgInfo_add, "add");
                new ImportLog();
                String var8 = "";
                String var9 = "";

                ImportLog var7;
                int var10;
                for (var10 = 0; var10 < var6.size(); ++var10) {
                    var7 = (ImportLog) var6.get(var10);
                    if (var7.getStatus().equals("失败")) {
                        var8 = var8 + (var7.getWorkCode() != null ? var7.getWorkCode() : "") + "|" + (var7.getLastname() != null ? var7.getLastname() : "") + "|" + var7.getOperation() + "|" + var7.getStatus() + "|" + var7.getReason() + ",";
                    }
                }

                if (!"".equals(var8)) {
                    var3 = var3 + "<message value=\"0\">" + var8 + "</message>";
                }

                for (var10 = 0; var10 < var6.size(); ++var10) {
                    var7 = (ImportLog) var6.get(var10);
                    if (var7.getStatus().equals("成功")) {
                        var9 = var9 + (var7.getWorkCode() != null ? var7.getWorkCode() : "") + "|" + (var7.getLastname() != null ? var7.getLastname() : "") + "|" + var7.getOperation() + "|" + var7.getStatus() + "|" + var7.getReason() + ",";
                    }
                }

                if (!"".equals(var9)) {
                    var3 = var3 + "<message value=\"1\">" + var9 + "</message>";
                }

                List var16 = var5.processMap("workcode", this.h_orgInfo_update, "update");
                new ImportLog();
                String var12 = "";
                String var13 = "";

                ImportLog var11;
                int var14;
                for (var14 = 0; var14 < var16.size(); ++var14) {
                    var11 = (ImportLog) var16.get(var14);
                    if (var11.getStatus().equals("失败")) {
                        var12 = var12 + (var11.getWorkCode() != null ? var11.getWorkCode() : "") + "|" + (var11.getLastname() != null ? var11.getLastname() : "") + "|" + var11.getOperation() + "|" + var11.getStatus() + "|" + var11.getReason() + ",";
                    }
                }

                if (!"".equals(var12)) {
                    var3 = var3 + "<message value=\"0\">" + var12 + "</message>";
                }

                for (var14 = 0; var14 < var16.size(); ++var14) {
                    var11 = (ImportLog) var16.get(var14);
                    if (var11.getStatus().equals("成功")) {
                        var13 = var13 + (var11.getWorkCode() != null ? var11.getWorkCode() : "") + "|" + (var11.getLastname() != null ? var11.getLastname() : "") + "|" + var11.getOperation() + "|" + var11.getStatus() + "|" + var11.getReason() + ",";
                    }
                }

                if (!"".equals(var13)) {
                    var3 = var3 + "<message value=\"1\">" + var13 + "</message>";
                }
            } catch (Exception var15) {
                var15.printStackTrace();
                var3 = var3 + "<message value=\"0\">数据异常</message>";
            }

            var3 = var3 + "</result>";
            return var3;
        }
    }

    public SubCompanyBean[] getHrmSubcompanyInfo(String var1) throws Exception {
            RecordSet var2 = new RecordSet();
            RecordSet var3 = new RecordSet();
            String var4 = "";
            ArrayList var5 = new ArrayList();
            var2.executeSql("select * from HrmSubCompany order by id");

            SubCompanyBean var6;
            for (; var2.next(); var5.add(var6)) {
                var6 = new SubCompanyBean();
                var6.set_subcompanyid(Util.null2String(var2.getString("id")));
                var6.set_code(Util.null2String(var2.getString("subcompanycode")));
                var6.set_shortname(Util.null2String(var2.getString("subcompanyname")));
                var6.set_fullname(Util.null2String(var2.getString("subcompanydesc")));
                var6.set_supsubcompanyid(Util.null2String(var2.getString("supsubcomid")));
                var6.set_showorder(Util.null2String(var2.getString("showorder")));
                var6.set_canceled(Util.null2String(var2.getString("canceled")));
                var6.set_website(Util.null2String(var2.getString("urls")));
                if (var3.getDBType().equals("oracle")) {
                    var4 = "select * from (select operatedate ||' '|| operatetime from sysMaintenanceLog where operateitem = 11 and relatedid = " + var2.getString("id") + " order by id desc) where rownum = 1";
                } else {
                    var4 = "select top 1 operatedate +' '+ operatetime from sysMaintenanceLog where operateitem = 11 and relatedid = " + var2.getString("id") + " order by id desc";
                }

                var3.executeSql(var4);
                if (var3.next()) {
                    var6.setLastChangdate(Util.null2String(var3.getString(1)));
                }
            }

            SubCompanyBean[] var8 = new SubCompanyBean[var5.size()];

            for (int var7 = 0; var7 < var5.size(); ++var7) {
                var8[var7] = (SubCompanyBean) var5.get(var7);
            }

            return var8;

    }

    public DepartmentBean[] getHrmDepartmentInfo(String var1, String var2) throws Exception {
        RecordSet var3 = new RecordSet();
        RecordSet var4 = new RecordSet();
        String var5 = "";
        ArrayList var6 = new ArrayList();
        String var7 = "";
        if (!"".equals(Util.null2String(var2))) {
            var7 = " where subcompanyid1 in (" + var2 + ")";
        }

        String var8 = "select * from hrmdepartment " + var7 + " order by id";
        var3.executeSql(var8);

        DepartmentBean var9;
        for (; var3.next(); var6.add(var9)) {
            var9 = new DepartmentBean();
            var9.set_departmentid(Util.null2String(var3.getString("id")));
            var9.set_subcompanyid(Util.null2String(var3.getString("subcompanyid1")));
            var9.set_code(Util.null2String(var3.getString("departmentcode")));
            var9.set_shortname(Util.null2String(var3.getString("departmentname")));
            var9.set_fullname(Util.null2String(var3.getString("departmentmark")));
            var9.set_supdepartmentid(Util.null2String(var3.getString("supdepid")));
            var9.set_showorder(Util.null2String(var3.getString("showorder")));
            var9.set_canceled(Util.null2String(var3.getString("canceled")));
            if (var4.getDBType().equals("oracle")) {
                var5 = "select * from (select operatedate ||' '|| operatetime from sysMaintenanceLog where operateitem = 12 and relatedid = " + var3.getString("id") + " order by id desc) where rownum = 1";
            } else {
                var5 = "select top 1 operatedate +' '+ operatetime from sysMaintenanceLog where operateitem = 12 and relatedid = " + var3.getString("id") + " order by id desc";
            }

            var4.executeSql(var8);
            if (var4.next()) {
                var9.setLastChangdate(Util.null2String(var4.getString(1)));
            }
        }

        DepartmentBean[] var11 = new DepartmentBean[var6.size()];

        for (int var10 = 0; var10 < var6.size(); ++var10) {
            var11[var10] = (DepartmentBean) var6.get(var10);
        }

        return var11;

    }

    public JobTitleBean[] getHrmJobTitleInfo(String var1, String var2, String var3) throws Exception {
        var1 = this.getClientIpXfire();
        if (("," + this.configip + ",").indexOf("," + var1 + ",") < 0) {
            this.writeLog("IP" + var1 + "不在设定的范围内，无权限访问!");
            return null;
        } else {
            RecordSet var4 = new RecordSet();
            RecordSet var5 = new RecordSet();
            String var6 = "";
            ArrayList var7 = new ArrayList();
            String var8 = " where 1=1 ";
            String var9 = "select * from hrmjobtitles " + var8 + " order by id";
            var4.executeSql(var9);

            JobTitleBean var10;
            for (; var4.next(); var7.add(var10)) {
                var10 = new JobTitleBean();
                var10.set_jobtitleid(Util.null2String(var4.getString("id")));
                var10.set_shortname(Util.null2String(var4.getString("jobtitlename")));
                var10.set_fullname(Util.null2String(var4.getString("jobtitlemark")));
                var10.set_departmentid(Util.null2String(var4.getString("jobdepartmentid")));
                var10.set_jobtitleremark(Util.null2String(var4.getString("jobtitleremark")));
                var10.set_jobresponsibility(Util.null2String(var4.getString("jobresponsibility")));
                var10.set_jobcompetency(Util.null2String(var4.getString("jobcompetency")));
                var10.set_jobdoc(Util.null2String(var4.getString("jobdoc")));
                if (var5.getDBType().equals("oracle")) {
                    var6 = "select * from (select operatedate ||' '|| operatetime from sysMaintenanceLog where operateitem = 26 and relatedid = " + var4.getString("id") + " order by id desc) where rownum = 1";
                } else {
                    var6 = "select top 1 operatedate +' '+ operatetime from sysMaintenanceLog where operateitem = 26 and relatedid = " + var4.getString("id") + " order by id desc";
                }

                var5.executeSql(var9);
                if (var5.next()) {
                    var10.set_lastChangdate(Util.null2String(var5.getString(1)));
                }
            }

            JobTitleBean[] var12 = new JobTitleBean[var7.size()];

            for (int var11 = 0; var11 < var7.size(); ++var11) {
                var12[var11] = (JobTitleBean) var7.get(var11);
            }

            return var12;
        }
    }

    public UserBean[] getHrmUserInfo(String var1, String var2, String var3, String var4, String var5, String var6) throws Exception {
        var1 = this.getClientIpXfire();
        if (("," + this.configip + ",").indexOf("," + var1 + ",") < 0) {
            this.writeLog("IP" + var1 + "不在设定的范围内，无权限访问!");
            return null;
        } else {
            RecordSet var7 = new RecordSet();
            RecordSet var8 = new RecordSet();
            SubCompanyComInfo var9 = new SubCompanyComInfo();
            DepartmentComInfo var10 = new DepartmentComInfo();
            HashMap var11 = new HashMap();
            HashMap var12 = new HashMap();
            var8.executeSql("select id, subcompanycode from HrmSubCompany ");
            if (var8.next()) {
                var11.put(var8.getInt("id"), var8.getString("subcompanycode"));
            }

            var8.executeSql("select id, departmentcode from hrmdepartment ");
            if (var8.next()) {
                var12.put(var8.getInt("id"), var8.getString("departmentcode"));
            }

            ArrayList var13 = new ArrayList();
            String var14 = " where 1=1 ";
            if (!"".equals(Util.null2String(var2))) {
                var14 = var14 + " and workcode ='" + var2 + "'";
            }

            if (!"".equals(Util.null2String(var3))) {
                var14 = var14 + " and subcompanyid1 in (" + var3 + ")";
            }

            if (!"".equals(Util.null2String(var4))) {
                var14 = var14 + " and departmentid in (" + var4 + ")";
            }

            if (!"".equals(Util.null2String(var5))) {
                var14 = var14 + " and jobtitle in (" + var5 + ")";
            }

            if (!"".equals(Util.null2String(var6))) {
                var14 = var14 + " and lastmoddate = '" + var6 + "'";
            }

            String var15 = "select * from HrmResource " + var14 + " order by id";
            var7.executeSql(var15);

            while (var7.next()) {
                UserBean var16 = new UserBean();
                var16.setUserid(var7.getInt("id"));
                var16.setLastname(Util.null2String(var7.getString("lastname")));
                var16.setLoginid(Util.null2String(var7.getString("loginid")));
                var16.setPassword(Util.null2String(var7.getString("password")));
                String var17 = "男";
                String var18 = Util.null2String(var7.getString("sex"));
                if ("1".equals(var18)) {
                    var17 = "女";
                }

                var16.setSex(var17);
                var16.setTelephone(Util.null2String(var7.getString("telephone")));
                var16.setMobile(Util.null2String(var7.getString("mobile")));
                var16.setMobilecall(Util.null2String(var7.getString("mobilecall")));
                var16.setEmail(Util.null2String(var7.getString("email")));
                var16.setStartdate(Util.null2String(var7.getString("startdate")));
                var16.setEnddate(Util.null2String(var7.getString("enddate")));
                var16.setJobtitle(Util.null2String(var7.getString("jobtitle")));
                var16.setSeclevel(Util.null2String(var7.getString("seclevel")));
                var16.setSystemlanguage(var7.getString("language"));
                var16.setSubcompanyid1(var7.getString("subcompanyid1"));
                var16.setDepartmentid(var7.getString("departmentid"));
                var16.setSubcompanyname(var9.getSubCompanyname(var7.getString("subcompanyid1")));
                var16.setDepartmentname(var10.getDepartmentname(var7.getString("departmentid")));
                var16.setSubcompanycode((String) var11.get(var7.getInt("subcompanyid1")));
                var16.setDepartmentcode((String) var12.get(var7.getInt("departmentid")));
                var16.setManagerid(Util.null2String(var7.getString("managerid")));
                var16.setAssistantid(Util.null2String(var7.getString("assistantid")));
                var16.setCertificatenum(Util.null2String(var7.getString("certificatenum")));
                var16.setWorkcode(Util.null2String(var7.getString("workcode")));
                var16.setStatus(var7.getString("status"));
                var16.setDsporder(var7.getFloat("dsporder"));
                String var19 = "未婚";
                String var20 = Util.null2String(var7.getString("lastname"));
                if (var20.equals("1")) {
                    var19 = "已婚";
                }

                if (var20.equals("2")) {
                    var19 = "离婚";
                }

                var16.setMaritalstatus(var19);
                var16.setBirthday(Util.null2String(var7.getString("birthday")));
                var16.setCreatedate(Util.null2String(var7.getString("createdate")));
                var16.setLastChangdate(Util.null2String(var7.getString("lastmoddate")));
                var16.setAccounttype(var7.getInt("accounttype"));
                var16.setFax(Util.null2String(var7.getString("fax")));
                var16.setEducationlevel(Util.null2String(var7.getString("educationlevel")));
                var16.setDegree(Util.null2String(var7.getString("degree")));
                var16.setFolk(Util.null2String(var7.getString("folk")));
                var16.setNativeplace(Util.null2String(var7.getString("nativeplace")));
                var16.setRegresidentplace(Util.null2String(var7.getString("regresidentplace")));
                var16.setCertificatenum(Util.null2String(var7.getString("certificatenum")));
                var16.setPolicy(Util.null2String(var7.getString("policy")));
                var16.setBememberdate(Util.null2String(var7.getString("bememberdate")));
                var16.setBepartydate(Util.null2String(var7.getString("bepartydate")));
                var16.setIslabouunion(Util.null2String(var7.getString("islabouunion")));
                var16.setHealthinfo(Util.null2String(var7.getString("healthinfo")));
                var16.setHeight(Util.null2String(var7.getString("height")));
                var16.setWeight(Util.null2String(var7.getString("weight")));
                var16.setResidentplace(Util.null2String(var7.getString("residentplace")));
                var16.setHomeaddress(Util.null2String(var7.getString("homeaddress")));
                var16.setJobcall(var7.getString("jobcall"));
                var16.setJoblevel(var7.getString("joblevel"));
                var16.setJobactivitydesc(var7.getString("jobactivitydesc"));
                var16.setLocationid(var7.getString("locationid"));
                var16.setWorkroom(var7.getString("workroom"));
                var16.setTempresidentnumber(var7.getString("tempresidentnumber"));
                var13.add(var16);
            }

            UserBean[] var21 = new UserBean[var13.size()];

            for (int var22 = 0; var22 < var13.size(); ++var22) {
                var21[var22] = (UserBean) var13.get(var22);
            }

            return var21;
        }
    }

    public boolean checkUser(String var1, String var2, String var3) throws Exception {
        var1 = this.getClientIpXfire();
        if (("," + this.configip + ",").indexOf("," + var1 + ",") < 0) {
            this.writeLog("IP" + var1 + "不在设定的范围内，无权限访问!");
            return false;
        } else {
            RecordSet var4 = new RecordSet();
            var4.executeSql("select id from hrmresource where loginid='" + var2 + "' and password='" + Util.getEncrypt(var3) + "'");
            return var4.next();
        }
    }

    public boolean changeUserPassword(String var1, String var2, String var3) throws Exception {
        var1 = this.getClientIpXfire();
        if (("," + this.configip + ",").indexOf("," + var1 + ",") < 0) {
            this.writeLog("IP" + var1 + "不在设定的范围内，无权限访问!");
            return false;
        } else {
            boolean var4 = false;
            if (var2 != null && !"".equals(var2)) {
                ResourceComInfo var5 = new ResourceComInfo();
                RecordSet var6 = new RecordSet();
                var4 = var6.executeSql("update hrmresource set password = '" + Util.getEncrypt(var3) + "' where workcode='" + var2 + "'");
                var5.removeResourceCache();
            }

            return var4;
        }
    }

    public String getHrmDepartmentInfoXML(String var1, String var2) throws Exception {
        DepartmentBean[] var3 = this.getHrmDepartmentInfo(var1, var2);
        HrmServiceXmlUtil var4 = HrmServiceXmlUtil.getInstance();
        String var5 = var4.objToXml(var3);
        return var5;
    }

    public String getHrmJobTitleInfoXML(String var1, String var2, String var3) throws Exception {
        JobTitleBean[] var4 = this.getHrmJobTitleInfo(var1, var2, var3);
        HrmServiceXmlUtil var5 = HrmServiceXmlUtil.getInstance();
        String var6 = var5.objToXml(var4);
        return var6;
    }

    public String getHrmSubcompanyInfoXML(String var1) throws Exception {
        SubCompanyBean[] var2 = this.getHrmSubcompanyInfo(var1);
        HrmServiceXmlUtil var3 = HrmServiceXmlUtil.getInstance();
        String var4 = var3.objToXml(var2);
        return var4;
    }

    public String getHrmUserInfoXML(String var1, String var2, String var3, String var4, String var5, String var6) throws Exception {
        UserBean[] var7 = this.getHrmUserInfo(var1, var2, var3, var4, var5, var6);
        HrmServiceXmlUtil var8 = HrmServiceXmlUtil.getInstance();
        String var9 = var8.objToXml(var7);
        return var9;
    }

    public String getClientIpXfire() {
        String var1 = "";

        try {
            HttpServletRequest var2 = XFireServletController.getRequest();
            var1 = this.getRemoteAddress(var2);
            return var1;
        } catch (Exception var3) {
            var3.printStackTrace();
            return "";
        }
    }

    private String getRemoteAddress(HttpServletRequest var1) {
        String var2 = var1.getHeader("x-forwarded-for");
        if (var2 == null || var2.length() == 0 || var2.equalsIgnoreCase("unknown")) {
            var2 = var1.getHeader("Proxy-Client-IP");
        }

        if (var2 == null || var2.length() == 0 || var2.equalsIgnoreCase("unknown")) {
            var2 = var1.getHeader("WL-Proxy-Client-IP");
        }

        if (var2 == null || var2.length() == 0 || var2.equalsIgnoreCase("unknown")) {
            var2 = var1.getRemoteAddr();
        }

        if (var2.indexOf(",") >= 0) {
            var2 = var2.substring(0, var2.indexOf(","));
        }

        return var2;
    }
}
