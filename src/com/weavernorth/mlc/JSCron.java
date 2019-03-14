package com.weavernorth.mlc;


import com.weaver.general.BaseBean;
import com.weaver.general.TimeUtil;
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
import weaver.conn.RecordSet;
import weaver.formmode.setup.ModeRightInfo;
import weaver.integration.util.HTTPUtil;
import weaver.interfaces.schedule.BaseCronJob;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhaohr on 2019/2/22.
 */
public class JSCron extends BaseCronJob {
    @Override
    public void execute() {
        BaseBean baseBean=new BaseBean();
        ConnStatement statement = new ConnStatement();
        String sql = "insert into uf_xcjssj(Orderid,Refund,RebookQueryFee,Dept1,Dept2,Coupon,ReBookingServiceFee,EmployeeID,PassengerName,RefundServiceFee,RealAmount,OrderDate,TakeOffTime,Remark,OrderDesc,Flight,Class,PriceRate,QPrice,price,Tax,OilFee,SendTicketFee,InsuranceFee,ServiceFee,formmodeid,modedatacreater,modedatacreatertype,modedatacreatedate,modedatacreatetime) values(? ,?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";
        String ticketUrl = "https://ct.ctrip.com/corpservice/authorize/getticket";
        String appKey = "obk_mljr";
        String appSecurity = "obk_mljr";
        JSONObject map = new JSONObject();
        map.put("appKey", appKey);
        map.put("appSecurity", appSecurity);
        String ticket = HTTPUtil.doPost(ticketUrl, map);
        System.out.println("*******************************" + ticket);
        JSONObject object1 = JSONObject.fromObject(ticket);
        String contentcode2 = object1.getString("Token");
        System.out.println("*******************************" + contentcode2);
        String ticketUrl1 = "https://ct.ctrip.com/switchapi/FlightOrderSettlement/GetCorpAccountFlightOrderSettlements?type=json";
        JSONObject map2 = new JSONObject();
        map2.put("AppKey", appKey);
        map2.put("Ticket", contentcode2);
        JSONObject map1 = new JSONObject();
        map1.put("Auth", map2);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        map1.put("DateFrom", DateCut(dateNowStr));
        map1.put("DateTo", dateNowStr);
        //System.out.println(map1);

        JSONObject aaa= doPost(ticketUrl1,map1);
        String bbb=aaa.getString("FlightOrderAccountSettlementList");
        //System.out.println(bbb);
        JSONArray json=JSONArray.fromObject(bbb);
        //String ccc=json.getString("OrderSettlementList");
        if (json.size() > 0) {
            for (int i = 0; i < json.size(); i++) {
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject job = json.getJSONObject(i);
                try {

                    String orderset=String.valueOf(job.get("OrderSettlementList"));
                    JSONArray json2=JSONArray.fromObject(orderset);
                    if(json2.size()>0){
                        for (int k = 0; k < json2.size(); k++) {
                            JSONObject job3 = json2.getJSONObject(k);
                            String orderinfo = String.valueOf(job3.getString("OrderSettlementBaseInfo"));
                            String orderlist=String.valueOf(job3.getString("OrderBaseInfo"));
                            String orderpassenger=String.valueOf(job3.getString("OrderPassengerInfo"));
                            String OrderFlightInfo=String.valueOf(job3.getString("OrderFlightInfo"));
                            JSONObject json4 = JSONObject.fromObject(orderinfo);
                            JSONObject json5 = JSONObject.fromObject(orderlist);
                            JSONObject json6 = JSONObject.fromObject(orderpassenger);
                            JSONObject json7 = JSONObject.fromObject(OrderFlightInfo);
                            String Orderid = json4.getString("OrderID");
                            String Refund = json4.getString("Refund");
                            String RebookQueryFee = json4.getString("RebookQueryFee");
                            String Dept1 = json5.getString("Dept1");
                            String Dept2 = json5.getString("Dept2");
                            String Coupon = json4.getString("Coupon");
                            String ReBookingServiceFee = json4.getString("ReBookingServiceFee");
                            String EmployeeID = json5.getString("EmployeeID");
                            String PassengerName = json5.getString("Name");
                            String RefundServiceFee = json4.getString("RefundServiceFee");
                            String Amount = json4.getString("Amount");
                            String OrderDate = json5.getString("OrderDate");
                            String TakeOffTime = json7.getString("TakeOffTime");
                            String Remark = json4.getString("Remark");
                            String Sequence = json4.getString("Sequence");
                            String Flight = json7.getString("Flight");
                            String Class = json7.getString("Class");
                            String PriceRate = json7.getString("PriceRate");
                            String price = json4.getString("Price");
                            String Tax = json4.getString("Tax");
                            String OilFee = json4.getString("OilFee");
                            String SendTicketFee = json4.getString("SendTicketFee");
                            String InsuranceFee = json4.getString("InsuranceFee");
                            String ServiceFee = json4.getString("ServiceFee");

                            statement.setStatementSql(sql);
                            statement.setString(1, Orderid);
                            statement.setString(2, Refund);
                            statement.setString(3, RebookQueryFee);
                            statement.setString(4, Dept1);
                            statement.setString(5, Dept2);
                            statement.setString(6, Coupon);
                            statement.setString(7, ReBookingServiceFee);
                            statement.setString(8, EmployeeID);
                            statement.setString(9, PassengerName);
                            statement.setString(10, RefundServiceFee);
                            statement.setString(11, Amount);
                            statement.setString(12, OrderDate);
                            statement.setString(13, TakeOffTime);
                            statement.setString(14, Remark);
                            statement.setString(15, Sequence);
                            statement.setString(16, Flight);
                            statement.setString(17, Class);
                            statement.setString(18, PriceRate);
                            statement.setString(19, "无");
                            statement.setString(20, price);
                            statement.setString(21, Tax);
                            statement.setString(22, OilFee);
                            statement.setString(23, SendTicketFee);
                            statement.setString(24, InsuranceFee);
                            statement.setString(25, ServiceFee);
                            RecordSet recordSet=new RecordSet();
                            recordSet.execute("select max(id) id from uf_xcjssj");
                            int maxId = 0;
                            if (recordSet.next()) {
                                maxId = recordSet.getInt("id");
                            }

                            ModeRightInfo ModeRightInfo = new ModeRightInfo();
                            ModeRightInfo.setNewRight(true);
                            ModeRightInfo.editModeDataShare(1, 19, maxId);
                            statement.setInt(26, 19);//模块id
                            statement.setString(27, "1");//创建人id
                            statement.setString(28, "0");//一个默认值0
                            statement.setString(29, TimeUtil.getCurrentTimeString().substring(0, 10));
                            statement.setString(30, TimeUtil.getCurrentTimeString().substring(11));
                            statement.executeUpdate();





                        }
                    }


                } catch ( Exception e ) {
                    baseBean.writeLog("8888888888888888888"+e.getMessage());
                }


            }
        }
    }
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
    public String DateCut(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date dt = null;
        try {
            dt = sdf.parse(str);
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DATE, -7);// 日期减7天
        return sdf.format(rightNow.getTime());
    }
}
