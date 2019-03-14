package com.weavernorth.mlc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import weaver.conn.ConnStatement;
import weaver.general.BaseBean;
import weaver.integration.util.HTTPUtil;
import weaver.interfaces.schedule.BaseCronJob;


import java.sql.SQLException;

/**
 * Created by zhaohr on 2019/1/25.
 */
public abstract class QueryCity extends BaseCronJob {
    BaseBean baseBean=new BaseBean();
    ConnStatement statement = new ConnStatement();
    String sql = "insert into uf_szm(citycode,cityname) values(?, ?)";


    @Override
    public void execute() {
        String ticketUrl = "https://ct.ctrip.com/corpservice/authorize/getticket";
        String appKey = "obk_mljr";
        String appSecurity = "obk_mljr";
        JSONObject map = new JSONObject();
        map.put("appKey",appKey);
        map.put("appSecurity",appSecurity);
        String ticket = HTTPUtil.doPost(ticketUrl, map);
        JSONObject object1 = JSONObject.fromObject(ticket);
        String contentcode2 = object1.getString("Token");
        String ticketUrl1 = "https://corpsz.ctrip.com/flightBaseData/queryCity";
        JSONObject map2 = new JSONObject();
        map2.put("appKey",appKey);
        map2.put("ticket",contentcode2);
        JSONObject map3 = new JSONObject();
        map3.put("auth",map2);
        JSONObject aaa = doPost(ticketUrl1, map3);
        JSONObject object12 = JSONObject.fromObject(aaa);
        String datas = object12.getString("datas");
        JSONArray json = JSONArray.fromObject(datas);

        if(json.size()>0){
            for (int i = 0; i < json.size(); i++) {
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject job = json.getJSONObject(i);
                int aiportCity = Integer.parseInt(String.valueOf(job.get("poiType")));
                String countryName = String.valueOf(job.get("countryName"));

                if (aiportCity == 5 && ("中国").equals(countryName)) {
                   baseBean.writeLog(job.get("code") + "" + job.get("name") + "=======" + job.get("poiType"));
                    try {
                        String citycode= String.valueOf(job.get("code"));
                        String cityname= String.valueOf(job.get("name"));
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
    }

    public static JSONObject doPost(String url, JSONObject json) {

        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
