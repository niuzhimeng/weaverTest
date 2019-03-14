<%@ page import="com.weaver.general.BaseBean" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="org.apache.http.HttpStatus" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.entity.StringEntity" %>
<%@ page import="org.apache.http.impl.client.CloseableHttpClient" %>
<%@ page import="org.apache.http.impl.client.HttpClientBuilder" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%@ page import="weaver.conn.ConnStatement" %>
<%@ page import="weaver.integration.util.HTTPUtil" %>
<%@ page language="java" contentType="text/html; charset=gbk" %>
<jsp:useBean id="RequestManager" class="weaver.workflow.request.RequestManager" scope="page"/>
<jsp:useBean id="flowDocss" class="weaver.workflow.request.RequestDoc" scope="session"/>
<jsp:useBean id="DocComInfo" class="weaver.docs.docs.DocComInfo" scope="page"/>
<jsp:useBean id="Doccoder" class="weaver.docs.docs.DocCoder" scope="page"/>

<%
    BaseBean baseBean = new BaseBean();
    ConnStatement statement = new ConnStatement();
    String sql = "insert into uf_szm(citycode,cityname) values(?, ?)";
    String ticketUrl = "https://ct.ctrip.com/corpservice/authorize/getticket";
    String appKey = "obk_mljr";
    String appSecurity = "obk_mljr";
    JSONObject map = new JSONObject();
    map.put("appKey", appKey);
    map.put("appSecurity", appSecurity);
    String ticket = HTTPUtil.doPost(ticketUrl, map);
    JSONObject object1 = JSONObject.fromObject(ticket);
    String contentcode2 = object1.getString("Token");
    baseBean.writeLog("****************" + contentcode2);
    String ticketUrl1 = "https://corpsz.ctrip.com/flightBaseData/queryCity";

    JSONObject map2 = new JSONObject();
    map2.put("appKey", appKey);
    map2.put("ticket", contentcode2);
    JSONObject map3 = new JSONObject();
    map3.put("auth", map2);
    JSONObject aaa = doPost(ticketUrl1, map3);
    baseBean.writeLog("**********"+aaa);
    JSONObject object12 = JSONObject.fromObject(aaa);
    String datas = object12.getString("datas");
    JSONArray json = JSONArray.fromObject(datas);
    baseBean.writeLog("****************" + json);

    if (json.size() > 0) {
        for (int i = 0; i < json.size(); i++) {
            // ���� jsonarray ���飬��ÿһ������ת�� json ����
            JSONObject job = json.getJSONObject(i);
            int aiportCity = Integer.parseInt(String.valueOf(job.get("poiType")));
            baseBean.writeLog("****************" + aiportCity);
            String countryName = String.valueOf(job.get("countryName"));
            baseBean.writeLog("****************" + countryName);
            if (aiportCity == 5 && ("�й�").equals(countryName)) {
                baseBean.writeLog(job.get("code") + "" + job.get("name") + "=======" + job.get("poiType"));
                try {
                    String citycode = String.valueOf(job.get("code"));
                    String cityname = String.valueOf(job.get("name"));
                    statement.setStatementSql(sql);
                    statement.setString(1, citycode);
                    statement.setString(2, cityname);
                    statement.executeUpdate();
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }

        }
    }


    //String xml = "<SubCompanyBean-array><SubCompanyBean><subcompanyid>1</subcompanyid><shortname>Default</shortname><fullname>Default</fullname><supsubcompanyid>0</supsubcompanyid><website></website><showorder></showorder><code></code><canceled></canceled><action></action><lastChangdate></lastChangdate></SubCompanyBean><SubCompanyBean><subcompanyid>5</subcompanyid><shortname>�������鼯��</shortname><fullname>�������鼯��</fullname><supsubcompanyid>0</supsubcompanyid><website></website><showorder></showorder><code></code><canceled></canceled><action></action><lastChangdate></lastChangdate></SubCompanyBean><SubCompanyBean><subcompanyid>6</subcompanyid><shortname>�����ֹ�˾</shortname><fullname>�����ֹ�˾</fullname><supsubcompanyid>5</supsubcompanyid><website></website><showorder></showorder><code></code><canceled></canceled><action></action><lastChangdate></lastChangdate></SubCompanyBean><SubCompanyBean><subcompanyid>7</subcompanyid><shortname>���Ϸֹ�˾</shortname><fullname>���Ϸֹ�˾</fullname><supsubcompanyid>5</supsubcompanyid><website></website><showorder></showorder><code></code><canceled></canceled><action></action><lastChangdate></lastChangdate></SubCompanyBean></SubCompanyBean-array>";

%>
<%!
    public static JSONObject doPost(String url, JSONObject json) {

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject js = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//����json������Ҫ����contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// ����json��ʽ��
                js = JSONObject.fromObject(result);
            }
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
        return js;
    }
%>
