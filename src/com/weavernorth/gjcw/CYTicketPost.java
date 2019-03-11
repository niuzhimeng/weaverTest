package com.weavernorth.gjcw;


import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.integration.util.HTTPUtil;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;


/**
 * Created by zhaohr on 2018/11/16.
 * 查验接口
 */
public class CYTicketPost extends BaseBean implements Action {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestid = requestInfo.getRequestid();
        this.writeLog("国机查验接口执行=============== " + TimeUtil.getCurrentTimeString() + " requestid: " + requestid);
        try {

            RecordSet rs = new RecordSet();
            rs.execute("select * from formtable_main_2 where requestid='" + requestid + "'");
            rs.next();
            String lcbh = rs.getString("lcbh");
            Double bxze = rs.getDouble("bxze");
            Double se = rs.getDouble("clfjxs");
            Double qtfy = rs.getDouble("qtfy");
            double zje = bxze + se + qtfy;

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("BXDH", lcbh);
            jsonObject.put("BXJE", String.valueOf(zje));
            jsonObject.put("QQLX", "CY");
            this.writeLog("发送json--------------------" + jsonObject);
            String contentJiami = Base64Util.encode(jsonObject.toString());
            JSONObject map1 = new JSONObject();
            map1.put("content", contentJiami);
            String url = "http://10.10.1.79:8080/finance/bxdfpgl/bxdCxForOA/bxdfpInfoToOA";

            // 调用接口
            String mao = HTTPUtil.doPost(url, map1);

            this.writeLog("接口返回Str： " + mao);

            JSONObject object = JSONObject.fromObject(mao);
            String contentcode1 = object.getString("content");
            String contentbase = Base64Util.decode(contentcode1);
            this.writeLog("--------------------" + contentbase);
            JSONObject object1 = JSONObject.fromObject(contentbase);
            String contentcode2 = object1.getString("FHJG");
            this.writeLog("--------------------" + contentcode2);
            if (contentcode2.equals("0")) {
                RecordSet rs2 = new RecordSet();
                rs2.execute("update formtable_main_2 set cyjg = '成功' where  requestid ='" + requestid + "'");
            }
            if (contentcode2.equals("1")) {
                RecordSet rs2 = new RecordSet();
                rs2.execute("update formtable_main_2 set cyjg = '失败' where  requestid ='" + requestid + "'");
            }
            if (contentcode2.equals("2")) {
                RecordSet rs2 = new RecordSet();
                rs2.execute("update formtable_main_2 set cyjg = '含重复发票' where  requestid ='" + requestid + "'");
            }
            this.writeLog("国机查验接口结束=============== " + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            this.writeLog("查验接口异常： " + e);
        }

        return SUCCESS;
    }


}
