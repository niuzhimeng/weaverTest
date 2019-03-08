package com.weavernorth.gjcw;


import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

import weaver.integration.util.HTTPUtil;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

/**
 * Created by zhaohr on 2018/11/27.
 */
public class HTMLTicketPost extends BaseBean implements Action {
    @Override
    public String execute(RequestInfo requestInfo) {
        String requestid = requestInfo.getRequestid();
        RecordSet rs = new RecordSet();
        rs.execute("select * from formtable_main_2 where requestid='" + requestid + "'");
        rs.next();
        String lcbh = rs.getString("lcbh");

        String url = "http://10.10.1.131:8080/finance/bxdfpgl/bxdCxForOA/bxdfpck?bxdh="+lcbh;
        JSONObject map1 = new JSONObject();
        String mao= HTTPUtil.doPost(url,map1);
        JSONObject object = JSONObject.fromObject(mao);
        String contentcode1=object.getString("url");
        String contentcode2=object.getString("code");
        RecordSet rs1=new RecordSet();
        rs1.execute("update formtable_main_2 set html ='"+contentcode1+"' where  requestid ='" + requestid + "'");
        RecordSet rs2=new RecordSet();
        if (contentcode2.equals("0000")) {
            rs2.execute("update formtable_main_2 set htmljg ='HTML返回结果成功' where  requestid ='" + requestid + "'");
        }else {

           rs2.execute("update formtable_main_2 set htmljg ='HTML返回结果失败' where  requestid ='" + requestid + "'");

       }
        return SUCCESS;
    }

}
