<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="weaver.integration.util.HTTPUtil" %>
<%@ page import="java.security.MessageDigest" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>测试表单</title>
</head>
<script type="text/javascript" language="javascript">
    window.onload = function() {
        document.getElementById("Submit1").click();
    }

</script>

<body>
<%
    String ticketUrl = "https://ct.ctrip.com/corpservice/authorize/getticket";
    String appKey = "obk_mljr";
    String appSecurity = "obk_mljr";
    JSONObject map = new JSONObject();
    map.put("appKey", appKey);
    map.put("appSecurity", appSecurity);
    String ticket = HTTPUtil.doPost(ticketUrl, map);

    JSONObject object1 = JSONObject.fromObject(ticket);
    String contentcode2 = object1.getString("Token");
    String sqrgh = request.getParameter("sqrgh");
    String sqdh = request.getParameter("sqdh");
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
    String source = appKey + employeeID +"public"+ sb;

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
%>

<form action="https://ct.ctrip.com/m/SingleSignOn/H5SignInfo" method="post">
    <input type="hidden" name="accessuserid" value="obk_mljr"/>
    <input type="hidden" name="employeeid" value="<%=sqrgh%>"/>
    <input type="hidden" name="signature" value="<%=signature%>"/>
    <input type="hidden" name="initpage" value="Home"/>
    <input type="hidden" name="appid" value="mljr"/>
    <input type="hidden" name="endorsementID" value="<%=sqdh%>"/>
    <input type="hidden" name="token" value="<%=contentcode2%>"/>
    <input type="hidden" name="onerror" value="errorcode"/>
    <input id="Submit1" value="单点登录" style="display:none" type="submit" onclick="a()"/>
</form>

</body>
</html>