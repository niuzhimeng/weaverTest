<%@ page import="com.weavernorth.mlc.OA2XCFilter" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="weaver.integration.util.HTTPUtil" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.*" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import="weaver.conn.RecordSet" %>
<%@ page import="com.weavernorth.mlc.OA2XCAction" %>
<%@ page import="weaver.general.BaseBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<script type="text/javascript" language="javascript" src="/FCKEditor/FCKEditorExt_wev8.js"></script>
<html>

<%
    // Ticket获取URL

    String ticketUrl = "https://ct.ctrip.com/corpservice/authorize/getticket";
    String appKey = "obk_mljr";
    String appSecurity = "obk_mljr";
    JSONObject map = new JSONObject();
    map.put("appKey", appKey);
    map.put("appSecurity", appSecurity);
    String ticket = HTTPUtil.doPost(ticketUrl, map);
    System.out.println(ticket);
    JSONObject object1 = JSONObject.fromObject(ticket);
    String contentcode2 = object1.getString("Token");
    System.out.println(contentcode2);


    //单点登录URL

    String loginUrl = "https://ct.ctrip.com/corpservice/authorize/login";

    String responseText = null;


    String sqrgh = request.getParameter("sqrgh");
    String sqdh = request.getParameter("sqdh");

    new BaseBean().writeLog("+++++++++++++++++++++++++++++++++++++++++++"+sqrgh);


    String employeeID = sqrgh;

    String TA = sqdh;
    String keymd5 = appSecurity;
    StringBuilder sb = new StringBuilder();
    MessageDigest md5;
    try {
        md5 = MessageDigest.getInstance("MD5");
        md5.update(keymd5.getBytes());
        for (byte b : md5.digest()) {
            sb.append(String.format("%02x", b));
        }

    } catch ( NoSuchAlgorithmException e ) {
        e.printStackTrace();
    }
    String source = appKey + "" + employeeID + TA + "0" + sb;

    StringBuilder sb1 = new StringBuilder();
    MessageDigest md51;
    try {
        md51 = MessageDigest.getInstance("MD5");
        md51.update(source.getBytes());
        for (byte b1 : md51.digest()) {
            sb1.append(String.format("%02x", b1));
        }

    } catch ( NoSuchAlgorithmException e ) {
        e.printStackTrace();
    }
    String signature = sb1.toString();


    responseText = "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">";

    responseText = responseText + "<script type=\"text/javascript\">function formSubmit(){document.getElementById(\"fLogin\").submit();}</script>";

    responseText = responseText + "</head><body>";

    responseText = responseText + "<form name=\"fLogin\" id=\"fLogin\" method=\"post\" action=\"" + loginUrl + "\">";

    responseText = responseText + "<input type=\"hidden\" name=\"AppKey\" value=\"" + appKey + "\" />";

    responseText = responseText + "<input type=\"hidden\" name=\"Ticket\" value=\"" + contentcode2 + "\" />";

    responseText = responseText + "<input type=\"hidden\" name=\"EmployeeID\" value=\"" + employeeID + "\"/>";

    responseText = responseText + "<input type=\"hidden\" name=\"Signature\" value=\"" + signature + "\"/>";

    responseText = responseText + "<input type=\"hidden\" name=\"TA\" value=\"" + TA + "\"/>";


    responseText = responseText + "<script language=\"javascript\">formSubmit();</script></form></body>";

    //设置编码为UTF-8

    response.setHeader("Content-type", "text/html;charset=UTF-8");

    PrintWriter pw = response.getWriter();

    pw.println(responseText);


%>

<%!
    //Post method

    public static String sendPost(String url, String param) {

        OutputStreamWriter out = null;

        BufferedReader in = null;

        String result = "";

        try {

            URL realUrl = new URL(url);

            HttpURLConnection conn = null;

            conn = (HttpURLConnection) realUrl.openConnection();// 打开和URL之间的连接


            // 发送POST请求必须设置如下两行

            conn.setRequestMethod("POST"); // POST方法

            conn.setDoOutput(true);

            conn.setDoInput(true);


            // 设置通用的请求属性

            conn.setRequestProperty("accept", "*/*");

            conn.setRequestProperty("connection", "Keep-Alive");

            conn.setRequestProperty("user-agent",

                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            conn.setRequestProperty("Content-Type", "application/json");

            conn.connect();


            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");// 获取URLConnection对象对应的输出流

            out.write(param);// 发送请求参数

            out.flush();// flush输出流的缓冲


            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));//定义BufferedReader输入流来读取URL的响应


            String line;

            while ((line = in.readLine()) != null) {

                result += line;

                System.out.println("OK");

            }

        } catch ( Exception e ) {

            System.out.println("发送 POST 请求出现异常！" + e);

            e.printStackTrace();

        }

        //使用finally块来关闭输出流、输入流

        finally {

            try {

                if (out != null) {

                    out.close();

                }

                if (in != null) {

                    in.close();

                }

            } catch ( IOException ex ) {

                ex.printStackTrace();

            }

        }

        return result;

    }

%>


</html>



