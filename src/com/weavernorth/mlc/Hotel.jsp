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
    String sql = "insert into uf_jdcs(citycode,cityname) values(?, ?)";
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
    String ticketUrl1 ="https://ct.ctrip.com/corpopenapi/HotelCity/GetCountryCityExtend";
    JSONObject map2 = new JSONObject();
    map2.put("AppKey", appKey);
    map2.put("Ticket", contentcode2);
    JSONObject map3 = new JSONObject();
    map3.put("CountryId",1);
    map3.put("Auth",map2);
    System.out.println(map3);
    JSONObject aaa=doPost(ticketUrl1,map3);
    System.out.println(aaa);
    JSONObject object12 = JSONObject.fromObject(aaa);
    String data=object12.getString("Data");
    JSONArray json=JSONArray.fromObject(data);

    if (json.size() > 0) {
        for (int i = 0; i < json.size(); i++) {
            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            JSONObject job = json.getJSONObject(i);
                try {
                    String citycode = String.valueOf(job.get("City"));
                    String cityname = String.valueOf(job.get("CityName"));
                    statement.setStatementSql(sql);
                    statement.setString(1, citycode);
                    statement.setString(2, cityname);
                    statement.executeUpdate();
                } catch ( Exception e ) {
                    e.printStackTrace();
                }


        }
    }


    //String xml = "<SubCompanyBean-array><SubCompanyBean><subcompanyid>1</subcompanyid><shortname>Default</shortname><fullname>Default</fullname><supsubcompanyid>0</supsubcompanyid><website></website><showorder></showorder><code></code><canceled></canceled><action></action><lastChangdate></lastChangdate></SubCompanyBean><SubCompanyBean><subcompanyid>5</subcompanyid><shortname>东方明珠集团</shortname><fullname>东方明珠集团</fullname><supsubcompanyid>0</supsubcompanyid><website></website><showorder></showorder><code></code><canceled></canceled><action></action><lastChangdate></lastChangdate></SubCompanyBean><SubCompanyBean><subcompanyid>6</subcompanyid><shortname>西北分公司</shortname><fullname>西北分公司</fullname><supsubcompanyid>5</supsubcompanyid><website></website><showorder></showorder><code></code><canceled></canceled><action></action><lastChangdate></lastChangdate></SubCompanyBean><SubCompanyBean><subcompanyid>7</subcompanyid><shortname>华南分公司</shortname><fullname>华南分公司</fullname><supsubcompanyid>5</supsubcompanyid><website></website><showorder></showorder><code></code><canceled></canceled><action></action><lastChangdate></lastChangdate></SubCompanyBean></SubCompanyBean-array>";

%>
<%!
    public static JSONObject doPost(String url, JSONObject json) {

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject js = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                js = JSONObject.fromObject(result);
            }
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
        return js;
    }
%>
