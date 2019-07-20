package com.test;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.weaver.general.TimeUtil;
import com.weavernorth.zgsy.webUtil.util.BaseDataUtil;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.joda.time.DateTime;
import org.junit.Test;
import weaver.conn.RecordSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestMain {

    @Test
    public void test() {
        String s = useCount("3.5", "2018-06-20");
        System.out.println(s);
        System.out.println(TimeUtil.getCurrentDateString().substring(0, 4));
    }

    /**
     * 计算可用天数
     *
     * @param allCount    总天数
     * @param currentDate 当前日期
     * @return 可用天数
     */
    private String useCount(String allCount, String currentDate) {
        Float all = Float.parseFloat(allCount);
        Float use;
        String currentMonthFirst = currentDate.substring(0, 8) + "01";
        int diff = TimeUtil.dateInterval(currentMonthFirst, currentDate);
        Integer currentMonth;
        if (diff < 14) {
            //上月
            currentMonth = Integer.parseInt(currentDate.substring(5, 7));
            if (currentMonth == 1) {
                currentMonth = 12;
            } else {
                currentMonth--;
            }
        } else {
            //当月
            currentMonth = Integer.parseInt(currentDate.substring(5, 7));
        }
        use = all / 12 * currentMonth;
        //计算小数部分 规则为计算的年假天数小于0.4，年假天数为0天；大于等于0.4小于0.9，年假天数为0.5天；大于等于0.9，年假天数为1天。
        String[] split = String.valueOf(use).split("\\.");
        //整数部分
        Float big;
        System.out.println("整数-》 " + split[0]);
        big = Float.parseFloat(split[0]);
        Float little = Float.parseFloat("0." + split[1]);
        System.out.println("小数-》 " + little);
        if (0.4 <= little && little < 0.9) {
            big += 0.5f;
        } else if (little >= 0.9) {
            big += 1;
        }
        return big.toString();
    }

    @Test
    public void test2() {
        String json = "{\n" +
                "\t\"id\": \"\",\n" +
                "\t\"operation\": insert,\n" +
                "\t\"deleted\": 0,\n" +
                "\t\"deptId\": \"irdM6gEeEADgAK8N0jOsvcznrtQ=\",\n" +
                "\t\"email\": \"malei51888@163.com\",\n" +
                "\t\"name\": \"test123\"\n" +
                "}";
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        //获取参数
        String operation = object.get("operation").getAsString();
        String id = object.get("id").isJsonNull() ? "" : object.get("id").getAsString();
        String deleted = object.get("deleted").isJsonNull() ? "" : object.get("deleted").getAsString();
        String deptId = object.get("deptId").isJsonNull() ? "" : object.get("deptId").getAsString();
        String email = object.get("email").isJsonNull() ? "" : object.get("email").getAsString();
        String name = object.get("name").isJsonNull() ? "" : object.get("name").getAsString();

        System.out.println(operation);
        System.out.println(id);
        System.out.println(deleted);
        System.out.println(deptId);
        System.out.println(email);
        System.out.println(name);


    }

    @Test
    public void test3() {
        try {

            //String endpoint = "http://oa2018.dadicinema.com/services/FinanceBase";
            String endpoint = "http://localhost:8080/services/getName";
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            //call.setTimeout(3000);
//            call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
//            call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
//            call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
//            call.setOperation("eas_user");
//            call.addParameter("json", XMLType.XSD_STRING, ParameterMode.IN);
            call.setOperationName(new javax.xml.namespace.QName("webservices.ofs.weaver.com.cn", "getName"));
            String result = (String) call.invoke(new Object[]{"nzm"});
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<String, String>(16);
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

    }

    @Test
    public void test4() {
        String json = "{\n" +
                "\t\"id\": 11,\n" +
                "\t\"eas_id\": \"hGgAAANteNu/DAQO\",\n" +
                "\t\"deleted\": 0,\n" +
                "\t\"name\": \"北京时尚传媒有限公司\",\n" +
                "\t\"bankList\": \"\"\n" +
                "}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        boolean bankList = jsonObject.get("bankList").isJsonArray();
        System.out.println(bankList);
    }

    @Test
    public void test5() {
        DateTime dateTime = new DateTime();

        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));

        RecordSet recordSet = new RecordSet();


    }

    @Test
    public void test6() throws InterruptedException {
        String xml = "<nam>nzm</nam><uri>123456@gd.zg</uri>";
        xml = "<test>" + xml + "</test>";
        Document doc;
        try {
            doc = DocumentHelper.parseText(xml);
            Element rootElt = doc.getRootElement();
            String nam = rootElt.elementText("nam");
            System.out.println(nam);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test7() throws DocumentException {
        String requestXml = "<?xml version=1.0 encoding=UTF-8?><result><success>0</success><operId>b48ecf820e704fd2b3e75d0e597caba3</operId ><msg><perUnhandled>11</perUnhandled><orgUnhandled>12</orgUnhandled></msg></result>";
        requestXml = requestXml.replace("<?xml version=1.0 encoding=UTF-8?>", "");
        Document doc = DocumentHelper.parseText(requestXml);
        Element rootElt = doc.getRootElement();
        String success = rootElt.elementTextTrim("success");
        if ("0".equals(success)) {
            //待收件数量节点；个人待收件数量
            String perUnhandled = rootElt.element("msg").elementTextTrim("perUnhandled");
            //待收件数量节点；单位待收件数量
            String orgUnhandled = rootElt.element("msg").elementTextTrim("orgUnhandled");
            System.out.println(perUnhandled + ", " + orgUnhandled);
        }

    }

    @Test
    public void test8() {
        BaseDataUtil baseDataUtil = new BaseDataUtil();
        String baseData = baseDataUtil.getBaseData("008");
        System.out.println(baseData);


    }

    @Test
    public void test9() {
        String str = "";
        int numDocIds = getNumDocIds(str);
        System.out.println(numDocIds);

        System.out.println(str.split(",").length);

        String[] ss = {};
        System.out.println(ss.length);
    }


    private static int getNumDocIds(String docids) {
        int num = 0;
        // 用英文版逗号分隔docid
        String[] strarray = docids.split(",");
        for (int i = 0; i < strarray.length; i++) {
            num += 1;
        }
        return num;
    }
}








