package com.weavernorth.gjcw;

import com.weaver.general.BaseBean;
import net.sf.json.JSONObject;

import sun.misc.BASE64Encoder;
import weaver.conn.RecordSet;
import weaver.integration.util.HTTPUtil;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.io.File;
import java.io.FileInputStream;

import java.io.InputStream;


/**
 * Created by zhaohr on 2018/11/21.
 * 电子发票接口
 */
public class DZFPTicketPost extends BaseBean implements Action {
    @Override
    public String execute(RequestInfo requestInfo) {
        String requestid = requestInfo.getRequestid();
        RecordSet rs = new RecordSet();
        rs.execute("select * from formtable_main_2 where requestid='" + requestid + "'");
        rs.next();
        String lcbh = rs.getString("lcbh");
        Double bxze = rs.getDouble("bxze");
        Double se = rs.getDouble("clfjxs");
        Double qtfy = rs.getDouble("qtfy");
        double zje = bxze + se + qtfy;
        String imageIdStr = rs.getString("fj");
        String[] images = imageIdStr.split(",");
        RecordSet imageSet = new RecordSet();
        for (String id : images) {
            imageSet.executeQuery("select * from docimagefile where docid = '" + id + "'");
            if (imageSet.next()) {
                String imagefileid = imageSet.getString("imagefileid");//路径表关联字段
                imageSet.executeQuery("select * from imagefile where imagefileid = '" + imagefileid + "'");
                imageSet.next();
                String filerealpath = imageSet.getString("filerealpath");//存放路径
                File file = new File(filerealpath);
                InputStream in = null;
                try {
                    in = new FileInputStream(file);
                    byte[] data = new byte[in.available()];
                    in.read(data);
                    in.close();
                    BASE64Encoder encoder = new BASE64Encoder();
                    String encode = encoder.encode(data) + ",";
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("BXDH", lcbh);
                    jsonObject.put("BXJE", String.valueOf(zje));
                    jsonObject.put("QQLX", "DZFP");
                    jsonObject.put("DZFPWJ", encode);
                    String contentJiami = Base64Util.encode(jsonObject.toString());
                    JSONObject map1 = new JSONObject();
                    map1.put("content", contentJiami);
                    String url = "http://10.10.1.131:8080/finance/bxdfpgl/bxdCxForOA/bxdfpInfoToOA";
                    String mao = HTTPUtil.doPost(url, map1);
                    JSONObject object = JSONObject.fromObject(mao);
                    String contentcode = object.getString("code");
                    this.writeLog("返回结果----------------------------" + contentcode);
                    if (contentcode.equals("0000")) {
                        RecordSet rs1 = new RecordSet();
                        rs1.execute("update formtable_main_2 set dzfpjg ='同步成功' where  requestid ='" + requestid + "'");
                    } else {
                        RecordSet rs1 = new RecordSet();
                        rs1.execute("update formtable_main_2 set dzfpjg ='同步失败' where  requestid ='" + requestid + "'");
                    }
                } catch ( Exception e ) {
                    e.printStackTrace();
                }


            }
        }
        return SUCCESS;
    }

}
