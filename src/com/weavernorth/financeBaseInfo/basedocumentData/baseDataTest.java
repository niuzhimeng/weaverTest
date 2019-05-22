package com.weavernorth.financeBaseInfo.basedocumentData;

import com.weavernorth.financeBaseInfo.basedocumentData.baseDataTestClient.FinanceBase;
import com.weavernorth.financeBaseInfo.basedocumentData.baseDataTestClient.FinanceBaseLocator;
import com.weavernorth.financeBaseInfo.basedocumentData.baseDataTestClient.FinanceBasePortType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import javax.xml.rpc.ServiceException;
import java.lang.reflect.Array;
import java.rmi.RemoteException;

public class baseDataTest {
    @Test
    public void baseDateTest() throws JSONException, ServiceException, RemoteException {
        //拼接json
        JSONObject jsonObject = new JSONObject();
        String json="{\n" +
                "\t\"my_id\":111,\n" +
                "\t\"code\":\"22222222222\",\n" +
                "\t\"name\":\"111\",\n" +
                "\t\"deleted\":\"1\"\n" +
                "}\n";
        //easAccount
//        jsonObject.put("id",8);
 //         jsonObject.put("name", "77");
//        jsonObject.put("assistant_id","88");
//        jsonObject.put("deleted", 1);
//        jsonObject.put("code","666");


//        jsonObject.put("my_id",111);
//        jsonObject.put("eas_id",222);
//        jsonObject.put("email","malei51888@163.com");
//        jsonObject.put("deptId","irdM6gEeEADgAK8N0jOsvcznrtQ");
        //jsonObject.put("name","0000088888");
//        jsonObject.put("ctcom_name","jjdkj");
//        jsonObject.put("ctdep_name","kkkoo");
//        jsonObject.put("cpcom_name","mmll");

//        jsonObject.put("corp","1");
//        jsonObject.put("org","1");
//        jsonObject.put("macom_name","fftt");
//        jsonObject.put("addep_name","vvv");
//        jsonObject.put("parent_id","ccc");

        //jsonObject.put("type","11");
//        jsonObject.put("deleted","1");
//        jsonObject.put("code","65465165/");
        //调用接口
        FinanceBaseLocator financeBaseLocator = new FinanceBaseLocator();
        FinanceBasePortType financeBaseHttpPort = financeBaseLocator.getFinanceBaseHttpPort();
        String s1=financeBaseHttpPort.easTaxCount(json);
        //String s1=financeBaseHttpPort.easOrg(jsonObject.toString());
        //String s1 = financeBaseHttpPort.easAssistant(jsonObject.toString());
        //String s = financeBaseHttpPort.easAccount(jsonObject.toString());
        //System.out.println("---->"+jsonObject);
        System.out.println("---->"+s1);
    }
}
