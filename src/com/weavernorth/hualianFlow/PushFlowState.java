package com.weavernorth.hualianFlow;

import com.alibaba.fastjson.JSONObject;
import weaver.conn.RecordSet;
import weaver.soa.workflow.request.RequestInfo;
import weaver.workflow.action.BaseAction;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 流程状态推送测试
 */
public class PushFlowState extends BaseAction {

    private static final String URL = "http://10.1.69.126:8182/api/WorkFlow/RequestWorkFlow";

    private static final String APP_KEY = "368bu094169a50f3bd690ba37ebey7026"; // 秘钥

    private static final String APP_ID = ""; // 供应商ID

    @Override
    public String execute(RequestInfo requestInfo) {
        String requestId = requestInfo.getRequestid();
        String operateType = requestInfo.getRequestManager().getSrc();
        int formId = requestInfo.getRequestManager().getFormid();
        String tableName = "";
        RecordSet recordSet = new RecordSet();
        recordSet.executeQuery("SELECT tablename FROM workflow_bill WHERE id = " + formId);
        if (recordSet.next()) {
            tableName = recordSet.getString("tablename");
        }

        this.writeLog("流程状态推送 Start requestid=" + requestId + "  operatetype --- " + operateType + "   fromTable --- " + tableName);
        try {
            String remark = requestInfo.getRequestManager().getRemark(); // 当前节点签字意见

            JSONObject sendJsonObj = new JSONObject(true);
            sendJsonObj.put("appid", "");
            sendJsonObj.put("ts", "");
            sendJsonObj.put("sig", "");
            sendJsonObj.put("format", "");
            sendJsonObj.put("userip", "");

            JSONObject dataJsonObj = new JSONObject(true);
            dataJsonObj.put("ExtInstanceID", "");
            dataJsonObj.put("Result", "");
            dataJsonObj.put("CreateDate", "");
            dataJsonObj.put("LoginName", "");
            dataJsonObj.put("Description", "");

            sendJsonObj.put("Approve", dataJsonObj);

            String sendJsonStr = sendJsonObj.toJSONString();
            this.writeLog("签字意见接口发送数据：" + sendJsonStr);

            // 调用签字意见接口
            String returnJson = sendPost(URL, sendJsonStr);
            this.writeLog("推送签字意见接口返回： " + returnJson);

            JSONObject returnJsonObj = JSONObject.parseObject(returnJson);
            String code = returnJsonObj.getString("code");
            if (!"200".equals(code)) {
                requestInfo.getRequestManager().setMessageid("110000");
                requestInfo.getRequestManager().setMessagecontent("流程状态推送 异常： " + returnJson);
                return "0";
            }

            this.writeLog("流程状态推送测试 End ===============");
        } catch (Exception e) {
            this.writeLog("流程状态推送测试 异常： " + e);
            requestInfo.getRequestManager().setMessageid("110000");
            requestInfo.getRequestManager().setMessagecontent("流程状态推送 异常： " + e);
            return "0";
        }

        return "1";
    }

    private String remarkChange(){
        String returnStr = "";



        return returnStr;
    }

    private String sendPost(String url, String paramsJson) {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            java.net.URL httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(9000);
            conn.setReadTimeout(9000);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(paramsJson);

            out.flush();
            out.close();

            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response.append(lines);
            }
            // 断开连接
            conn.disconnect();
        } catch (Exception e) {
            this.writeLog("佳杰总部点单sendPost异常： " + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response.toString();
    }
}
