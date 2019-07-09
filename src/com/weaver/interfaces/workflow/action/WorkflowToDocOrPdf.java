package com.weaver.interfaces.workflow.action;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.*;
import com.weavernorth.OA2archives.OAPDF2archivesAction;
import com.weavernorth.OA2archives.util.ArchivesUtil;
import com.weavernorth.OA2archives.util.DocUtil;
import com.weavernorth.util.LogUtil;
import com.weavernorth.workflow.RequestComInfo;
import oracle.sql.CLOB;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.alioss.AliOSSObjectManager;
import weaver.common.StringUtil;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.docs.DocDetailLog;
import weaver.docs.category.DocTreeDocFieldComInfo;
import weaver.docs.category.SecCategoryComInfo;
import weaver.docs.docs.*;
import weaver.file.AESCoder;
import weaver.file.FileUpload;
import weaver.general.BaseBean;
import weaver.general.GCONST;
import weaver.general.TimeUtil;
import weaver.general.Util;
import weaver.hrm.resource.ResourceComInfo;
import weaver.interfaces.workflow.action.Action;
import weaver.interfaces.workflow.action.RequestDocPropManager;
import weaver.soa.workflow.request.RequestInfo;
import weaver.system.SystemComInfo;
import weaver.workflow.msg.PoppupRemindInfoUtil;
import weaver.workflow.request.RequestManager;

