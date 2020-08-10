package com.weavernorth.dadiyingyuan.acceptPayFeeStatus;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import weaver.conn.ConnStatement;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

public class acceptPayFeeStatusImpl implements acceptPayFeeStatusI{
    private BaseBean baseBean = new BaseBean();
    public acceptPayFeeStatusImpl() {

    }
    /*
     * @function 接收付款状态更新至流程表单实现方法
     * @date 2019-1-8
     * */
    @Override
    public String acceptPayFeeStatus(String json) {
        /*json格式--->[
            {
                "oaTabBaseName":"formtable_main_219",
                "message": "借款单未生成付款单!",
                "number": "OAH00001",
                "success": "false"
            }
        ]*/
        this.baseBean.writeLog("核算项目类别明细表接收到的json-> " + json);
        JsonObject returnObject = new JsonObject();
        ConnStatement statement = new ConnStatement();
        JsonArray asJsonArray = (new JsonParser()).parse(json).getAsJsonArray();
        for (int i = 0; i <asJsonArray.size() ; i++) {
            JsonObject asJsonObject = asJsonArray.get(i).getAsJsonObject();
            String message = asJsonObject.get("message").getAsString();
            this.baseBean.writeLog("收到的返回信息--->"+message);
            String dh = asJsonObject.get("number").getAsString();
            this.baseBean.writeLog("返回的单号--->"+dh);
            String success = asJsonObject.get("success").getAsString();
            this.baseBean.writeLog("返回的信息状态--->"+success);
            String oaTabBaseName = asJsonObject.get("oaTabBaseName").getAsString();
            this.baseBean.writeLog("返回的流程表明--->"+oaTabBaseName);

            /****
             *往流程表单插入acceptPayFeeStatus数据
             ****/
            /*验证requestid是否存在*/
            RecordSet requestidSave = new RecordSet();
            String requestidSql="select dh from "+oaTabBaseName+" where dh="+"'"+dh+"'";
            requestidSave.execute(requestidSql);
            requestidSave.next();
            String dhValue = requestidSave.getString("dh");//获取requestid

            if(dhValue.equals(dh)){
                RecordSet rs = new RecordSet();
                String updateSql="update "+oaTabBaseName+" set fklb='"+message+"' where dh="+"'"+dh+"'";
                this.baseBean.writeLog("update------->"+updateSql);
                boolean execute = rs.execute(updateSql);
                this.baseBean.writeLog("执行成功与否"+execute);
                returnObject.addProperty("success", "true");
                returnObject.addProperty("code", "200");
                returnObject.addProperty("message", "执行插入付款状态成功");

            }else{
                this.baseBean.writeLog("执行插入付款状态失败，没有对应的单号");
                returnObject.addProperty("success", "false");
                returnObject.addProperty("code", "201");
                returnObject.addProperty("message", "执行插入付款状态失败，没有对应的单号");
            }
        }
        return returnObject.toString();
    }
}
