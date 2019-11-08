package com.weavernorth;

import ln.LN;
import org.apache.commons.lang.StringUtils;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.StaticObj;
import weaver.general.Util;
import weaver.hrm.OnLineMonitor;
import weaver.hrm.User;
import weaver.login.Account;
import weaver.systeminfo.SysMaintenanceLog;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class TestFilter extends BaseBean implements Filter {

    /**
     * servlet 上下文
     */
    private ServletContext application;

    /**
     * 初始化方法
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.application = filterConfig.getServletContext();
    }

    /**
     * 业务处理方法 业务逻辑实现
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        writeLog("进入高等教育单点filter================================");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (StringUtils.isNotBlank(request.getHeader("x-requested-with"))) {
            request.setCharacterEncoding("UTF-8");
        }

        // 登录名
        String loginName = request.getParameter("loginName");

        // 默认url 登录主页
        String workFlowUrl = "/wui/main.jsp?templateId=1";

        writeLog("loginName》》》" + loginName);

        //检查license
        String currentPage = request.getServletPath().toLowerCase();
        if (!currentPage.toLowerCase().contains(("/system/inlicense.jsp")) && !currentPage.toLowerCase().contains(("/system/licenseoperation.jsp"))) {
            if (!currentPage.toLowerCase().contains(("/mobile/plugin/"))) {
                // 不是手机端调用接口，判断license
                Calendar today1 = Calendar.getInstance();
                String currentdate1 = Util.add0(today1.get(Calendar.YEAR), 4) + "-" + Util.add0(today1.get(Calendar.MONTH) + 1, 2) + "-" + Util.add0(today1.get(Calendar.DAY_OF_MONTH), 2);
                String message;
                LN ckLicense = new LN();
                message = ckLicense.CkLicense(currentdate1);
                if (!"1".equals(message)) {
                    response.sendRedirect("/system/InLicense.jsp");
                    return;
                } else {
                    StaticObj.getInstance().putObject("isLicense", "true");
                }
            }
        }

        //登录名为空
        if (StringUtils.isBlank(loginName)) {
            response.sendRedirect("/login/Login.jsp?logintype=1");
        }

        RecordSet rs = new RecordSet();
        rs.executeSql("select * from HrmResource where loginid = lower('" + loginName + "') and status < 4 and (accounttype !=1 or accounttype is null)");

        User userNew;
        if (rs.next()) {

            // OA有相关人员
            User user = (User) request.getSession(true).getAttribute("weaver_user@bean");
            // 用户session不存在 或者 用户session中的用户名和此次登录的用户名不一致，要重启构造用户session
            if (user == null || !user.getLoginid().equals(loginName)) {
                //用户登录
                writeLog("授权执行============");
                userNew = new User();
                userNew.setUid(rs.getInt("id"));
                userNew.setLoginid(rs.getString("loginid"));
                userNew.setFirstname(rs.getString("firstname"));
                userNew.setLastname(rs.getString("lastname"));
                userNew.setAliasname(rs.getString("aliasname"));
                userNew.setTitle(rs.getString("title"));
                userNew.setTitlelocation(rs.getString("titlelocation"));
                userNew.setSex(rs.getString("sex"));
                userNew.setPwd(rs.getString("password"));
                String languageidweaver = rs.getString("systemlanguage");
                userNew.setLanguage(Util.getIntValue(languageidweaver, 0));
                userNew.setTelephone(rs.getString("telephone"));
                userNew.setMobile(rs.getString("mobile"));
                userNew.setMobilecall(rs.getString("mobilecall"));
                userNew.setEmail(rs.getString("email"));
                userNew.setCountryid(rs.getString("countryid"));
                userNew.setLocationid(rs.getString("locationid"));
                userNew.setResourcetype(rs.getString("resourcetype"));
                userNew.setStartdate(rs.getString("startdate"));
                userNew.setEnddate(rs.getString("enddate"));
                userNew.setContractdate(rs.getString("contractdate"));
                userNew.setJobtitle(rs.getString("jobtitle"));
                userNew.setJobgroup(rs.getString("jobgroup"));
                userNew.setJobactivity(rs.getString("jobactivity"));
                userNew.setJoblevel(rs.getString("joblevel"));
                userNew.setSeclevel(rs.getString("seclevel"));
                userNew.setUserDepartment(Util.getIntValue(rs.getString("departmentid"), 0));
                userNew.setUserSubCompany1(Util.getIntValue(rs.getString("subcompanyid1"), 0));
                userNew.setUserSubCompany2(Util.getIntValue(rs.getString("subcompanyid2"), 0));
                userNew.setUserSubCompany3(Util.getIntValue(rs.getString("subcompanyid3"), 0));
                userNew.setUserSubCompany4(Util.getIntValue(rs.getString("subcompanyid4"), 0));
                userNew.setManagerid(rs.getString("managerid"));
                userNew.setAssistantid(rs.getString("assistantid"));
                userNew.setPurchaselimit(rs.getString("purchaselimit"));
                userNew.setCurrencyid(rs.getString("currencyid"));
                userNew.setLastlogindate(rs.getString("currentdate"));
                userNew.setLogintype("1");
                userNew.setAccount(rs.getString("account"));

                userNew.setLoginip(request.getRemoteAddr());
                List<Account> childAccountList = getChildAccountList(userNew);//子账号相关设置
                request.getSession(true).setMaxInactiveInterval(60 * 60 * 24);
                request.getSession(true).setAttribute("weaver_user@bean", userNew);
                request.getSession(true).setAttribute("accounts", childAccountList);
                request.getSession(true).setAttribute("browser_isie", getisIE(request));

                request.getSession(true).setAttribute("moniter", new OnLineMonitor("" + userNew.getUID(), userNew.getLoginip()));
                Util.setCookie(response, "loginfileweaver", "/main.jsp", 172800);
                Util.setCookie(response, "loginidweaver", "" + userNew.getUID(), 172800);
                Util.setCookie(response, "languageidweaver", languageidweaver, 172800);

                Map logmessages = (Map) application.getAttribute("logmessages");
                if (logmessages == null) {
                    logmessages = new HashMap();
                    logmessages.put(String.valueOf(userNew.getUID()), "");
                    application.setAttribute("logmessages", logmessages);
                }

                request.getSession(true).setAttribute("logmessage", getLogMessage(String.valueOf(userNew.getUID())));

                // 登录日志
                SysMaintenanceLog log1 = new SysMaintenanceLog();
                log1.resetParameter();
                log1.setRelatedId(rs.getInt("id"));
                log1.setRelatedName((rs.getString("firstname") + " " + rs.getString("lastname")).trim());
                log1.setOperateType("6");
                log1.setOperateDesc("");
                log1.setOperateItem("60");
                log1.setOperateUserid(rs.getInt("id"));
                log1.setClientAddress(request.getRemoteAddr());
                try {
                    log1.setSysLogInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                writeLog("直接赋值=======");
                userNew = user;
            }
            //response.sendRedirect(workFlowUrl);

        } else {
            // OA中查无此人，必须重新登录
            response.sendRedirect("/login/Login.jsp?logintype=1");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private List<Account> getChildAccountList(User user) {
        List<Account> accounts = new ArrayList<Account>();
        int uid = user.getUID();
        RecordSet rs = new RecordSet();
        rs.executeSql("select * from hrmresource where status in (0,1,2,3) and (belongto = " + uid + " or id = " + uid + ")");
        while (rs.next()) {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setDepartmentid(rs.getInt("departmentid"));
            account.setJobtitleid(rs.getInt("JOBTITLE"));
            account.setSubcompanyid(rs.getInt("SUBCOMPANYID1"));
            account.setType(rs.getInt("ACCOUNTTYPE"));
            account.setAccount(Util.null2String(rs.getString("ACCOUNT")));
            accounts.add(account);
        }
        return accounts;
    }

    /**
     * 获取日志信息
     */
    private String getLogMessage(String uid) {
        String message = "";
        RecordSet rs = new RecordSet();
        String sqltmp = "";
        if (rs.getDBType().equals("oracle")) {
            sqltmp = "select * from (select * from SysMaintenanceLog where relatedid = " + uid + " and operatetype='6' and operateitem='60' order by id desc ) where rownum=1 ";
        } else if (rs.getDBType().equals("db2")) {
            sqltmp = "select * from SysMaintenanceLog where relatedid = " + uid + " and operatetype='6' and operateitem='60' order by id desc fetch first 1 rows only ";
        } else if (rs.getDBType().equals("mysql")) {
            sqltmp = "SELECT t2.* FROM (SELECT * FROM SysMaintenanceLog WHERE relatedid = " + uid + " and  operatetype='6' AND operateitem='60' ORDER BY id DESC) t2  LIMIT 1 ,1";
        } else {
            sqltmp = "select top 1 * from SysMaintenanceLog where relatedid = " + uid + " and operatetype='6' and operateitem='60' order by id desc";
        }

        rs.executeSql(sqltmp);
        if (rs.next()) {
            message = rs.getString("clientaddress") + " " + rs.getString("operatedate") + " " + rs.getString("operatetime");
        }

        return message;
    }

    /**
     * 判断浏览器是否为IE
     *
     * @param request
     * @return
     */
    private String getisIE(HttpServletRequest request) {
        String isIE = "true";
        String agent = request.getHeader("User-Agent").toLowerCase();
        if (!agent.contains("rv:11") && !agent.contains("msie")) {
            isIE = "false";
        }
        if (agent.contains("rv:11") || agent.indexOf("msie") > -1) {
            isIE = "true";
        }
        return isIE;
    }
}
