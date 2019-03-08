package com.weavernorth.gjcw;

import net.sf.json.JSONObject;


import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.integration.util.HTTPUtil;
import weaver.interfaces.workflow.action.Action;

import weaver.soa.workflow.request.RequestInfo;



/**
 * Created by zhaohr on 2018/11/22.
 * 认证接口
 */
public class RZTicketPost extends BaseBean implements Action {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestid = requestInfo.getRequestid();
        this.writeLog("requestid-------------------"+requestid);
        RecordSet rs = new RecordSet();
        rs.execute("select * from formtable_main_2 where requestid='" + requestid + "'");
        this.writeLog("数据库服务开启--------------------------");
        rs.next();
        String lcbh = rs.getString("lcbh");
        Double bxze = rs.getDouble("bxze");
        Double se=rs.getDouble("clfjxs");
        Double qtfy=rs.getDouble("qtfy");
        double zje=bxze+se+qtfy;
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("BXDH", lcbh);
        jsonObject.put("BXJE", String.valueOf(zje));
        jsonObject.put("QQLX", "RZ");
        String contentJiami= Base64Util.encode(jsonObject.toString());
        JSONObject map1 = new JSONObject();
        map1.put("content",contentJiami);
        this.writeLog("要传输的Json---------------------"+map1);
        String url = "http://10.10.1.131:8080/finance/bxdfpgl/bxdCxForOA/bxdfpInfoToOA";

        String mao= HTTPUtil.doPost(url,map1);
        JSONObject object = JSONObject.fromObject(mao);
        this.writeLog("-------------------"+object);
        String contentcode1 = object.getString("content");
        String contentbase= Base64Util.decode(contentcode1);

        JSONObject object1 = JSONObject.fromObject(contentbase);
        String contentcode2 = object1.getString("FHJG");
        this.writeLog("-----------------contengcode2"+contentcode2);
        if (contentcode2.equals("0")) {
            RecordSet rs1 = new RecordSet();
            rs1.execute("update formtable_main_2 set rzjg =' 成功' where  requestid ='" + requestid + "'");
        }
        if (contentcode2.equals("1")) {
            RecordSet rs1 = new RecordSet();
            rs1.execute("update formtable_main_2 set rzjg =' 失败' where  requestid ='" + requestid + "'");
        }
        if (contentcode2.equals("2")) {
            RecordSet rs1 = new RecordSet();
            rs1.execute("update formtable_main_2 set rzjg =' 含重复发票' where  requestid ='" + requestid + "'");
        }


        return SUCCESS;
    }

}
