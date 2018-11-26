package com.weavernorth.gaoji.sap.action.util;

import com.alibaba.druid.util.Base64;
import com.weavernorth.gaoji.sap.action.vo.POTableVo;
import com.weavernorth.gaoji.util.OkHttpUtils;
import weaver.conn.ConnStatement;
import weaver.general.BaseBean;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionUtil {

    private static BaseBean baseBean = new BaseBean();

    public static String sendPo(String json) {
        Map<String, String> map = new HashMap<String, String>();
        String URL = baseBean.getPropValue("poMutualConn","POURL");
        String userPwd = baseBean.getPropValue("poMutualConn","poAuthPwd");
        String byteArrayToBase64 = Base64.byteArrayToBase64(userPwd.getBytes());
        map.put("Authorization", "Basic " + byteArrayToBase64);
        return OkHttpUtils.postJsonHeader(URL, json, map);
    }

    /**
     * 存储data
     */
    public static void saveDataJson(List<POTableVo> list) {
        ConnStatement statement = new ConnStatement();
        String sql = "insert into uf_sapData(bguid, btype, bsource, bdestination, bdatetime, bstatus, bcallback, bversion, bdatahash, bkeys, bdata, createtime, modifytime) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(POTableVo baoXiaoVo : list){
                statement.setStatementSql(sql);
                statement.setString(1, baoXiaoVo.getBguid());
                statement.setInt(2, Integer.parseInt(baoXiaoVo.getBtype()));
                statement.setInt(3, Integer.parseInt(baoXiaoVo.getBsource()));
                statement.setInt(4, Integer.parseInt(baoXiaoVo.getBdestination()));
                statement.setString(5, baoXiaoVo.getBdatetime());
                statement.setInt(6, Integer.parseInt(baoXiaoVo.getBstatus()));
                if(baoXiaoVo.getBcallback().length() < 95){
                    statement.setString(7, baoXiaoVo.getBcallback());
                } else {
                    statement.setString(7, baoXiaoVo.getBcallback().substring(0,95));
                }
                statement.setString(8, baoXiaoVo.getBversion());
                statement.setString(9, baoXiaoVo.getBdatahash());
                if(baoXiaoVo.getBkeys().length() < 95){
                    statement.setString(10, baoXiaoVo.getBkeys());
                } else {
                    statement.setString(10, baoXiaoVo.getBkeys().substring(0,95));
                }
                if(baoXiaoVo.getBdata().length() < 3500){
                    statement.setString(11, baoXiaoVo.getBdata());
                } else {
                    statement.setString(11, baoXiaoVo.getBdata().substring(0,3500));
                }
                statement.setString(12, dateformat1.format(new Date()));
                statement.setString(13, dateformat1.format(new Date()));
                statement.executeUpdate();
            }
        } catch (Exception e) {
            baseBean.writeLog("本地存储json异常： " + e);
        } finally {
            statement.close();
        }
    }

    public static String md5(String content) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = content.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return (new String(str));
        } catch (Exception e) {
            baseBean.writeLog("ActionUtil md5异常： " + e);
        }
        return "";
    }
}


