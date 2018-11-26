package com.weavernorth.B1.zyml;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.weavernorth.B1.zyml.po.CatalogAll;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.integration.util.HTTPUtil;
import weaver.interfaces.schedule.BaseCronJob;

import java.lang.reflect.Type;
import java.util.List;

public class TimedGetAllCatalogList extends BaseCronJob {

    private static final String URL = "http://222.102.23.7:7072/api/CatalogInfoSearch/getAllCatalogList";

    @Override
    public void execute() {
        BaseBean baseBean = new BaseBean();
        baseBean.writeLog("获取所有资源目录信息--------开始执行");
        try {
            String returnStr = HTTPUtil.doGet(URL);
            //String returnStr = "{\"obj\":[{\"id\":\"40288adb5fe1a53f015fe2e9db7c0017\",\"restitle\":\"法律法规信息\",\"status\":\"4\",\"sourcecodemiddle\":\"444444003176\",\"source_code_prev\":\"307021\",\"source_code_last\":\"000009\",\"cat_id\":\"3>07>021>444444003>176\",\"metadata_id\":\"40288acb5e996c1d015e9e27dc600005\",\"create_user\":\"root\",\"create_date\":1511340498000,\"dept_ids\":\"\",\"do_user_code\":\"\",\"do_cause\":\"\",\"rootcode\":\"444444003\",\"dept_names\":\"\",\"kvlist\":\"[]\",\"tabname\":\"g_信息资源目录模板_recs\",\"form_html\":\"\",\"mustfields\":\"\",\"catlogid\":\"40288adb5fa04e15015fbf3b270d0000\",\"path\":\"部门信息资源目录>省（自治区、直辖市）和计划单列市>湖南省>部门信息资源目录>湖南省安全生产监督管理局\",\"resourceapply\":\"湖南省\",\"resourceapplyinner\":\"湖南安监局\",\"resourceapplycode\":\"11430000743187451F\",\"resourcetype\":\"电子文件\",\"resourcefiletype\":\"其他\",\"otherresourcedescribe\":\"\",\"net\":\"2政务外网\",\"resourceposition\":\"无\",\"resourcesummary\":\"法律法规颁布部门\",\"relationname\":\"\",\"relationsystem\":\"无\",\"totaldatastore\":\"0\",\"sharedatastore\":\"0\",\"opendatastore\":\"0\",\"totalstructrecords\":\"0\",\"sharestructrecords\":\"0\",\"openstructrecords\":\"0\",\"allcode\":\"3070211760000002004006/000033\",\"catalog_code\":\"3070211760000002004006/000033\",\"updatecycle\":\"实时\",\"publishdate\":\"2017年11月15日\",\"subjecttype\":\"006\"},{\"id\":\"40288adb5fe1a53f015fe2e9db7c0018\",\"restitle\":\"法律法规信息\",\"status\":\"4\",\"sourcecodemiddle\":\"444444003176\",\"source_code_prev\":\"307021\",\"source_code_last\":\"000009\",\"cat_id\":\"3>07>021>444444003>176\",\"metadata_id\":\"40288acb5e996c1d015e9e27dc600005\",\"create_user\":\"root\",\"create_date\":1511340498000,\"dept_ids\":\"\",\"do_user_code\":\"\",\"do_cause\":\"\",\"rootcode\":\"444444003\",\"dept_names\":\"\",\"kvlist\":\"[]\",\"tabname\":\"g_信息资源目录模板_recs\",\"form_html\":\"\",\"mustfields\":\"\",\"catlogid\":\"40288adb5fa04e15015fbf3b270d0000\",\"path\":\"部门信息资源目录>省（自治区、直辖市）和计划单列市>湖南省>部门信息资源目录>湖南省安全生产监督管理局\",\"resourceapply\":\"湖南省\",\"resourceapplyinner\":\"湖南安监局\",\"resourceapplycode\":\"11430000743187451F\",\"resourcetype\":\"电子文件\",\"resourcefiletype\":\"其他\",\"otherresourcedescribe\":\"\",\"net\":\"2政务外网\",\"resourceposition\":\"无\",\"resourcesummary\":\"法律法规颁布部门\",\"relationname\":\"\",\"relationsystem\":\"无\",\"totaldatastore\":\"0\",\"sharedatastore\":\"0\",\"opendatastore\":\"0\",\"totalstructrecords\":\"0\",\"sharestructrecords\":\"0\",\"openstructrecords\":\"0\",\"allcode\":\"3070211760000002004006/000033\",\"catalog_code\":\"3070211760000002004006/000033\",\"updatecycle\":\"实时\",\"publishdate\":\"2017年11月10日\",\"subjecttype\":\"006\"}],\"ok\":true}";
            baseBean.writeLog("获取所有资源目录信息 返回json： " + returnStr);
            JsonObject jsonObject = new JsonParser().parse(returnStr).getAsJsonObject();
            if (jsonObject.get("ok").getAsBoolean()) {
                JsonArray jsonArray = jsonObject.get("obj").getAsJsonArray();
                List<CatalogAll> interFirstList = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<CatalogAll>>() {
                }.getType());
                RecordSet recordSet = new RecordSet();
                int size = interFirstList.size();
                baseBean.writeLog("获取所有资源目录信息 返回数据数量： " + size);
                if (size > 0) {
                    recordSet.execute("delete from uf_getAllCatalogLis");
                    for (CatalogAll catalogAll : interFirstList) {
                        catalogAll.insert();
                    }
                }
            }
        } catch (Exception e) {
            baseBean.writeLog("获取所有资源目录信息 异常： " + e);
        }
    }
}