import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用来将流程存为文档，步骤为先将流程保存为html/pdf文件， 然后新建一个html/pdf文档，保存的html/pdf文件作为html/pdf文档的附件
 * Windows版本
 * status 0：生成html  1:生成pdf  2:生成带水印pdf 水印参数contentstr需有值
 * Date: 2017-02-24
 *
 * @author jiangshuiming
 * @version 1.0
 */

    public class WorkflowToDocOrPdf extends BaseBean implements Action {

    private RequestManager requestManager;

    private Log log = LogFactory.getLog(WorkflowToDocOrPdf.class.getName());

    private static ImageFileIdUpdate imageFileIdUpdate = new ImageFileIdUpdate();

    private static VersionIdUpdate versionIdUpdate = new VersionIdUpdate();

    private String isaesencrypt = "";
    private String aescode = "";
    private String status;
    private String contentstr;

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public void setStatus(String status_) {
        this.status = status_;
    }

    public String getStatus() {
        return this.status;
    }

    public String getContentstr() {
        return contentstr;
    }

    public void setContentstr(String contentstr) {
        this.contentstr = contentstr;
    }

    @Override
    public String execute(RequestInfo request) {
        writeLog("status===>" + status);
        this.requestManager = request.getRequestManager();
        String requestid = Util.null2String(request.getRequestid());
        String userid = getWorkflowEndPerson(requestid);/*Util.null2String("1");*/
        String requestname = Util.null2String(request.getDescription());
        LogUtil.doWriteLog("请求标题:----" + requestname);
        String workflowid = Util.null2String(request.getWorkflowid());
        //判断是否是归档 的流程
        if ("1".equals(RequestComInfo.getIsComplete(requestid))||!requestname.startsWith("Old-")) {
            if (null == status) {
                Start(requestid, userid, requestname, workflowid);
            }
            //刘立华新增开始20171220
            String gzzd_wfid = new BaseBean().getPropValue("wf_request", "gzzd_wfid");
            //判断规章制度流程不转pdf
            if((",".concat(gzzd_wfid).concat(",")).indexOf(","+workflowid+",") < 0){
                OAPDF2archivesAction OAPDF2archives = new OAPDF2archivesAction();
                String returnRes = OAPDF2archives.execute(request);
                if (returnRes.equals("0")) {
                    request.getRequestManager().setMessageid("-128599");
                    request.getRequestManager().setMessagecontent("流程无法归档，请联系管理员");
                    this.writeLog("流程无法归档，请联系管理员");
                    return returnRes;
                }
            }
        } else {
            writeLog("流程未归档或old-旧数据，无需转换");
        }
        //刘立华新增结束20171220
        return Action.SUCCESS;
    }

    /**
     * start
     *
     * @param requestid   流程id
     * @param userid      用户id
     * @param requestname 流程名 流程名作为文档的文档名
     * @param workflowid  流程类型id
     */
    public boolean Start(String requestid, String userid, String requestname,
                         String workflowid) {
        String wfdocpath = Util.null2String(getWfDocPath(workflowid));
        if (wfdocpath.equals("")) {
            log.error("流程保存为文档失败，因为未设置流程保存文档的目录");
            return false;
        }
        String url[] = getUrl(requestid, userid);
        if (url != null && url.length == 5) {//避免url为空时，出现异常
            boolean hasNull = false;//检查5个值是不是有空，如果有，就不导为文档了
            for (int cx = 0; cx < url.length; cx++) {
                if (url[cx] == null || "".equals(url[cx])) {
                    hasNull = true;
                }
            }
            if (hasNull == false) {
                getWorkflowHtml(url, requestid, requestname, workflowid, wfdocpath, userid);
            }
        }
        return true;
    }

    /**
     * 获取流程的url
     *
     * @param requestid 流程id
     * @param userid    当前用户
     * @return
     */
    public String[] getUrl(String requestid, String userid) {
        String sql = "";
        String tempurl = "";
        String loginid = "";
        String password = "";
        String para = "";
        String oaaddress = getHost();
        String params[] = new String[5];
        RecordSet rs = new RecordSet();

        if (oaaddress.equals("")) {
            log.error("流程保存为文档失败，因为系统未设置OA访问地址");
            return params;
        }

        sql = "select * from hrmresource where id = " + userid;
        rs.executeSql(sql);
        while (rs.next()) {
            loginid = rs.getString("loginid");
            password = rs.getString("password");
        }

        sql = "select * from HrmResourceManager where id = " + userid;
        rs.executeSql(sql);
        while (rs.next()) {
            loginid = rs.getString("loginid");
            password = rs.getString("password");
        }

        if (!loginid.equals("") && !password.equals("")) {
            para = "/workflow/request/ViewRequest.jsp?requestid=" + requestid
                    + "&para2=" + loginid + "&para3=" + password;
        } else {
            log.error("流程保存为文档失败，因为用户名和密码为空");
            return params;
        }


        tempurl = oaaddress
                + "/login/VerifyRtxLogin.jsp?urlfrom=workflowtodoc&para1="
                + para;

        params[0] = oaaddress + "/login/VerifyRtxLogin.jsp";
        params[1] = "workflowtodoc";
        params[2] = "/workflow/request/ViewRequest.jsp?isworkflowhtmldoc=1&requestid=" + requestid;
        params[3] = PoppupRemindInfoUtil.encrypt(loginid);//解决中文账号问题
        params[4] = password;


        return params;
    }

    /**
     * 得到系统域名
     *
     * @return系统域名
     */
    private String getHost() {
        RecordSet rs = new RecordSet();
        String host = "";
        rs.execute("select oaaddress from systemset");
        if (rs.next()) {
            host = Util.null2String(rs.getString("oaaddress"));
        }
        return host;
    }

    /**
     * 根据url读取html文件，并生成文档，放到指定的目录下
     *
     * @param url         流程页面的url
     * @param requestid   流程id
     * @param requestname 流程名称
     * @param workflowid  流程类型id
     * @param wfdocpath   文档存放的目录
     */
    public void getWorkflowHtml(String url[], String requestid,
                                String requestname, String workflowid, String wfdocpath, String userid) {
        HttpClient client = new HttpClient();
        LogUtil.doWriteLog("----jhy----" + url[0] + "--------" + url[1] + "--------" + url[2] + "--------" + url[3] + "--------" + url[4]);
        PostMethod method = new PostMethod(url[0]);
        method.getParams().setParameter("http.method.retry-handler",
                new DefaultHttpMethodRetryHandler(3, false));
        try {
            NameValuePair[] params = {
                    new NameValuePair("urlfrom", url[1]),
                    new NameValuePair("para1", url[2]),
                    new NameValuePair("para2", url[3]),
                    new NameValuePair("para3", url[4])};
            method.setRequestBody(params);
            int statusCode = client.executeMethod(method);
            String temppath = getFileSavePath();
            String filename = System.currentTimeMillis() + "";
            String htmlname = temppath + filename;
            File _temppath = new File(temppath);
            if (!_temppath.exists()) {
                _temppath.mkdirs();
            }

            if ((statusCode == 301) ||
                    (statusCode == 302)) {
                Header locationHeader = method.getResponseHeader("location");
                if (locationHeader != null) {
                    String tempurl = locationHeader.getValue();
                    tempurl = getFinallyUrl(client, tempurl);
                    tempurl = tempurl.replaceFirst(".jsp", "Iframe.jsp");
                    this.log.error(tempurl);
                    GetMethod g = new GetMethod(tempurl);
                    client.executeMethod(g);

                    OutputStream os = new FileOutputStream(htmlname);
                    SystemComInfo syscominfo = new SystemComInfo();
                    this.isaesencrypt = syscominfo.getIsaesencrypt();
                    this.aescode = Util.getRandomString(13);
                    if ("1".equals(this.isaesencrypt))
                        try {
                            os = AESCoder.encrypt(os, this.aescode);
                        } catch (Exception localException1) {
                        }
                    OutputStreamWriter output = new OutputStreamWriter(os, "UTF-8");
                    BufferedWriter bw = new BufferedWriter(output);

                    BufferedReader in = new BufferedReader(new InputStreamReader(g.getResponseBodyAsStream(), "UTF-8"));
                    StringBuffer sb = new StringBuffer();
                    String line = in.readLine();
                    String img_path = "";
                    String server = getServer();

                    while (line != null) {
                        line = line.trim();
                        if ((line.indexOf("</a>") < 0) || (line.indexOf("openSignPrint()") < 0) || (line.indexOf("onclick") < 0)) {
                            if ((line.indexOf("<img") < 0) || (line.indexOf("class=\"transto\"") < 0) || (line.indexOf("onclick") < 0) || (line.indexOf("transtoClick(this)") < 0)) {
                                if ((line.indexOf("var") >= 0) && (line.indexOf("bar") >= 0) && (line.indexOf("eval") >= 0) && (line.indexOf("handler") >= 0) && (line.indexOf("text") >= 0)) {
                                    sb.append("var bar=eval(\"[]\");\n");
                                } else if (line.indexOf("weaver.file.ImgFileDownload") > 0) {
                                    String[] imgArr = line.split("</img>");
                                    for (int i = 0; i < imgArr.length; i++) {
                                        String tempimgStr = imgArr[i];
                                        if (tempimgStr.indexOf("userid=") != -1) {
                                            String userId = tempimgStr.substring(tempimgStr.indexOf("userid=") + 7, tempimgStr.lastIndexOf("\""));
                                            img_path = getImgpath(userId);
                                            if (line.indexOf("src=") != -1 && line.indexOf("</img>") != -1 && img_path.indexOf("filesystem") != -1) {
                                                String repImgStr = tempimgStr.replace(tempimgStr.substring(tempimgStr.indexOf("src=") + 5, tempimgStr.lastIndexOf("\"")), server + img_path.substring(img_path.indexOf("filesystem") - 1).replace("\\", "/"));
                                                line = line.replace(tempimgStr, repImgStr);
                                            }
                                        }
                                    }
                                    sb.append(line + "\n");
                                }else if (line.indexOf("/weaver/weaver.file.FileDownload?fileid=") > 0) {
                                    try {
                                        String[] imgArr = line.split("</img>");
                                        for (int i = 0; i < imgArr.length; i++) {
                                            String tempimgStr = imgArr[i];
                                            if (tempimgStr.indexOf("fileid=") != -1) {
                                                tempimgStr = StringEscapeUtils.unescapeJava(tempimgStr);
                                                LogUtil.doWriteLog("-----------FileDownload-----------33:"+tempimgStr);
                                                String src = getImgStr(tempimgStr).get(0);
                                                LogUtil.doWriteLog("-----------FileDownload-----------src:"+src);
                                                String fileid = src.substring(src.indexOf("fileid=")+7, src.length());
                                                String path = DocUtil.getRealDoc(StringUtil.parseToInt(fileid));
                                                if (line.indexOf("src=") != -1 && path.indexOf("filesystem") != -1) {
                                                    String repImgStr = "";
                                                    if(tempimgStr.indexOf("setIframeContent") != -1){
                                                        LogUtil.doWriteLog("--------setIframeContent-----------");
                                                        repImgStr = tempimgStr.replace(tempimgStr.substring(tempimgStr.indexOf("src=") + 5, tempimgStr.indexOf("</a>")-5), server + path.substring(path.indexOf("filesystem") - 1).replace("\\", "/"));
                                                        repImgStr = repImgStr.substring(repImgStr.indexOf("<p"),repImgStr.lastIndexOf("</p>")+4);
                                                    }else {
                                                        repImgStr = tempimgStr.replace(tempimgStr.substring(tempimgStr.indexOf("src=") + 5, tempimgStr.lastIndexOf("\"")), server + path.substring(path.indexOf("filesystem") - 1).replace("\\", "/"));
                                                    }
                                                    line = line.replace(tempimgStr, repImgStr);
                                                    LogUtil.doWriteLog("-----------FileDownload-----------repImgStr:"+repImgStr);
                                                }
                                            }
                                        }
                                        sb.append(line + "\n");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        ArchivesUtil.getExceptionMsg(e);
                                    }
                                } else {
                                    sb.append(line + "\n");
                                }
                            }
                        }
                        line = in.readLine();
                    }


                    sb.append("<script type=\"text/javascript\">\n");
                    sb.append("function drm4request2doc(){\n");
                    sb.append("\tbar=eval(\"[]\");\n");
                    sb.append("\tdocument.getElementById(\"rightMenu\").style.display=\"none\";\n");
                    sb.append("}\n");
                    sb.append("window.attachEvent(\"onload\", drm4request2doc);\n");
                    sb.append("</script>");

                    String sdata = sb.toString();
                    bw.write(sdata, 0, sdata.length());

                    long size = 0L;
                    File f = new File(htmlname);
                    if (f.exists()) {
                        size = f.length();
                    }

                    bw.flush();
                    bw.close();
                    in.close();
                    //0:生成html
                    if ("0".equals(status)) {
                        saveDocDetail(requestname, htmlname, size, workflowid, requestid, wfdocpath, userid, ".html");
                    }
                    //1：生成pdf
                    else {
                        ///HTML转PDF
                        String temphtmlpath = GCONST.getRootPath() + "docToPdf" + "/" + System.currentTimeMillis();
                        LogUtil.doWriteLog("jhy zhenhao" + temphtmlpath);
                        String downloadHtmlToPdfTemp = GCONST.getRootPath() + "docToPdf";
                        File htmlToPdfTempFile = new File(downloadHtmlToPdfTemp);
                        if (!htmlToPdfTempFile.exists()) {
                            htmlToPdfTempFile.mkdirs();
                        }
                        String ymhtml = GCONST.getRootPath() + (new BaseBean()).getPropValue("pdfHtml", "ymhtmlurl");
                        File tempfile = createNewFile(temphtmlpath);
                        fileChannelCopy(f, tempfile);
                        String newhtmlapth = temphtmlpath.replace(GCONST.getRootPath(), getHost() + "/");
                        this.log.error("============++++++++++++++++newhtmlapth:" + newhtmlapth);
//			             String temppath2=temppath.toLowerCase().substring(temppath.indexOf("ilesystem")-1);
//			             String pdhpath = (getHost() + "/" + temppath2 + filename).replace("/weaver/ecology", "");
                        String pdhpath = temppath + filename;
//			             this.log.error("============+++++++++temppath:" + temppath2);
                        this.log.error("============+++++++++++filename:" + filename);
                        this.log.error("============+++++++pdhpath:" + pdhpath);
                        String usewfPath = "wkhtmltopdf";
                        if (!"".equals(Util.null2String(getServerV2("docToPdfConfig/docToPdf.properties", "Path")))) {
                            usewfPath = Util.null2String(getServerV2(
                                    "docToPdfConfig/docToPdf.properties",
                                    "Path"));
                        }
                        StringBuilder cmd = new StringBuilder();
                        cmd.append(usewfPath);
                        cmd.append(" ");

                        //限制某些流程可以生成pdf，获取限制的流程的所有id
                        String workflowidxz = (new BaseBean()).getPropValue("workflowidpdf", "workflowid");
                        this.log.error("============workflowidxz++s所有的workflowid:" + workflowidxz);
                        //判断是否在所有限制流程里
                        String flag = judgeworkfloewid(workflowidxz, workflowid);
                        this.log.error("============返回的标志，是否存在于配置文件" + flag);
                        //如果存在在生成pdf
                        if (!"".equals(flag) && "true".equals(flag)) {
                            cmd.append("--margin-right 14mm --margin-left 17mm --user-style-sheet " + GCONST.getRootPath() + "weavernorth\\css\\endoftheworld.css --header-html " + ymhtml + " --footer-line --disable-javascript ");
                        } else {
                            //不生成pdf语句
                            cmd.append("--margin-right 14mm --margin-left 17mm --user-style-sheet " + GCONST.getRootPath() + "weavernorth\\css\\endoftheworld.css --footer-line --disable-javascript ");

                        }
                        //生成pdf语句
                        // cmd.append("--margin-right 14mm --margin-left 17mm --header-html " + ymhtml + " --footer-line --disable-javascript ");
                        cmd.append(newhtmlapth);
                        cmd.append(" ");
                        cmd.append(pdhpath);
                        this.log.error("wkhtmltopdf===>" + cmd.toString());
                        try {
                            Process proc = Runtime.getRuntime().exec(cmd.toString());
                            HtmlToPdfInterceptor1 error = new HtmlToPdfInterceptor1(proc.getErrorStream());
                            HtmlToPdfInterceptor1 output1 = new HtmlToPdfInterceptor1(proc.getInputStream());
                            error.start();
                            output1.start();
                            proc.waitFor();
//			               tempfile.delete();
                            log.error("HTML转PDF成功===>" + pdhpath);
                        } catch (Exception e) {
                            log.error("HTML转PDF失败===>");
                            log.error(e);
                            writeLog(e);
                            e.printStackTrace();
                        }


                        //2:生成带水印的PDF
                        if ("2".equals(status) && StringUtil.isNotNull(contentstr)) {
                            String sypdfpath = temppath + System.currentTimeMillis() + "";
                            boolean result = setWatermark(pdhpath, sypdfpath, 16);
                            if (result) {
                                log.error("PDF添加水印成功===>" + sypdfpath);
                                //删除源文件
                                File pdf = new File(pdhpath);
                                if (pdf.exists()) {
                                    pdf.delete();
                                }
                                pdhpath = sypdfpath;
                            } else {
                                log.error("PDF添加水印失败===>");
                            }
                        }

                        saveDocDetail(requestname, pdhpath, size, workflowid, requestid, wfdocpath, userid, ".pdf");
                    }


                    if (g != null) {
                        g.releaseConnection();
                    }
                    if (method != null) {
                        method.releaseConnection();
                    }
                }
            }

        } catch (HttpException e) {
            log.error("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            log.error("Fatal transport error: " + e.getMessage());
        } finally {
            method.releaseConnection();
        }

    }


    /**
     * 获取html中img src的参数
     * @param htmlStr
     * @return
     */
    private static List<String> getImgStr(String htmlStr) {
        List<String> pics = new ArrayList<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    //判断当前流程是否在限制流程里
    public String judgeworkfloewid(String workflowidxz, String workflowid) {
        String flag = "false";
        if (!"".equals(workflowidxz) && workflowidxz != null) {
            String[] workflowidarr = workflowidxz.split(",");
            //循环判断是否等于限制的流程
            for (String workflowidad : workflowidarr) {
                if (workflowid.equals(workflowidad)) {
                    flag = "true";
                    break;
                }
            }


        }
        return flag;

    }

    private String getServerV2(String path, String used) {
        String docToPdfConfig =
                GCONST.getRootPath() + path;
        File f = new File(docToPdfConfig);
        InputStream is = null;
        String server = "";
        if (f.exists()) {
            try {
                is = new FileInputStream(f);
                Properties properties = new Properties();
                properties.load(is);
                server = Util.null2String(properties.getProperty(used));

                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();

                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }

                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();

                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e11) {
                        e11.printStackTrace();
                    }
                }
                if (is != null)
                    try {
                        is.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return server;
    }

    public String getServer() {
        String docToPdfConfig = GCONST.getRootPath() + "docToPdfConfig/docToPdf.properties";
        File f = new File(docToPdfConfig);
        InputStream is = null;
        String server = "";
        if (f.exists()) {
            try {
                is = new FileInputStream(f);
                Properties properties = new Properties();
                properties.load(is);
                server = Util.null2String(properties.getProperty("server"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != is) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return server;
    }

    /**
     * TODO 获取签章路径
     *
     * @param userid
     * @return
     */
    public String getImgpath(String userid) {
        String sql = "select * from DocSignature where hrmresid=" + userid + " order by markid";
        RecordSet rs_img = new RecordSet();
        rs_img.execute(sql);
        String img_path = "";
        if (rs_img.next()) {
            img_path = rs_img.getString("markpath");
        }
        return img_path;
    }

    /**
     * 获得最后的url，因为response之后的值，post方法不能直接获取
     *
     * @param client
     * @param url
     * @return
     */
    public String getFinallyUrl(HttpClient client, String url) {
        PostMethod g = new PostMethod(url);
        try {
            client.executeMethod(g);
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return url;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return url;
        }
        Header locationHeader = g.getResponseHeader("location");
        if (locationHeader != null) {
            url = locationHeader.getValue();
            url = getFinallyUrl(client, url);
        }
        if (g != null) {
            g.releaseConnection();
        }
        return url;
    }

    /**
     * 保存文档信息
     *
     * @param imagefilename 文档名称
     * @param filerealpath  文档绝对路径
     * @param filesize      文件大小
     * @param workflowid    流程类型id
     * @param allwfdocpath  文档目录
     * @return 保存是否成功
     */
    public boolean saveDocDetail(String imagefilename, String filerealpath,
                                 long filesize, String workflowid, String requestid, String allwfdocpath, String userid, String suffix) {
        RecordSet rs = new RecordSet();
        if (allwfdocpath.equals("")) {
            log.error("流程保存为文档失败，因为未设置流程保存文档的目录");
            return false;
        }
        String wfdocpath[] = Util.TokenizerString2(allwfdocpath, ",");
        int maincategory = 0;
        int subcategory = 0;
        int seccategory = 0;
        if (wfdocpath.length != 3) {
            return false;
        } else {

            for (int i = 0; i < wfdocpath.length; i++) {
                if (Util.null2String(wfdocpath[i]).equals("")
                        || Util.null2String(wfdocpath[i]).equals("0")) {
                    return false;
                }
            }
            maincategory = Util.getIntValue(wfdocpath[0], 0);
            subcategory = Util.getIntValue(wfdocpath[1], 0);
            seccategory = Util.getIntValue(wfdocpath[2], 0);
        }

        // 先保存imagefile
        int imagefileid = saveImageFile(imagefilename, filerealpath, filesize, suffix);
        if (imagefileid <= 0) {
            return false;
        }

        // 保存docdetail
        int docid = 0;
        try {
            docid = new DocManager().getNextDocId(rs);
        } catch (Exception e) {
            log.error("读取文档id号异常：" + e);
            return false;
        }

        ResourceComInfo ResourceComInfo = null;
        try {
            ResourceComInfo = new ResourceComInfo();
        } catch (Exception e) {
            log.error(e);
        }
        String sql = "";
        int doclangurage = 7;
        int docowner = Util.getIntValue(getWfDocOwner(workflowid, requestid), 0);
        int docdepartmentid = Util.getIntValue(ResourceComInfo
                .getDepartmentID("" + docowner), 0);
        String docsubject = imagefilename;

        Calendar today = Calendar.getInstance();
        String formatdate = Util.add0(today.get(Calendar.YEAR), 4) + "-"
                + Util.add0(today.get(Calendar.MONTH) + 1, 2) + "-"
                + Util.add0(today.get(Calendar.DAY_OF_MONTH), 2);
        String formattime = Util.add0(today.get(Calendar.HOUR_OF_DAY), 2) + ":"
                + Util.add0(today.get(Calendar.MINUTE), 2) + ":"
                + Util.add0(today.get(Calendar.SECOND), 2);
        String doccreatedate = formatdate;
        String doccreatetime = formattime;
        String doclastmoddate = formatdate;
        String doclastmodtime = formattime;
        String docValidDate = formatdate;
        String docValidTime = formattime;
        String parentids = docid + "";
        String docCreaterType = "1";
        String docextendname = "html";
        String docCode = "";
        String doccontent = "";
        String clientIp = "";

        rs.executeSql("select clientAddress from SysMaintenanceLog, SystemLogItem where operateitem=60 AND SysMaintenanceLog.operateItem = SystemLogItem.itemId and operateuserid = '" + userid + "' order by id desc");
        rs.next();
        clientIp = rs.getString("clientAddress");

        if ("0:0:0:0:0:0:0:1".equals(clientIp)) {
            clientIp = "127.0.0.1";
        }

        rs.executeProc("Doc_SecCategory_SelectByID", seccategory + "");
        rs.next();

        String docapprovable = "";
        String docreplyable = rs.getString("replyable");
        String isreply = "";
        int replydocid = 0;
        String docpublishtype = "";

        int itemid = 0;
        int itemmaincategoryid = 0;
        int hrmresid = 0;
        int crmid = 0;
        int projectid = 0;
        int financeid = 0;

        int doccreaterid = docowner;
        int doclastmoduserid = docowner;
        int docapproveuserid = 0;
        String docapprovedate = "";
        String docapprovetime = "";

        int docarchiveuserid = 0;
        String docarchivedate = "";
        String docarchivetime = "";

        String docstatus = "1";
        int assetid = 0;
        int ownerid = docowner;

        String keyword = "";
        //int accessorycount = 0;
        int accessorycount = 1;
        int replaydoccount = 0;
        int docType = 1;//html
        String canCopy = "1";
        String canRemind = "1";
        String orderable = rs.getString("orderable");

        int docEdition = -1;
        int docEditionId = -1;
        SecCategoryComInfo scc = null;

        try {
            scc = new SecCategoryComInfo();
            if (scc.isEditionOpen(seccategory)) {//如果版本管理开启

                docEdition = 1;
                docEditionId = this.getNextEditionId();

            }
        } catch (Exception e) {
            log.error(e);
        }
        int isHistory = 0;
        int approveType = 0;
        int mainDoc = docid;
        int readOpterCanPrint = rs.getInt("readoptercanprint");


        int docValidUserId = docowner;
        String invalidationDate = "";
        String docLastModUserType = docCreaterType;
        String docApproveUserType = "";
        String docValidUserType = docCreaterType;
        String docInvalUserType = "";
        String docArchiveUserType = "";
        String docCancelUserType = "";
        String docPubUserType = "";
        String docReopenUserType = "";
        String ownerType = docCreaterType;
        int canPrintedNum = 0;


        ConnStatement statement = statement = new ConnStatement();
        DocDetailLog dlog = new DocDetailLog();

        boolean isoracle = (statement.getDBType()).equals("oracle");

        if (isoracle) {

            sql = "insert into DocDetail("
                    + "id,maincategory,subcategory,seccategory,doclangurage,"
                    + "docapprovable,docreplyable,isreply,replydocid,docsubject,"
                    + "docpublishtype,itemid,itemmaincategoryid,hrmresid,crmid,"
                    + "projectid,financeid,doccreaterid,docdepartmentid,doccreatedate,"
                    + "doccreatetime,doclastmoduserid,doclastmoddate,doclastmodtime,docapproveuserid,"
                    + "docapprovedate,docapprovetime,docarchiveuserid,docarchivedate,docarchivetime,"
                    + "docstatus,parentids,assetid,ownerid,keyword,"
                    + "accessorycount,replaydoccount,usertype,doctype,cancopy,"
                    + "canremind,orderable,docextendname"
                    +

                    ",doccode,docedition,doceditionid,ishistory,approveType,maindoc,"
                    + "readoptercanprint,docvaliduserid,docvaliddate,docvalidtime,invalidationdate"
                    +

                    ",docCreaterType,docLastModUserType,docApproveUserType,docValidUserType,docInvalUserType"
                    + ",docArchiveUserType,docCancelUserType,docPubUserType,docReopenUserType,ownerType"
                    + ",canPrintedNum,fromworkflow" +

                    ") values(" + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?," + "?,?,?" +

                    ",?,?,?,?,?,?" + ",?,?,?,?,?" +

                    ",?,?,?,?,?" + ",?,?,?,?,?" + ",?,?" +

                    ")";

            try {

                statement.setStatementSql(sql);
                statement.setInt(1, docid);
                statement.setInt(2, maincategory);
                statement.setInt(3, subcategory);
                statement.setInt(4, seccategory);
                statement.setInt(5, doclangurage);
                statement.setString(6, docapprovable);
                statement.setString(7, docreplyable);
                statement.setString(8, isreply);
                statement.setInt(9, replydocid);
                statement.setString(10, docsubject);
                statement.setString(11, docpublishtype);
                statement.setInt(12, itemid);
                statement.setInt(13, itemmaincategoryid);
                statement.setInt(14, hrmresid);
                statement.setInt(15, crmid);
                statement.setInt(16, projectid);
                statement.setInt(17, financeid);
                statement.setInt(18, doccreaterid);
                statement.setInt(19, docdepartmentid);
                statement.setString(20, doccreatedate);
                statement.setString(21, doccreatetime);
                statement.setInt(22, doclastmoduserid);
                statement.setString(23, doclastmoddate);
                statement.setString(24, doclastmodtime);
                statement.setInt(25, docapproveuserid);
                statement.setString(26, docapprovedate);
                statement.setString(27, docapprovetime);
                statement.setInt(28, docarchiveuserid);
                statement.setString(29, docarchivedate);
                statement.setString(30, docarchivetime);
                statement.setString(31, docstatus);
                statement.setString(32, parentids);

                statement.setInt(33, assetid);
                statement.setInt(34, ownerid);
                statement.setString(35, keyword);
                statement.setInt(36, accessorycount);
                statement.setInt(37, replaydoccount);
                statement.setString(38, docCreaterType);

                statement.setInt(39, docType);
                statement.setString(40, canCopy);
                statement.setString(41, canRemind);
                statement.setString(42, orderable);
                statement.setString(43, docextendname);
                statement.setString(44, docCode);

                statement.setInt(45, docEdition);
                statement.setInt(46, docEditionId);
                statement.setInt(47, isHistory);
                statement.setInt(48, approveType);
                statement.setInt(49, mainDoc);
                statement.setInt(50, readOpterCanPrint);
                statement.setInt(51, docValidUserId);

                statement.setString(52, docValidDate);
                statement.setString(53, docValidTime);

                statement.setString(54, invalidationDate);

                statement.setString(55, docCreaterType);
                statement.setString(56, docLastModUserType);
                statement.setString(57, docApproveUserType);
                statement.setString(58, docValidUserType);
                statement.setString(59, docInvalUserType);
                statement.setString(60, docArchiveUserType);
                statement.setString(61, docCancelUserType);
                statement.setString(62, docPubUserType);
                statement.setString(63, docReopenUserType);
                statement.setString(64, ownerType);
                statement.setInt(65, canPrintedNum);
                statement.setString(66, requestid);

                statement.executeUpdate();

                sql = "insert into DocDetailContent (docid, doccontent) values(?,empty_clob()) ";
                statement.setStatementSql(sql);
                statement.setInt(1, docid);
                statement.executeUpdate();

                sql = "select doccontent from DocDetailContent where docid = "
                        + docid;
                statement.setStatementSql(sql, false);
                statement.executeQuery();
                statement.next();
                CLOB theclob = statement.getClob(1);

                String doccontenttemp = doccontent;
                char[] contentchar = doccontenttemp.toCharArray();
                Writer contentwrite = theclob.getCharacterOutputStream();
                contentwrite.write(contentchar);
                contentwrite.flush();
                contentwrite.close();

                dlog.resetParameter();
                dlog.setDocId(docid);
                dlog.setDocSubject(docsubject);
                dlog.setOperateType("1");
                dlog.setOperateUserid(docowner);
                dlog.setUsertype(docCreaterType);
                dlog.setClientAddress(clientIp);
                dlog.setDocCreater(docowner);
                dlog.setCreatertype(docCreaterType);
                dlog.setDocLogInfo();

            } catch (Exception e) {
                log.error(e);
            } finally {
                try {
                    statement.close();
                } catch (Exception ex) {
                }
            }

        } else {

            sql = "insert into DocDetail("
                    + "id,maincategory,subcategory,seccategory,doclangurage,"
                    + "docapprovable,docreplyable,isreply,replydocid,docsubject,"
                    + "doccontent,docpublishtype,itemid,itemmaincategoryid,hrmresid,"
                    + "crmid,projectid,financeid,doccreaterid,docdepartmentid,"
                    + "doccreatedate,doccreatetime,doclastmoduserid,doclastmoddate,doclastmodtime,"
                    + "docapproveuserid,docapprovedate,docapprovetime,docarchiveuserid,docarchivedate,"
                    + "docarchivetime,docstatus,parentids,assetid,ownerid,"
                    + "keyword,accessorycount,replaydoccount,usertype,doctype,"
                    + "cancopy,canremind,orderable,docextendname"
                    +

                    ",doccode,docedition,doceditionid,ishistory,approveType,maindoc,"
                    + "readoptercanprint,docvaliduserid,docvaliddate,docvalidtime,invalidationdate"
                    +

                    ",docCreaterType,docLastModUserType,docApproveUserType,docValidUserType,docInvalUserType"
                    + ",docArchiveUserType,docCancelUserType,docPubUserType,docReopenUserType,ownerType"
                    + ",canPrintedNum,fromworkflow" +

                    ") values(" + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?" +

                    ",?,?,?,?,?,?" + ",?,?,?,?,?" +

                    ",?,?,?,?,?" + ",?,?,?,?,?" + ",?,?" +

                    ")";

            try {

                statement.setStatementSql(sql);
                statement.setInt(1, docid);
                statement.setInt(2, maincategory);
                statement.setInt(3, subcategory);
                statement.setInt(4, seccategory);
                statement.setInt(5, doclangurage);
                statement.setString(6, docapprovable);
                statement.setString(7, docreplyable);
                statement.setString(8, isreply);
                statement.setInt(9, replydocid);
                statement.setString(10, docsubject);
                statement.setString(11, doccontent);
                statement.setString(12, docpublishtype);
                statement.setInt(13, itemid);
                statement.setInt(14, itemmaincategoryid);
                statement.setInt(15, hrmresid);
                statement.setInt(16, crmid);
                statement.setInt(17, projectid);
                statement.setInt(18, financeid);
                statement.setInt(19, doccreaterid);
                statement.setInt(20, docdepartmentid);
                statement.setString(21, doccreatedate);
                statement.setString(22, doccreatetime);
                statement.setInt(23, doclastmoduserid);
                statement.setString(24, doclastmoddate);
                statement.setString(25, doclastmodtime);
                statement.setInt(26, docapproveuserid);
                statement.setString(27, docapprovedate);
                statement.setString(28, docapprovetime);
                statement.setInt(29, docarchiveuserid);
                statement.setString(30, docarchivedate);
                statement.setString(31, docarchivetime);
                statement.setString(32, docstatus);
                statement.setString(33, parentids);

                statement.setInt(34, assetid);
                statement.setInt(35, ownerid);
                statement.setString(36, keyword);
                statement.setInt(37, accessorycount);
                statement.setInt(38, replaydoccount);
                statement.setString(39, docCreaterType);
                statement.setInt(40, docType);
                statement.setString(41, canCopy);
                statement.setString(42, canRemind);
                statement.setString(43, orderable);
                statement.setString(44, docextendname);

                statement.setString(45, docCode);
                statement.setInt(46, docEdition);
                statement.setInt(47, docEditionId);
                statement.setInt(48, isHistory);
                statement.setInt(49, approveType);

                statement.setInt(50, mainDoc);
                statement.setInt(51, readOpterCanPrint);
                statement.setInt(52, docValidUserId);
                statement.setString(53, docValidDate);
                statement.setString(54, docValidTime);

                statement.setString(55, invalidationDate);

                statement.setString(56, docCreaterType);
                statement.setString(57, docLastModUserType);
                statement.setString(58, docApproveUserType);
                statement.setString(59, docValidUserType);
                statement.setString(60, docInvalUserType);
                statement.setString(61, docArchiveUserType);
                statement.setString(62, docCancelUserType);
                statement.setString(63, docPubUserType);
                statement.setString(64, docReopenUserType);
                statement.setString(65, ownerType);
                statement.setInt(66, canPrintedNum);
                statement.setString(67, requestid);

                statement.executeUpdate();

                dlog.resetParameter();
                dlog.setDocId(docid);
                dlog.setDocSubject(docsubject);
                dlog.setOperateType("1");
                dlog.setOperateUserid(docowner);
                dlog.setUsertype(docCreaterType);
                dlog.setClientAddress(clientIp);
                dlog.setDocCreater(docowner);
                dlog.setCreatertype(docCreaterType);
                dlog.setDocLogInfo();

            } catch (Exception e) {
                log.error(e);
            } finally {
                try {
                    statement.close();
                } catch (Exception ex) {
                }
            }
        }

        // 最后保存 docimagefile
        saveDocImageFile(docid, imagefileid, imagefilename, suffix);
        // 保存文档属性
        saveDocProp(docid, requestid);

        try {
            //保存文档信息至虚拟目录
            RecordSet rsDummyDoc = new RecordSet();
            String dummycata = "";
            String strSql = "select defaultDummyCata from DocSecCategory where id=" + seccategory;
            rsDummyDoc.executeSql(strSql);
            if (rsDummyDoc.next()) dummycata = Util.null2String(rsDummyDoc.getString("defaultDummyCata"));

            if (dummycata != null && !"".equals(dummycata)) {
                ArrayList dummyCataList = Util.TokenizerString(dummycata, ",");
                for (int i = 0; i < dummyCataList.size(); i++) {
                    String strDummy = (String) dummyCataList.get(i);
                    if (!"".equals(strDummy)) {
                        String importdate = TimeUtil.getCurrentDateString();
                        String importtime = TimeUtil.getOnlyCurrentTimeString();
                        DocTreeDocFieldComInfo ddfc = new DocTreeDocFieldComInfo();
                        if (!ddfc.isHaveSameOne(strDummy, "" + docid)) {
                            strSql = "insert into DocDummyDetail(catelogid,docid,importdate,importtime) values (" + strDummy + "," + docid + ",'" + importdate + "','" + importtime + "')";
                            rsDummyDoc.executeSql(strSql);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            log.error("添加文档信息至虚拟目录出错：" + ex);
        }

        // 添加文档共享信息
        DocManager DocManager = new DocManager();
        DocManager.setIsreply("0");
        DocManager.setId(docid);
        DocManager.setSeccategory(seccategory);
        DocManager.setUserid(docowner);
        DocManager.setUsertype("1");
        try {
            DocManager.AddShareInfo();
        } catch (Exception e) {
            log.error("添加文档共享信息出错：" + e);
        }
        try {
            new DocViewer().setDocShareByDoc("" + docid);
        } catch (Exception e) {
            log.error("添加文档共享信息出错：" + e);
        }
        return true;
    }


    /**
     * 判断requestmanager是否为空
     *
     * @param rmanager
     */
    public RequestManager getRequestManager(RequestManager rmanager, String requestid) {

        if (rmanager == null) {
            rmanager = new RequestManager();
            RecordSet rs = new RecordSet();
            String sql = "select * from workflow_requestbase where requestid = " + requestid;
            rs.executeSql(sql);
            while (rs.next()) {
                int _requestId = rs.getInt("requestid");
                int workflowId = rs.getInt("workflowid");
                int creater = rs.getInt("creater");
                String requestname = rs.getString("requestname");
                String requestlevel = rs.getString("requestlevel");
                String messagetype = rs.getString("messagetype");
                rmanager.setRequestid(_requestId);
                rmanager.setWorkflowid(workflowId);
                rmanager.setCreater(creater);
                rmanager.setRequestname(requestname);
                rmanager.setRequestlevel(requestlevel);
                rmanager.setMessageType(messagetype);
            }
        }

        return rmanager;
    }

    /**
     * 保存文档属性
     *
     * @param docid
     * @return
     */
    public boolean saveDocProp(int docid, String requestid) {
        boolean success = true;
        String docids = docid + "";
        this.requestManager = getRequestManager(this.requestManager, requestid);
        try {
            log.error("saveDocProp:" + docid + " " + requestid);
            RequestDocPropManager requestDocPropManager = new RequestDocPropManager();
            requestDocPropManager.changeDocProp(this.requestManager, docids);
        } catch (Exception e) {
            success = false;
            log.error("RequestDocPropManager Error:requestid=" + requestid + "###docids=" + docids);
            log.error(e);
        }
        log.error("success:" + success);
        //liulihua修改20171106 开始
        DocUtil.setTableOfPDFInfo(docids, requestid);
        //liulihua修改20171106 结束
        return success;

    }

    /**
     * 保存docimagefile
     *
     * @param docid       文档id
     * @param imagefileid imagefileid
     * @param filename    文件名
     */
    public boolean saveDocImageFile(int docid, int imagefileid, String filename, String suffix) {

        RecordSet rs = new RecordSet();

        int id = 0;
        int versionId = 0;
        String imagefilename = filename + suffix;
        String imagefiledesc = "";
        String imagefilewidth = "0";
        String imagefileheight = "0";
        String imagefielsize = "0";
        String docfiletype = "2";
        String versionDetail = "";

        try {
            id = new DocImageManager().getNextDocImageFileId();
        } catch (Exception e) {
            log.error("读取docimagefileid异常：" + e);
            return false;
        }

//		rs.executeProc("SequenceIndex_SelectVersionId", "");
//		if (rs.next()) {
//			versionId = Util.getIntValue(rs.getString(1));
//		}
        versionId = versionIdUpdate.getVersionNewId();

        String tmpsqlstr = "INSERT INTO DocImageFile(docid, imagefileid, imagefilename, imagefiledesc, imagefilewidth, imagefileheight, imagefielsize, docfiletype,versionId,versionDetail,id)"
                + "VALUES ("
                + ""
                + docid
                + ""
                + ","
                + ""
                + imagefileid
                + ""
                + ","
                + "'"
                + imagefilename
                + "'"
                + ","
                + "'"
                + imagefiledesc
                + "'"
                + ","
                + "'"
                + imagefilewidth
                + "'"
                + ","
                + "'"
                + imagefileheight
                + "'"
                + ","
                + ""
                + imagefielsize
                + ""
                + ","
                + "'"
                + docfiletype
                + "'"
                + ","
                + ""
                + versionId
                + ""
                + ","
                + "'" + versionDetail + "'" + "," + id + ")";
        rs.executeSql(tmpsqlstr);
        //log.error("保存到docimagefile: " + tmpsqlstr);

        return true;
    }

    /**
     * 保存imagefile 返回文件的id
     *
     * @param filename     文件名
     * @param filerealpath 文件绝对路径
     * @param filesize     文件大小
     * @return imagefileid 文件id
     */
    public int saveImageFile(String filename, String filerealpath, long filesize, String suffix) {
        int imageid = 0;
        String originalfilename = filename + suffix;
        LogUtil.doWriteLog("保存文件夹 名字"+originalfilename);
        String contenttype = "";

        String imagefileused = "1";
        String iszip = "0";
        String isencrypt = "0";

        RecordSet rs = new RecordSet();
        char separator = Util.getSeparator();
        imageid = imageFileIdUpdate.getImageFileNewId();

        String para = "" + imageid + separator + originalfilename + separator
                + contenttype + separator + imagefileused + separator
                + filerealpath + separator + iszip + separator + isencrypt
                + separator + filesize;

        rs.executeProc("ImageFile_Insert", para);

        AliOSSObjectManager aliOSSObjectManager = new AliOSSObjectManager();
        String tokenKey = aliOSSObjectManager.getTokenKeyByFileRealPath(filerealpath);

        //log.error("保存到imagefile: " + para);

        //comefrom='WorkflowToDoc'  表示该条附件数据来自于流程存为文档
        rs.executeSql("update imagefile set isaesencrypt='" + this.isaesencrypt + "', aescode='" + this.aescode + "',TokenKey='" + tokenKey + "',comefrom='WorkflowToDoc' where imagefileid=" + imageid);

        aliOSSObjectManager.uploadFile(filerealpath, originalfilename, iszip, isaesencrypt, aescode);

        return imageid;
    }

    /**
     * 获取系统的路径
     *
     * @return
     */
    public String getSystemPath() {
        String path = GCONST.getPropertyPath();
        path = path.substring(0, path.indexOf("WEB-INF"));
        path = Util.StringReplace(path, "\\", "/");
        return path;
    }

    /**
     * 获得文档存放的目录
     *
     * @param workflowid
     * @return 主目录, 分目录, 子目录
     */
    public String getWfDocPath(String workflowid) {
        String wfdocpath = "";
        RecordSet rs = new RecordSet();
        String sql = "select * from workflow_base where id = '" + workflowid
                + "'";
        rs.executeSql(sql);
        rs.next();
        wfdocpath = Util.null2String(rs.getString("wfdocpath"));
        return wfdocpath;
    }

    /**
     * 获得文档的所有者
     *
     * @param workflowid
     * @return resourceid
     */
    public String getWfDocOwner(String workflowid, String requestid) {
        int wfdocowner = 0;
        try {
            String sql = "";
            int wfid = Util.getIntValue(workflowid, 0);
            int rid = Util.getIntValue(requestid, 0);
            if (wfid <= 0 || rid <= 0) {
                return "0";
            }
            RecordSet rs = new RecordSet();
            sql = "select * from workflow_base where id=" + wfid;
            rs.executeSql(sql);
            if (rs.next()) {
                int wfdocowner_tmp = Util.getIntValue(rs.getString("wfdocowner"), 0);
                int wfdocownertype_tmp = Util.getIntValue(rs.getString("wfdocownertype"), 0);
                int wfdocownerfieldid_tmp = Util.getIntValue(rs.getString("wfdocownerfieldid"), 0);
                int isbill = Util.getIntValue(rs.getString("isbill"), 0);
                int formid = Util.getIntValue(rs.getString("formid"), 0);
                if (wfdocownertype_tmp == 1) {
                    wfdocowner = wfdocowner_tmp;
                } else if (wfdocownertype_tmp == 2) {
                    String tablename = " workflow_form ";
                    String fieldname = "";
                    if (isbill == 1) {
                        sql = "select tablename from workflow_bill where id=" + formid;
                        rs.execute(sql);
                        if (rs.next()) {
                            tablename = Util.null2String(rs.getString(1));
                        }
                        sql = "select fieldname from workflow_billfield where billid=" + formid + " and id=" + wfdocownerfieldid_tmp;
                    } else {
                        sql = "select fieldname from workflow_formdict where id=" + wfdocownerfieldid_tmp;
                    }
                    rs.execute(sql);
                    if (rs.next()) {
                        fieldname = Util.null2String(rs.getString(1));
                        if (!"".equals(tablename) && !"".equals(fieldname)) {
                            sql = "select " + fieldname + " from " + tablename + " where requestid=" + rid;
                            rs.execute(sql);
                            if (rs.next()) {
                                String fieldvalue = Util.null2String(rs.getString(1));
                                if (!"".equals(fieldvalue)) {
                                    int index = fieldvalue.indexOf(",");
                                    if (index > -1) {
                                        fieldvalue = fieldvalue.substring(0, index);
                                    }
                                    wfdocowner = Util.getIntValue(fieldvalue);
                                }
                            }
                        }
                    }
                }
                String status = Util.null2String(new ResourceComInfo().getStatus("" + wfdocowner));
                if (status.equals("0") || status.equals("1") || status.equals("2") || status.equals("3")) {
                    //do nothing
                } else {
                    wfdocowner = 0;
                }
            }
        } catch (Exception e) {

        }
        if (wfdocowner <= 0) {
            wfdocowner = 1;
        }
        return "" + wfdocowner;
    }

    /**
     * 获得文件保存目录
     *
     * @return
     */
    public String getFileSavePath() {
        SystemComInfo syscominfo = new SystemComInfo();
        String createdir = FileUpload.getCreateDir(syscominfo.getFilesystem());
        return createdir;
    }

    public synchronized int getNextEditionId() throws Exception {
        int docindex = 0;
        RecordSet rs = new RecordSet();
        rs.executeProc("SequenceIndex_SelectNextID", "doceditionid");
        if (rs.next())
            docindex = rs.getInt(1);
        return docindex;
    }

    /**
     * 给PDF添加水印
     *
     * @param inputpath  源文件
     * @param outputpath 输出文件
     * @param permission
     * @throws DocumentException
     * @throws IOException
     */
    public boolean setWatermark(String inputpath, String outputpath, int permission) {
        boolean result = false;
        try {
            PdfReader reader = new PdfReader(inputpath);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(outputpath)));
            PdfStamper stamper = new PdfStamper(reader, bos);
            int total = reader.getNumberOfPages() + 1;
            PdfContentByte content;
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            PdfGState gs = new PdfGState();
            for (int i = 1; i < total; i++) {
                content = stamper.getOverContent(i);// 在内容上方加水印
                //content = stamper.getUnderContent(i);//在内容下方加水印
                gs.setFillOpacity(0.2f);
                content.beginText();
                content.setColorFill(Color.LIGHT_GRAY);
                content.setFontAndSize(base, 50);
                content.setTextMatrix(70, 200);
                content.showTextAligned(Element.ALIGN_CENTER, contentstr, 300, 350, 55);
                content.setColorFill(Color.BLACK);
                content.setFontAndSize(base, 8);
                content.endText();

            }
            stamper.close();
            result = true;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取归档人
     *
     * @param requestid
     * @return
     */
    private String getWorkflowEndPerson(String requestid) {

        String userid = "";
        RecordSet rs = new RecordSet();
        rs.executeQuery("select userid from workflow_currentoperator where isremark = '4' and requestid = " + requestid);
        if (rs.next()) {
            userid = rs.getString("userid");
        }
        LogUtil.debugLog("=======WorkflowToDocOrPdf=====>userid:" + userid + "---requestid--:" + requestid);
        return userid;
    }

    private File createNewFile(String filepath) {
        File file = new File(filepath);
        this.log.error("===================filepath:" + file.getAbsolutePath());
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public void fileChannelCopy(File s, File t) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();
            out = fo.getChannel();
            in.transferTo(0L, in.size(), out);
        } catch (IOException e) {
            writeLog(e);
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e1) {
                writeLog(e1);
            }

            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e2) {
                writeLog(e2);
            }
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                writeLog(e);
            }
        }
    }
}

class HtmlToPdfInterceptor1 extends Thread {
    private InputStream is;
    public HtmlToPdfInterceptor1(InputStream is) {
        this.is = is;
    }
    public void run() {
        try {
            InputStreamReader isr = new InputStreamReader(this.is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null)
                System.out.println(line.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
