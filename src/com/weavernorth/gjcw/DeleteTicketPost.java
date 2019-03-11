package com.weavernorth.gjcw;


import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.general.TimeUtil;
import weaver.integration.util.HTTPUtil;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * 退回删除接口
 */
public class DeleteTicketPost extends BaseBean implements Action {

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestid = requestInfo.getRequestid();
        this.writeLog("国机退回删除接口执行=============== " + TimeUtil.getCurrentTimeString() + " requestid: " + requestid);
        try {
            RecordSet rs = new RecordSet();
            rs.execute("select * from formtable_main_2 where requestid='" + requestid + "'");
            rs.next();
            String lcbh = rs.getString("lcbh");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("BXDH", lcbh);
            jsonObject.put("QQLX", "DELFP");

            this.writeLog("发送json--------------------" + jsonObject);
            String contentJiami = Base64Util.encode(jsonObject.toString());
            Map<String, String> map1 = new HashMap<String, String>();
            map1.put("content", contentJiami);
            String url = "http://10.10.1.79:8080/finance/bxdfpgl/bxdCxForOA/bxdfpInfoToOA";

            // 调用接口
            String strReturn = HTTPUtil.doPost(url, map1);

            this.writeLog("接口返回Str： " + strReturn);
            this.writeLog("国机退回删除接口结束=============== " + TimeUtil.getCurrentTimeString());
        } catch (Exception e) {
            this.writeLog("国机退回删除接口异常： " + e);
        }

        return SUCCESS;
    }


}
