package com.weavernorth.B1.sso;

import com.weavernorth.B1.exChange.GetDoubleCount;
import ln.LN;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.StaticObj;
import weaver.general.Util;
import weaver.hrm.OnLineMonitor;
import weaver.hrm.User;
import weaver.systeminfo.SysMaintenanceLog;
import weaver.systeminfo.template.UserTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class XrSSOFilter extends BaseBean implements Filter {

    /**
     * servlet 上下文
     */
    private ServletContext application;


    /**
     * 初始化方法
     */
    public void init(FilterConfig filterConfig) {
        this.application = filterConfig.getServletContext();
    }

    /**
     * 业务处理方法 业务逻辑实现
     */
    public void doFilter(ServletRequest ServletRequest, ServletResponse ServletResponse, FilterChain FilterChain) throws ServletException, IOException {
        writeLog("进入信任单点filter================================");
        HttpServletRequest request = (HttpServletRequest) ServletRequest;
        HttpServletResponse response = (HttpServletResponse) ServletResponse;
        if (StringUtils.isNotBlank(request.getHeader("x-requested-with"))) {
            request.setCharacterEncoding("UTF-8");
        }

        String userInfoXml = request.getParameter("userInfoXml");
        userInfoXml = "<test>" + userInfoXml + "</test>";
        String workCode = "";
        try {
            Document doc = DocumentHelper.parseText(userInfoXml);
            Element rootElt = doc.getRootElement();
            workCode = rootElt.elementText("uri");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String workFlowUrl = "/wui/main.jsp?templateId=1";

        writeLog("请求xml》》》" + userInfoXml);
        writeLog("请求工号》》》" + workCode);

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
                if (!message.equals("1")) {
                    response.sendRedirect("/system/InLicense.jsp");
                    return;
                } else {
                    StaticObj.getInstance().putObject("isLicense", "true");
                }
            }
        }

        //登录名为空
        if (StringUtils.isBlank(workCode)) {
            response.sendRedirect("/login/Login.jsp?logintype=1");
        }

        RecordSet rs = new RecordSet();
        rs.executeSql("select * from HrmResource where workcode = lower('" + workCode + "') and status < 4 and (accounttype !=1 or accounttype is null)");

        User user_new;
        if (rs.next()) {
            //访问交换系统，获取待接收件数量
            try {
                GetDoubleCount getDoubleCount = new GetDoubleCount();
                getDoubleCount.userGetCount(workCode);
            } catch (Exception e) {
                writeLog("获取交换信息出错： " + e);
            }
            // OA有相关人员
            User user = (User) request.getSession(true).getAttribute("weaver_user@bean");
            // 用户session不存在 或者 用户session中的用户名和此次登录的用户名不一致，要重启构造用户session
            if (user == null || !user.getLoginid().equals(workCode)) {
                //用户登录
                writeLog("赋值一堆============");
                user_new = new User();
                user_new.setUid(rs.getInt("id"));
                user_new.setLoginid(rs.getString("loginid"));
                user_new.setFirstname(rs.getString("firstname"));
                user_new.setLastname(rs.getString("lastname"));
                user_new.setAliasname(rs.getString("aliasname"));
                user_new.setTitle(rs.getString("title"));
                user_new.setTitlelocation(rs.getString("titlelocation"));
                user_new.setSex(rs.getString("sex"));
                user_new.setPwd(rs.getString("password"));
                String languageidweaver = rs.getString("systemlanguage");
                user_new.setLanguage(Util.getIntValue(languageidweaver, 0));
                user_new.setTelephone(rs.getString("telephone"));
                user_new.setMobile(rs.getString("mobile"));
                user_new.setMobilecall(rs.getString("mobilecall"));
                user_new.setEmail(rs.getString("email"));
                user_new.setCountryid(rs.getString("countryid"));
                user_new.setLocationid(rs.getString("locationid"));
                user_new.setResourcetype(rs.getString("resourcetype"));
                user_new.setStartdate(rs.getString("startdate"));
                user_new.setEnddate(rs.getString("enddate"));
                user_new.setContractdate(rs.getString("contractdate"));
                user_new.setJobtitle(rs.getString("jobtitle"));
                user_new.setJobgroup(rs.getString("jobgroup"));
                user_new.setJobactivity(rs.getString("jobactivity"));
                user_new.setJoblevel(rs.getString("joblevel"));
                user_new.setSeclevel(rs.getString("seclevel"));
                user_new.setUserDepartment(Util.getIntValue(rs.getString("departmentid"), 0));
                user_new.setUserSubCompany1(Util.getIntValue(rs.getString("subcompanyid1"), 0));
                user_new.setUserSubCompany2(Util.getIntValue(rs.getString("subcompanyid2"), 0));
                user_new.setUserSubCompany3(Util.getIntValue(rs.getString("subcompanyid3"), 0));
                user_new.setUserSubCompany4(Util.getIntValue(rs.getString("subcompanyid4"), 0));
                user_new.setManagerid(rs.getString("managerid"));
                user_new.setAssistantid(rs.getString("assistantid"));
                user_new.setPurchaselimit(rs.getString("purchaselimit"));
                user_new.setCurrencyid(rs.getString("currencyid"));
                user_new.setLastlogindate(rs.getString("currentdate"));
                user_new.setLogintype("1");
                user_new.setAccount(rs.getString("account"));

                user_new.setLoginip(request.getRemoteAddr());
                request.getSession(true).setMaxInactiveInterval(60 * 60 * 24);
                request.getSession(true).setAttribute("weaver_user@bean", user_new);
                request.getSession(true).setAttribute("browser_isie", getisIE(request));

                request.getSession(true).setAttribute("moniter", new OnLineMonitor("" + user_new.getUID(), user_new.getLoginip()));
                Util.setCookie(response, "loginfileweaver", "/main.jsp", 172800);
                Util.setCookie(response, "loginidweaver", "" + user_new.getUID(), 172800);
                Util.setCookie(response, "languageidweaver", languageidweaver, 172800);

                Map logmessages = (Map) application.getAttribute("logmessages");
                if (logmessages == null) {
                    logmessages = new HashMap();
                    logmessages.put(String.valueOf(user_new.getUID()), "");
                    application.setAttribute("logmessages", logmessages);
                }

                request.getSession(true).setAttribute("logmessage", getLogMessage(String.valueOf(user_new.getUID())));

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
                user_new = user;
            }

            response.sendRedirect(workFlowUrl);

            // 如果登录页面是login.jsp、verifylogin.jsp，则跳转到main.jsp
            if (currentPage.contains("/login.jsp") || currentPage.contains("/verifylogin.jsp") || currentPage.contains("/refresh.jsp")) {
                String tourl = "";
                // 用户登录， 用户的登录后的页面
                UserTemplate ut = new UserTemplate();
                ut.getTemplateByUID(user_new.getUID(), user_new.getUserSubCompany1());
                int templateId = ut.getTemplateId();
                int extendTempletid = ut.getExtendtempletid();
                String defaultHp = ut.getDefaultHp();
                request.getSession(true).setAttribute("defaultHp", defaultHp);

                if (extendTempletid != 0) {
                    rs.executeSql("select id,extendname,extendurl from extendHomepage  where id=" + extendTempletid);
                    if (rs.next()) {
                        String extendurl = Util.null2String(rs.getString("extendurl"));
                        rs.executeSql("select * from extendHpWebCustom where templateid=" + templateId);
                        String defaultshow = "";
                        if (rs.next()) {
                            defaultshow = Util.null2String(rs.getString("defaultshow"));
                        }
                        String param = "";
                        if (!defaultshow.equals("")) {
                            param = "&" + defaultshow.substring(defaultshow.indexOf("?") + 1);
                        }
                        tourl = "/login/RemindLogin.jsp?RedirectFile=" + extendurl + "/index.jsp?templateId=" + templateId + param;
                    }
                } else {
                    tourl = "/wui/main.jsp";
                }
                response.sendRedirect(tourl);
            }
        } else {
            // OA中查无此人，必须重新登录
            response.sendRedirect("/login/Login.jsp?logintype=1");
        }
        FilterChain.doFilter(request, response);
    }

    public void destroy() {
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
