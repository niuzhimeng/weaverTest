package com.test.webserviceTest;


import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.test.webserviceTest.vo.Student;
import com.weavernorth.B1.zyml.po.CatalogAll;
import com.weavernorth.gaoji.vo.OrganizationVo;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import weaver.general.AES;
import weaver.general.TimeUtil;
import weaver.hrm.webservice.HrmServiceXmlUtil;

import java.io.*;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MyTest {

    @Test
    public void test1() {
        String xml = "<info> \n" +
                "  <name>牛智萌</name>  \n" +
                "  <age>24</age>  \n" +
                "  <sex>男</sex> \n" +
                "</info>";
        try {
            String endpoint = "http://localhost:8080/services/getName";
            Service service = new Service();
            Call _call = (Call) service.createCall();
            _call.setTargetEndpointAddress(endpoint);
            //call.setTimeout(3000);

            _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
//            _call.setOperation("getName");
//            _call.addParameter("xml", XMLType.XSD_STRING, ParameterMode.IN);
            _call.setOperationName(new javax.xml.namespace.QName("webservices.ofs.weaver.com.cn.", "getName"));
            String result = (String) _call.invoke(new Object[]{xml});
            System.out.println("result is--》》 " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 寻找一段数字重复的
     */
    @Test
    public void test2() {

        int a[] = {1, 2, 3, 4, 5, 10, 7, 8, 9, 11, 14, 12, 13, 10, 6, 15, 1};
        int b[] = new int[a.length];
        for (int anA : a) {
            if (b[anA]++ > 0) {//以a数组的值作为b的下标，如果有重复的则会在同一个下标处多加1
                System.out.println(anA);
            }
        }


    }

    @Test
    public void test3() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 2) {
                iterator.remove();
            }
        }

        for (Integer i : list) {
            System.out.println(i + ", ");
        }

    }

    @Test
    public void test6() {
        //中广上洋创建流程测试
        try {
            String requestXml = "";
            String endpoint = "http://Localhost:8080/services/HrmService";
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
            call.setOperationName(new javax.xml.namespace.QName("webservices.services.weaver.com.cn", "getHrmSubcompanyInfoXML"));
            String requestid = (String) call.invoke(new Object[]{requestXml});
            System.out.println(requestid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7() throws Exception {
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL("http://hangder-open.gnway.cc:6080/ZGSYWebService_IIS/LoadService.asmx?wsdl"));
            call.setSOAPActionURI("http://tempuri.org/MaterialData");
            call.setEncodingStyle(null);
            call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
            call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "MaterialData"));
            //MaterialInfo[] materialInfos = (MaterialInfo[]) call.invoke(new Object[]{"004"});
            //System.out.println(materialInfos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test8() {
        Pattern loginPattern = Pattern.compile("^\\d{8}(#\\d{8})*$");
        String requestid = "12345678#123456780";
        requestid = requestid.replaceAll("\\s*", "");
        System.out.println(requestid);
        Matcher matcher = loginPattern.matcher(requestid);
        System.out.println(matcher.matches());
    }

    @Test
    public void test9() {
        try {
            ProcessBuilder proc = new ProcessBuilder("C:\\办公软件\\浏览器\\360\\360se6\\Application\\360se.exe", "http://www.baidu.com");
            proc.start();
        } catch (Exception e) {
            System.out.println("Error executing progarm.");
        }

    }


    @Test
    public void test10() {
        try {
            String requestXml = "<task><org><oper>add</oper><name>办文处子3</name><id>34000004@gd.zg </id><pid>34000001@gd.zg</pid><order>10002</order><standardcode>100101</standardcode><standardname>办文处</standardname></org></task>";
            String endpoint = "http://localhost:8080/services/WHSynchronization";
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
            call.setOperationName(new javax.xml.namespace.QName("http://datasyn.wellhope.com/", "CA_UpdateForSame"));
            String result = (String) call.invoke(new Object[]{requestXml, ""});
            System.out.println("返回结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * xml格式化
     *
     * @throws Exception
     */
    @Test
    public void test11() throws Exception {
        String xml = "<RequestList><RequestInfo><RequestId>1</RequestId><RequestName>XXX申请1</RequestName><RequestUrl>http://xxxx:8080/xxx/ViewRequest.do?requestid=1</RequestUrl></RequestInfo><RequestInfo><RequestId>2</RequestId><RequestName>XXX申请2</RequestName><RequestUrl>http://xxxx:8080/xxx/ViewRequest.do?requestid=2</RequestUrl></RequestInfo></RequestList>";

        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(xml));
        String requestXML = null;
        XMLWriter writer = null;
        if (document != null) {
            try {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = new OutputFormat(" ", true);
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
        System.out.println(requestXML);

    }

    @Test
    public void test12() {
        try {
            String requestXml = "";
            String endpoint = "http://localhost:8080/services/HrmService";
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
            call.setOperationName(new javax.xml.namespace.QName("", "getUnhandledNumByOperId "));
            String requestid = (String) call.invoke(new Object[]{"1"});
            System.out.println("创建流程的requestid:" + requestid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test13() {
        String url = "http://192.168.10.178:21161/api/CatalogInfoSearch/getAllResourceInfoByCatalogId";
        //String s = HTTPUtil.doGet(url);
        String json = "{\"obj\":[{\"id\":\"40288adb5fe1a53f015fe2e9db7c0017\",\"restitle\":\"法律法规信息\",\"status\":\"4\",\"sourcecodemiddle\":\"444444003176\",\"source_code_prev\":\"307021\",\"source_code_last\":\"000009\",\"cat_id\":\"3>07>021>444444003>176\",\"metadata_id\":\"40288acb5e996c1d015e9e27dc600005\",\"create_user\":\"root\",\"create_date\":1511340498000,\"dept_ids\":\"\",\"do_user_code\":\"\",\"do_cause\":\"\",\"rootcode\":\"444444003\",\"dept_names\":\"\",\"kvlist\":\"[]\",\"tabname\":\"g_信息资源目录模板_recs\",\"form_html\":\"\",\"mustfields\":\"\",\"catlogid\":\"40288adb5fa04e15015fbf3b270d0000\",\"path\":\"部门信息资源目录>省（自治区、直辖市）和计划单列市>湖南省>部门信息资源目录>湖南省安全生产监督管理局\",\"resourceapply\":\"湖南省\",\"resourceapplyinner\":\"湖南安监局\",\"resourceapplycode\":\"11430000743187451F\",\"resourcetype\":\"电子文件\",\"resourcefiletype\":\"其他\",\"otherresourcedescribe\":\"\",\"net\":\"2政务外网\",\"resourceposition\":\"无\",\"resourcesummary\":\"法律法规颁布部门\",\"relationname\":\"\",\"relationsystem\":\"无\",\"totaldatastore\":\"0\",\"sharedatastore\":\"0\",\"opendatastore\":\"0\",\"totalstructrecords\":\"0\",\"sharestructrecords\":\"0\",\"openstructrecords\":\"0\",\"allcode\":\"3070211760000002004006/000033\",\"catalog_code\":\"3070211760000002004006/000033\",\"updatecycle\":\"实时\",\"publishdate\":\"2017年11月15日\",\"subjecttype\":\"006\"},{\"id\":\"40288adb5fe1a53f015fe2e9db7c0017\",\"restitle\":\"法律法规信息\",\"status\":\"4\",\"sourcecodemiddle\":\"444444003176\",\"source_code_prev\":\"307021\",\"source_code_last\":\"000009\",\"cat_id\":\"3>07>021>444444003>176\",\"metadata_id\":\"40288acb5e996c1d015e9e27dc600005\",\"create_user\":\"root\",\"create_date\":1511340498000,\"dept_ids\":\"\",\"do_user_code\":\"\",\"do_cause\":\"\",\"rootcode\":\"444444003\",\"dept_names\":\"\",\"kvlist\":\"[]\",\"tabname\":\"g_信息资源目录模板_recs\",\"form_html\":\"\",\"mustfields\":\"\",\"catlogid\":\"40288adb5fa04e15015fbf3b270d0000\",\"path\":\"部门信息资源目录>省（自治区、直辖市）和计划单列市>湖南省>部门信息资源目录>湖南省安全生产监督管理局\",\"resourceapply\":\"湖南省\",\"resourceapplyinner\":\"湖南安监局\",\"resourceapplycode\":\"11430000743187451F\",\"resourcetype\":\"电子文件\",\"resourcefiletype\":\"其他\",\"otherresourcedescribe\":\"\",\"net\":\"2政务外网\",\"resourceposition\":\"无\",\"resourcesummary\":\"法律法规颁布部门\",\"relationname\":\"\",\"relationsystem\":\"无\",\"totaldatastore\":\"0\",\"sharedatastore\":\"0\",\"opendatastore\":\"0\",\"totalstructrecords\":\"0\",\"sharestructrecords\":\"0\",\"openstructrecords\":\"0\",\"allcode\":\"3070211760000002004006/000033\",\"catalog_code\":\"3070211760000002004006/000033\",\"updatecycle\":\"实时\",\"publishdate\":\"2017年11月10日\",\"subjecttype\":\"006\"}],\"ok\":true}";
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        boolean ok = jsonObject.get("ok").getAsBoolean();
        if (ok) {
            JsonArray jsonArray = jsonObject.get("obj").getAsJsonArray();
            JsonObject asJsonObject;
            for (JsonElement element : jsonArray) {
                asJsonObject = element.getAsJsonObject();
                asJsonObject.get("id").getAsString();
                asJsonObject.get("restitle").getAsString();//信息资源名称
                asJsonObject.get("create_user").getAsString();//创建人
                asJsonObject.get("resourceapply").getAsString();//信息资源提供方
                asJsonObject.get("resourceapplyinner").getAsString();//提供方内部部门
                asJsonObject.get("resourceapplycode").getAsString();//资源提供方代码
                asJsonObject.get("resourcetype").getAsString();//信息资源格式分类
                asJsonObject.get("resourcefiletype").getAsString();//信息资源格式类型
                asJsonObject.get("otherresourcedescribe").getAsString();//其他类型资源格式描述
                asJsonObject.get("net").getAsString();//信息资源所在网络
                asJsonObject.get("resourceposition").getAsString();//信息资源所在位置
                asJsonObject.get("resourcesummary").getAsString();//信息资源摘要
                asJsonObject.get("relationname").getAsString();//关联名称
                asJsonObject.get("catalog_code").getAsString();//信息资源编码
                asJsonObject.get("totalDataStore").getAsString();//数据存储总量
                asJsonObject.get("shareDataStore").getAsString();//已共享的数据存储量
                asJsonObject.get("openDataStore").getAsString();//已开放的数据存储量
                asJsonObject.get("totalStructRecords").getAsString();//结构化信息记录总数
                asJsonObject.get("shareStructRecords").getAsString();//已共享结构化记录数
                asJsonObject.get("openStructRecords").getAsString();//已开放结构化记录数
            }
        }
    }

    @Test
    public void test14() {
        String returnStr = "{\"obj\":[{\"id\":\"40288adb5fe1a53f015fe2e9db7c0017\",\"restitle\":\"法律法规信息\",\"status\":\"4\",\"sourcecodemiddle\":\"444444003176\",\"source_code_prev\":\"307021\",\"source_code_last\":\"000009\",\"cat_id\":\"3>07>021>444444003>176\",\"metadata_id\":\"40288acb5e996c1d015e9e27dc600005\",\"create_user\":\"root\",\"create_date\":1511340498000,\"dept_ids\":\"\",\"do_user_code\":\"\",\"do_cause\":\"\",\"rootcode\":\"444444003\",\"dept_names\":\"\",\"kvlist\":\"[]\",\"tabname\":\"g_信息资源目录模板_recs\",\"form_html\":\"\",\"mustfields\":\"\",\"catlogid\":\"40288adb5fa04e15015fbf3b270d0000\",\"path\":\"部门信息资源目录>省（自治区、直辖市）和计划单列市>湖南省>部门信息资源目录>湖南省安全生产监督管理局\",\"resourceapply\":\"湖南省\",\"resourceapplyinner\":\"湖南安监局\",\"resourceapplycode\":\"11430000743187451F\",\"resourcetype\":\"电子文件\",\"resourcefiletype\":\"其他\",\"otherresourcedescribe\":\"\",\"net\":\"2政务外网\",\"resourceposition\":\"无\",\"resourcesummary\":\"法律法规颁布部门\",\"relationname\":\"\",\"relationsystem\":\"无\",\"totaldatastore\":\"0\",\"sharedatastore\":\"0\",\"opendatastore\":\"0\",\"totalstructrecords\":\"0\",\"sharestructrecords\":\"0\",\"openstructrecords\":\"0\",\"allcode\":\"3070211760000002004006/000033\",\"catalog_code\":\"3070211760000002004006/000033\",\"updatecycle\":\"实时\",\"publishdate\":\"2017年11月15日\",\"subjecttype\":\"006\"},{\"id\":\"40288adb5fe1a53f015fe2e9db7c0017\",\"restitle\":\"法律法规信息\",\"status\":\"4\",\"sourcecodemiddle\":\"444444003176\",\"source_code_prev\":\"307021\",\"source_code_last\":\"000009\",\"cat_id\":\"3>07>021>444444003>176\",\"metadata_id\":\"40288acb5e996c1d015e9e27dc600005\",\"create_user\":\"root\",\"create_date\":1511340498000,\"dept_ids\":\"\",\"do_user_code\":\"\",\"do_cause\":\"\",\"rootcode\":\"444444003\",\"dept_names\":\"\",\"kvlist\":\"[]\",\"tabname\":\"g_信息资源目录模板_recs\",\"form_html\":\"\",\"mustfields\":\"\",\"catlogid\":\"40288adb5fa04e15015fbf3b270d0000\",\"path\":\"部门信息资源目录>省（自治区、直辖市）和计划单列市>湖南省>部门信息资源目录>湖南省安全生产监督管理局\",\"resourceapply\":\"湖南省\",\"resourceapplyinner\":\"湖南安监局\",\"resourceapplycode\":\"11430000743187451F\",\"resourcetype\":\"电子文件\",\"resourcefiletype\":\"其他\",\"otherresourcedescribe\":\"\",\"net\":\"2政务外网\",\"resourceposition\":\"无\",\"resourcesummary\":\"法律法规颁布部门\",\"relationname\":\"\",\"relationsystem\":\"无\",\"totaldatastore\":\"0\",\"sharedatastore\":\"0\",\"opendatastore\":\"0\",\"totalstructrecords\":\"0\",\"sharestructrecords\":\"0\",\"openstructrecords\":\"0\",\"allcode\":\"3070211760000002004006/000033\",\"catalog_code\":\"3070211760000002004006/000033\",\"updatecycle\":\"实时\",\"publishdate\":\"2017年11月10日\",\"subjecttype\":\"006\"}],\"ok\":true}";
        JsonObject jsonObject = new JsonParser().parse(returnStr).getAsJsonObject();
        if (jsonObject.get("ok").getAsBoolean()) {
            JsonArray jsonArray = jsonObject.get("obj").getAsJsonArray();
            List<CatalogAll> interFirstList = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<CatalogAll>>() {
            }.getType());
            for (CatalogAll i : interFirstList) {
                System.out.println(i.toString());
            }
        }

    }

    @Test
    public void test15() {
        String startTime = "1960-01-01";
        String endTime = "9999-12-31";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            Date nowTime = new Date();

            System.out.println(format.format(start));
//            if (!(nowTime.after(start) && nowTime.before(end))) {
//                System.out.println("不符");
//            }else {
//                System.out.println("相符");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test16() {
        String wh = "机电工程处纪〔2018〕X号";
        String bmdz = "资产";
        int i = wh.lastIndexOf("〔");


        String[] zth = {wh.substring(0, i), wh.substring(i)};
        wh = zth[0] + bmdz + zth[1];
        System.out.println(wh);
    }

    @Test
    public void getCalendar() {
        String dateStr = "2018-01-01";
        Date date;
        // 声明格式化日期的对象
        SimpleDateFormat format = format = new SimpleDateFormat("yyyy-MM-dd");
        // 创建一个Calendar类型的对象
        Calendar calendar = Calendar.getInstance();
        try {
            //解析日期字符串，生成Date对象
            date = format.parse(dateStr);
            System.out.println(date);
            // 使用Date对象设置此Calendar对象的时间
            calendar.setTime(date);
            System.out.println(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * lambda表达式跑线程
     */
    @Test
    public void test19() {
//        Thread thread = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println("线程1： " + i);
//            }
//        });
//        Thread thread1 = new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                System.out.println("线程2： " + i);
//            }
//        });
//        thread.start();
//        thread1.start();
    }

    @Test
    public void test20() {
        String json = "{\"Table\":{\"bcallback\":\"http:\\/\\/localhost:8080\\/mdm\\/distribute\\/callback\",\"bdata\":\"{\\\"active\\\":true,\\\"activeDesc\\\":\\\"同时分发POS、SAP\\\",\\\"activeTime\\\":0,\\\"data\\\":[{\\\"mdm_data_operation_status\\\":0,\\\"update_time\\\":20181010171713,\\\"CODEITEMDESC\\\":\\\"高济医药\\\",\\\"SFMD\\\":2,\\\"CODEITEMID\\\":\\\"01\\\",\\\"SFXZJG\\\":1,\\\"GRADE\\\":1,\\\"CODESETID\\\":\\\"UN\\\",\\\"GUIDKEY\\\":\\\"1\\\",\\\"status\\\":1,\\\"PARENTID\\\":\\\"1\\\"},{\\\"mdm_data_operation_status\\\":0,\\\"update_time\\\":20181010171713,\\\"CODEITEMDESC\\\":\\\"好医生\\\",\\\"SFMD\\\":2,\\\"CODEITEMID\\\":\\\"02\\\",\\\"SFXZJG\\\":1,\\\"GRADE\\\":2,\\\"CODESETID\\\":\\\"UM\\\",\\\"GUIDKEY\\\":\\\"2\\\",\\\"status\\\":1,\\\"PARENTID\\\":\\\"1\\\"},{\\\"mdm_data_operation_status\\\":0,\\\"update_time\\\":20181010171714,\\\"CODEITEMDESC\\\":\\\"华为\\\",\\\"SFMD\\\":2,\\\"CODEITEMID\\\":\\\"03\\\",\\\"SFXZJG\\\":1,\\\"GRADE\\\":2,\\\"CODESETID\\\":\\\"UM\\\",\\\"GUIDKEY\\\":\\\"2\\\",\\\"status\\\":1,\\\"PARENTID\\\":\\\"1\\\"},{\\\"mdm_data_operation_status\\\":0,\\\"update_time\\\":20181010171714,\\\"CODEITEMDESC\\\":\\\"瑞康\\\",\\\"SFMD\\\":2,\\\"CODEITEMID\\\":\\\"04\\\",\\\"SFXZJG\\\":1,\\\"GRADE\\\":2,\\\"CODESETID\\\":\\\"UM\\\",\\\"GUIDKEY\\\":\\\"2\\\",\\\"status\\\":1,\\\"PARENTID\\\":\\\"1\\\"},{\\\"mdm_data_operation_status\\\":0,\\\"update_time\\\":20181010171716,\\\"CODEITEMDESC\\\":\\\"百姓缘\\\",\\\"SFMD\\\":2,\\\"CODEITEMID\\\":\\\"05\\\",\\\"SFXZJG\\\":1,\\\"GRADE\\\":2,\\\"CODESETID\\\":\\\"UM\\\",\\\"GUIDKEY\\\":\\\"2\\\",\\\"status\\\":1,\\\"PARENTID\\\":\\\"1\\\"}],\\\"dataVersion\\\":\\\"default\\\",\\\"totalNum\\\":5}\",\"bdatahash\":\"304bc6cad67d1f298e12abc29d4c5d53\",\"bdatetime\":\"2018-10-1110:18:46\",\"bdestination\":901,\"bguid\":\"MDM001760409fb-fb83-4898-a2d4-daf01b616e27\",\"bkeys\":\"[]\",\"bsource\":401,\"bstatus\":1,\"btype\":1071,\"bversion\":\"V1\"}}";
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonObject table = jsonObject.get("Table").getAsJsonObject();
        String btype = table.get("btype").getAsString().trim();//数据类型
        String bdataString = table.get("bdata").getAsString().trim();//内容

        JsonObject bdata = new JsonParser().parse(bdataString).getAsJsonObject();
        String data1 = bdata.get("data").toString();

        List<OrganizationVo> HrmSubCompanyV1 = gson.fromJson(data1, new TypeToken<List<OrganizationVo>>() {
        }.getType());
        //HrmSubCompanyV1.forEach(a -> System.out.println(a.toString()));

    }

    /**
     * 正则测试
     *
     * @throws Exception
     */
    @Test
    public void test21() throws Exception {
        String check = "12345678#12345678#12345678#12345678";
        Pattern loginPattern = Pattern.compile("^\\d{8}(#\\d{8})*$");
        Matcher matcher = loginPattern.matcher(check);
        System.out.println(matcher.matches());
    }

    private String ObjectToXml(Object obj) {
        HrmServiceXmlUtil xmlUtil = HrmServiceXmlUtil.getInstance();
        return xmlUtil.objToXml(obj);
    }

    @Test
    public void test22() {
        String key = "{\"Table\":[{\"bguid\":\"OA90165DB44A66010429681D72086E0D8A64A\",\"btype\":\"9055\",\"bsource\":\"901\",\"bdestination\":\"101\",\"bdatetime\":\"2018-09-25 16:39:59\",\"bstatus\":\"1\",\"bcallback\":\"http://124.251.118.4/workflow/request/gaoji/CallBack.jsp\",\"bversion\":\"1.0\",\"bdatahash\":\"\",\"bkeys\":\"OA90165DB44A66010429681D72086E0D8A64A\",\"bdata\":\"{\\\"BUKRS\\\":\\\"测试主体\\\",\\\"PERNR\\\":\\\"\\\",\\\"NAME1\\\":\\\"赵国栋\\\",\\\"HBKID\\\":\\\"招商银行\\\",\\\"BANKN\\\":\\\"6214850118228342\\\",\\\"ZTYPE\\\":\\\"报销\\\",\\\"KOSTL\\\":\\\"115\\\",\\\"WAERS\\\":\\\"CNY\\\",\\\"ZJK\\\":\\\"借款余额\\\",\\\"ZBX\\\":\\\"369\\\",\\\"WRBTR\\\":\\\"支付金额\\\",\\\"ZDJBH\\\":\\\"CLBX-2018-09-1119\\\",\\\"ZDJZT\\\":\\\"\\\",\\\"AUFNR\\\":\\\"内部订单\\\",\\\"ZFKFS\\\":\\\"现金\\\",\\\"detailVoList\\\":[{\\\"ZFYLX\\\":\\\"66021301\\\"},{\\\"ZFYLX\\\":\\\"66021302\\\",\\\"ZSE\\\":\\\"123\\\",\\\"SGTXT\\\":\\\"123123\\\"},{\\\"ZFYLX\\\":\\\"66021303\\\"}]}\"}]}";
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
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
            System.out.println(new String(str));
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    /**
     * guava样例
     *
     * @throws Exception
     */
    @Test
    public void test23() throws Exception {
//        ArrayList<String> strings = Lists.newArrayList("1", "2");
//        strings.forEach(System.out::println);

//        List<String> lines = Files.readLines(new File("D:\\1.txt"), Charsets.UTF_8);
//        lines.forEach(System.out::println);

//        Multimap<String, Integer> multimap = ArrayListMultimap.create();
//        multimap.put("a", 1);
//        multimap.put("a", 2);
//        multimap.put("a", 4);
//        multimap.put("b", 3);
//        multimap.put("c", 5);
//        System.out.println(multimap.get("a"));

        List<String> list = new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        String result = Joiner.on("-").join(list);
        System.out.println(result);


    }

    /**
     * demo自带AES加密工具
     */
    @Test
    public void test25() {
        String a = AES.encrypt("http://192.168.1.214:8001/testDoc/123.docx|http://192.168.1.214:8001/testDoc/1234.txt", "123");
        String b = AES.decrypt("64B4267DA7065D9EB847BFB4E67E63AA1684537EBA410FF1DFF13BAFAE1E35F8756395B3F8FDC92E6558F192B0DBC41DB6DADC8C96B8CD0CBE92B72E8024E9B8BE48161CCC4F270B55FD10158295CEEB4061C1A7352D6F5B1349DEE0F2AAD21F", "123");
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void test26() {
        String str = "<br>系统管理员 2018-09-10";
        String rgex = ">(.*?)<";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(str);
        if (m.find()) {
            String group = m.group(1);
            System.out.println(group);
        }
    }

    @Test
    public void test27() {
        String str = "<p style=\"font-family: &quot;微软雅黑&quot;,&quot;Microsoft YaHei&quot;; font-size: 12px;\"><span style=\"font-family: 微软雅黑; font-size: 12px;\">ok</span></p>";
        String rgex = "<.*?>";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(str);
        String trim = m.replaceAll("").trim();
        System.out.println(trim);
    }

    @Test
    public void test28() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Gson gson1 = new Gson();
        Student student = new Student();
        student.setAge("24");
        student.setName("牛智萌");
        student.setIdentityCardNumber("123");
        System.out.println(gson1.toJson(student));
    }

    @Test
    public void test30() throws Exception {
        File file = new File("D:\\WEAVER\\ecology\\filesystem\\201811\\Q\\1e2cfe79-cd59-40cf-a039-0337a797182c.zip");
        unZip(file, "D:\\");
//        File f = new File("D:\\WEAVER\\ecology\\filesystem\\201811\\Q\\1e2cfe79-cd59-40cf-a039-0337a797182c");
//        File f1 = new File("D:\\WEAVER\\ecology\\filesystem\\201811\\Q\\1e2cfe79-cd59-40cf-a039-0337a797182c.jpeg");
//        f.renameTo(f1);
    }

    /**
     * zip解压
     *
     * @param srcFile     zip源文件
     * @param destDirPath 解压后的目标文件夹
     * @throws RuntimeException 解压失败会抛出运行时异常
     */

    public void unZip(File srcFile, String destDirPath) throws RuntimeException {
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName() + ".jpg";
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName() + ".jpg");
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[4096];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test31() throws Exception {
//        ConnStatementDataSource conn = new ConnStatementDataSource("");
//        conn.setStatementSql("");
//        conn.setString(1,"");
//        conn.executeUpdate();
//        conn.close();
    }

    @Test
    public void test32() {
        try {
            InputStream inputStream = new FileInputStream("d:/12.png");
            byte[] bytes = new byte[inputStream.available()];
            int read = inputStream.read(bytes);
            inputStream.close();
            String decode = new BASE64Encoder().encode(bytes);
            System.out.println(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test33() throws Exception {
        boolean equal = Objects.equal(null, null);
        System.out.println(equal);

        List<String> list = Lists.newArrayList("alpha", "beta", "gamma");
        list.add(null);
        String join = Joiner.on(" | ").skipNulls().join(list);//跳过null
        String join1 = Joiner.on(" | ").useForNull("nzm").join(list);//用指定字符替换null

        StringBuilder builder = new StringBuilder();
        StringBuilder stringBuilder = Joiner.on(" | ").skipNulls().appendTo(builder, list);
        System.out.println(stringBuilder.toString());

    }

    @Test
    public void test34() {
        //excel base64传输
        try {
            InputStream inputStream = new FileInputStream("d:/11月开发任务.xls");
            byte[] bytes = new byte[inputStream.available()];
            int read = inputStream.read(bytes);
            inputStream.close();
            String decode = new BASE64Encoder().encode(bytes);
            System.out.println(decode);
            byte[] bytes1 = new BASE64Decoder().decodeBuffer(decode);
            OutputStream outputStream = new FileOutputStream("d:\\复制的.xls");
            outputStream.write(bytes1);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test36() throws Exception {
        double zshj = 1167.0;
        double zb = 30;
        double test = zshj * (zb * 0.01);

        DecimalFormat df = new DecimalFormat("##.00");
        System.out.println(Double.valueOf(df.format(test)));

        double v = new BigDecimal(test).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        String s = new BigDecimal(test).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

        System.out.println(v);
        System.out.println(s);

        double a = 1.00;
        System.out.println(a);

    }

    @Test
    public void test37() {
        DecimalFormat df = new DecimalFormat(",###.00");
        double hh = 4324321.0123;
        System.out.println(df.format(hh));
        TimeUtil.getCurrentTimeString();

    }

    @Test
    public void test38() {

    }

}
