package com.test;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lowagie.text.pdf.*;
import com.test.webserviceTest.vo.Student;
import com.weaver.general.TimeUtil;
import com.weavernorth.saiwen.util.SwUtil;
import com.weavernorth.zgsy.webUtil.util.BaseDataUtil;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.joda.time.DateTime;
import org.junit.Test;
import weaver.conn.RecordSet;
import weaver.general.MD5;
import weaver.integration.util.HTTPUtil;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void test10() {
        String str = "D:\\WEAVER\\ecology\\filesystem\\201907\\N\\1564280579323";
        fileCopy(str, str + ".html");

    }

    private void fileCopy(String srcPath, String outPath) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(srcPath);
            outputStream = new FileOutputStream(outPath);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) > -1) {
                outputStream.write(bytes, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) throws Exception {
        // 要输出的pdf文件
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\29529\\Desktop\\123Back.pdf")));
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // 将pdf文件先加水印然后输出
        setWatermark(bos, "C:\\Users\\29529\\Desktop\\123.pdf", format.format(cal.getTime())
                + "  下载使用人：" + "测试user");
    }

    public static void setWatermark(BufferedOutputStream bos, String input, String waterMarkName) throws Exception {

        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, bos);
        int total = reader.getNumberOfPages() + 1;
        PdfContentByte content;
        BaseFont base = BaseFont.createFont("C:\\Windows\\Fonts\\STCAIYUN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        PdfGState gs = new PdfGState();
        for (int i = 1; i < total; i++) {
            content = stamper.getOverContent(i);// 在内容上方加水印
            //content = stamper.getUnderContent(i);//在内容下方加水印
            gs.setFillOpacity(0.2f);
            // content.setGState(gs);
            content.beginText();
            content.setColorFill(Color.LIGHT_GRAY);
            content.setFontAndSize(base, 80);
            content.setTextMatrix(70, 200);
            // x y 倾斜角度
            content.showTextAligned(1, "牛智萌加的水印", 260, 350, 30);
            content.showTextAligned(1, "2019-07-30", 260, 280, 30);
            content.setColorFill(Color.BLACK);
            content.setFontAndSize(base, 8);
            content.showTextAligned(1, "下载时间：" + waterMarkName + "", 300, 10, 0);
            content.endText();

        }
        stamper.close();
        reader.close();
    }

    @Test
    public void test12() throws Exception {
        String returnXml = "<?xml version=\"1.0\" encoding=\"utf‐8\"?>\n" +
                "<Cbo_Account_QueryResult_List>   \n" +
                "    <AccountQueryResultList>   \n" +
                "     <Cbo_Account_QueryResult_Model>   \n" +
                "       <Sob>1001009140200035</Sob>    \n" +
                "       <OrgName>赛闻天津</OrgName>   \n" +
                "       <OrgCode>101</OrgCode>   \n" +
                "       <Code>66020507|0|0|015|0|0|0|0|0</Code>  \n" +
                "       <Name>慰问费|0|0|工会|0|0|0|0|0</Name>   \n" +
                "       <AccountProperty_AccountBasic_code>06</AccountProperty_AccountBasic_code> \n" +
                "       <AccountProperty_AccountBasic_name>损益</AccountProperty_AccountBasic_name>\n" +
                "       <AccountProperty_Name>管理费用</AccountProperty_Name>\n" +
                "       <AccountProperty_Code>120103</AccountProperty_Code>\n" +
                "       <Id>1001102019130145</Id>\n" +
                "     </Cbo_Account_QueryResult_Model>   \n" +
                "    </AccountQueryResultList>   \n" +
                "</Cbo_Account_QueryResult_List>";
        Document doc = DocumentHelper.parseText(returnXml);
        Element rootElt = doc.getRootElement();
        List resultList = rootElt.element("AccountQueryResultList").elements();
        System.out.println(resultList.size());
        Element myElement;
        for (Object tableObj : resultList) {
            myElement = (Element) tableObj;
            String orgName = myElement.elementTextTrim("Sob");
            String orgCode = myElement.elementTextTrim("OrgName");
            String supplierName = myElement.elementTextTrim("OrgCode");
            String supplierCode = myElement.elementTextTrim("Code");
            String bankCode = myElement.elementTextTrim("Name");

            String bankName = myElement.elementTextTrim("AccountProperty_AccountBasic_code");
            String supplierBankAccountCode = myElement.elementTextTrim("AccountProperty_AccountBasic_name");
            String orgID = myElement.elementTextTrim("AccountProperty_Name");
            String supplierId = myElement.elementTextTrim("AccountProperty_Code");
            String u9Id = myElement.elementTextTrim("Id");

            System.out.println(orgName);
            System.out.println(orgCode);
            System.out.println(supplierName);
            System.out.println(supplierCode);
            System.out.println(bankCode);

            System.out.println(bankName);
            System.out.println(supplierBankAccountCode);
            System.out.println(orgID);
            System.out.println(supplierId);
            System.out.println(u9Id);
            System.out.println("=========");
        }

    }

    public static void WordToPDF(String startFile, String overFile) throws IOException {
        // 源文件目录
        File inputFile = new File(startFile);
        if (!inputFile.exists()) {
            System.out.println("源文件不存在！");
            return;
        }

        // 输出文件目录
        File outputFile = new File(overFile);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().exists();
        }

        // 调用openoffice服务线程
        /** 我把openOffice下载到了 C:/Program Files (x86)/下  ,下面的写法自己修改编辑就可以**/
        String command = "D:/openOffice/program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
        Process p = Runtime.getRuntime().exec(command);

        // 连接openoffice服务
        OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);

        connection.connect();

        // 转换
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);

        // 关闭连接
        connection.disconnect();

        // 关闭进程
        p.destroy();
    }

    @Test
    public void test13() {
        String start = "C:\\Users\\29529\\Desktop\\123.docx";
        String over = "C:\\Users\\29529\\Desktop\\成了.pdf";
        try {
            WordToPDF(start, over);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test14() {


        Element root = DocumentHelper.createElement("RequestSupplierList");
        Element SupplierList = root.addElement("SupplierList");
        Element CreateSupplierModel = SupplierList.addElement("CreateSupplierModel");

        // 拼接子级
        Element m_descFlexField = CreateSupplierModel.addElement("M_descFlexField");
        m_descFlexField.addElement("M_privateDescSeg4").setText(""); // 供应商等级
        m_descFlexField.addElement("M_privateDescSeg3").setText(""); // 建筑面积
        m_descFlexField.addElement("M_privateDescSeg2").setText(""); // 企业性质
        m_descFlexField.addElement("M_pubDescSeg8").setText(""); // 主要产品

        CreateSupplierModel.addElement("M_Turnover").setText("");
        CreateSupplierModel.addElement("M_RegisterCapital").setText("");
        CreateSupplierModel.addElement("M_tradeCategory").setText("");
        CreateSupplierModel.addElement("M_RegisterLocation").setText("");
        CreateSupplierModel.addElement("M_EmployeeCount").setText("");

        CreateSupplierModel.addElement("M_EstablishDate").setText("");
        CreateSupplierModel.addElement("M_effective").setText("");
        CreateSupplierModel.addElement("M_tradeCurrency").setText("");
        CreateSupplierModel.addElement("M_shortName").setText("");
        CreateSupplierModel.addElement("Name").setText("");

        CreateSupplierModel.addElement("M_code").setText("");
        CreateSupplierModel.addElement("M_officialLocation").setText("");
        CreateSupplierModel.addElement("M_org").setText("");
        CreateSupplierModel.addElement("M_corpUnifyCode").setText("");
        CreateSupplierModel.addElement("M_isPriceListModify").setText("true");

        CreateSupplierModel.addElement("M_isAPConfirmTermEditable").setText("true");
        CreateSupplierModel.addElement("M_isPaymentTermModify").setText("true");
        CreateSupplierModel.addElement("M_isReceiptRuleEditable").setText("true");

        Document document = DocumentHelper.createDocument(root);
        String requestXml = document.asXML();

        System.out.println(requestXml);
    }

    @Test
    public void test15() {
        Map<String, String> map = new HashMap<String, String>();
        String xml = "<?xml{哈哈哈} {} versi{ }on=\"1.0\" enc{a _1}odi{endtime}ng=\"UTF-8\" standalone=\"yes\"?><w:document xmlns:wpc=\"http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas\" xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:wp14=\"http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing\" xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" xmlns:w14=\"http://schemas.microsoft.com/office/word/2010/wordml\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" xmlns:w15=\"http://schemas.microsoft.com/office/word/2012/wordml\" xmlns:wpg=\"http://schemas.microsoft.com/office/word/2010/wordprocessingGroup\" xmlns:wpi=\"http://schemas.microsoft.com/office/word/2010/wordprocessingInk\" xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\" xmlns:wps=\"http://schemas.microsoft.com/office/word/2010/wordprocessingShape\" xmlns:wpsCustomData=\"http://www.wps.cn/officeDocument/2013/wpsCustomData\" mc:Ignorable=\"w14 w15 wp14\"><w:body><w:p><w:pPr><w:rPr><w:rFonts w:hint=\"eastAsia\" w:eastAsiaTheme=\"minorEastAsia\"/><w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:hint=\"eastAsia\"/><w:lang w:val=\"en-US\" w:eastAsia=\"zh-CN\"/></w:rPr><w:t>牛智萌：{name}</w:t><w:t>牛智萌：{sex}</w:t><w:t>牛智萌：{age_12}</w:t></w:r><w:bookmarkStart w:id=\"0\" w:name=\"_GoBack\"/><w:bookmarkEnd w:id=\"0\"/></w:p><w:sectPr><w:pgSz w:w=\"11906\" w:h=\"16838\"/><w:pgMar w:top=\"1440\" w:right=\"1800\" w:bottom=\"1440\" w:left=\"1800\" w:header=\"851\" w:footer=\"992\" w:gutter=\"0\"/><w:cols w:space=\"425\" w:num=\"1\"/><w:docGrid w:type=\"lines\" w:linePitch=\"312\" w:charSpace=\"0\"/></w:sectPr></w:body></w:document>";
        // 匹配{数字、字母、下划线}
        Pattern pattern = Pattern.compile("\\{\\w+}");
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            String group = matcher.group().replaceAll("\\s*", "");
            String substring = group.substring(1, group.length() - 1);
            map.put(group, substring);
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


    }

    @Test
    public void test16() throws Exception {
        String result = String.format("%03d", 12345);
        System.out.println(result);

        int num = 76543468;
        String str = String.format("%,d", num);
        System.out.println(str);

//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd E a hh:mm:ss");
//        System.out.println(now.format(formatter));

    }

    @Test
    public void test17() {
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < 5; i++) {
            Student student = new Student();
            student.setAge("25");
            student.setIdentityCardNumber("230106xxxx");
            student.setName("nzm:" + i);
            studentList.add(student);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "test");
        jsonObject.put("studentlIST", studentList);
        System.out.println(jsonObject.toJSONString());
    }

    @Test
    public void test18() {
        String josnStr = "{\"studentlIST\":[{\"age\":\"25\",\"identityCardNumber\":\"230106xxxx\",\"name\":\"nzm:0\"},{\"age\":\"25\",\"identityCardNumber\":\"230106xxxx\",\"name\":\"nzm:1\"},{\"age\":\"25\",\"identityCardNumber\":\"230106xxxx\",\"name\":\"nzm:2\"},{\"age\":\"25\",\"identityCardNumber\":\"230106xxxx\",\"name\":\"nzm:3\"},{\"age\":\"25\",\"identityCardNumber\":\"230106xxxx\",\"name\":\"nzm:4\"}],\"name\":null}";

        JSONObject jsonObject = JSONObject.parseObject(josnStr);
        String name = jsonObject.getString("name");
        //System.out.println(name);
        String studentlIST = jsonObject.getString("studentlIST");
        //System.out.println(studentlIST);

        JSONArray studentArray = jsonObject.getJSONArray("studentlIST");
        System.out.println("数组长度：" + studentArray.size());

        List<Student> studentList = studentArray.toJavaList(Student.class);
//        for (Object object : studentArray) {
//            JSONObject obj = (JSONObject) object;
//            System.out.println(obj.getString("name"));
//            System.out.println(obj.getString("age"));
//            System.out.println(obj.getString("identityCardNumber"));
//            System.out.println("========");
//        }
        String s = JSONObject.toJSONString(studentList, true);
        System.out.println(s);
    }

    @Test
    public void test19() {
        JSONObject jsonObjectAll = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 5; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("1", 1);
            jsonObject.put("2", 2);
            jsonArray.add(jsonObject);
        }

        jsonObjectAll.put("count", 12);
        jsonObjectAll.put("flowInfo", jsonArray);
        System.out.println(jsonObjectAll.toJSONString());
    }

    @Test
    public void test20() {
        String loginId = "zhangrw";
        String flowStr = "OA_DONE_FLOW";
        long currentTime = System.currentTimeMillis() / 1000;
        MD5 md5 = new MD5();
        String md5ofStr = md5.getMD5ofStr(currentTime + flowStr + loginId);
        String url = "http://10.1.11.30/workflow/request/gaodeng/GetFlowDone.jsp?loginId="
                + loginId + "&token=" + md5ofStr + "&currentTime=" + currentTime + "&getCounts=5";
        System.out.println(url);
        String s = HTTPUtil.doGet(url);
        System.out.println(s);
    }

    @Test
    public void test21() {
        String loginId = "zhangrw";
        String flowStr = "OA_TODO_FLOW";
        long currentTime = System.currentTimeMillis() / 1000;
        MD5 md5 = new MD5();
        String md5ofStr = md5.getMD5ofStr(currentTime + flowStr + loginId);
        String url = "http://10.1.11.30/workflow/request/gaodeng/GetFlowToDo.jsp?loginId="
                + loginId + "&token=" + md5ofStr + "&currentTime=" + currentTime + "&getCounts=5";
        System.out.println(url);
        String s = HTTPUtil.doGet(url);
        System.out.println(s);
    }

    @Test
    public void test22() {
        String loginId = "zhangrw";
        String flowStr = "OA_PORTAL_FLOW";
        long currentTime = System.currentTimeMillis() / 1000;
        MD5 md5 = new MD5();
        String md5ofStr = md5.getMD5ofStr(currentTime + flowStr + loginId);
        String url = "http://10.1.11.27/workflow/request/gaodeng/GetFlowPortal.jsp?loginId="
                + loginId + "&token=" + md5ofStr + "&currentTime=" + currentTime + "&getCounts=10";
        System.out.println(url);
        String s = HTTPUtil.doGet(url);
        System.out.println(s);
    }

    @Test
    public void test24() {
        String[] splits = {""};
        List<String> muIdList = new ArrayList<String>();
        Collections.addAll(muIdList, splits);
        System.out.println(JSONObject.toJSONString(muIdList));
    }

    @Test
    public void test26() {
        // 获取文档
        String loginId = "zhangrw";
        String flowStr = "OA_DOC";
        String documentType = "002"; // 文档类型(最新文件 002, 社内要闻 003, 部门动态 004)
        long currentTime = System.currentTimeMillis() / 1000;
        MD5 md5 = new MD5();
        String md5ofStr = md5.getMD5ofStr(currentTime + flowStr + loginId);
        String url = "http://10.1.11.30/workflow/request/gaodeng/GetDoc.jsp?loginId="
                + loginId + "&token=" + md5ofStr + "&currentTime=" + currentTime + "&documentType=" + documentType + "&getCounts=10";
        System.out.println(url);
        String s = HTTPUtil.doGet(url);
        System.out.println(s);
    }

    @Test
    public void test27() {
        // 获取文档byId
        String loginId = "zhangrw";
        String flowStr = "OA_DOC_BYID";
        String seccategoryId = "778"; // OA中目录id（工程 778   现代化779），多个目录中间用英文逗号分隔，不得有空格
        long currentTime = System.currentTimeMillis() / 1000;
        MD5 md5 = new MD5();
        System.out.println(currentTime + flowStr + loginId);
        String md5ofStr = md5.getMD5ofStr(currentTime + flowStr + loginId);
        String url = "http://10.1.11.30/workflow/request/gaodeng/GetDocById.jsp?loginId="
                + loginId + "&token=" + md5ofStr + "&currentTime=" + currentTime + "&seccategoryId=" + seccategoryId + "&getCounts=10";
        System.out.println(url);
        String s = HTTPUtil.doGet(url);
        System.out.println(s);
    }

    @Test
    public void test28() throws UnsupportedEncodingException {
        String str = "{\"appSecId\":\"d4bf814c02abb801a2a2b6742a6d140a\",\"userId\":\"1111\",\"enterpriseId\":\"000001\"}";
        Base64 base64 = new Base64();
        String s = base64.encodeToString("测试22222222222".getBytes());

        System.out.println(s);

    }

    @Test
    public void test29() throws UnsupportedEncodingException {
        //String str = "http://sso.ceshi113.com/sign/redirect";
        String str = "http://124.205.140.201/sign/redirect";
        String code = "{\"timeStamp\":1571229945520,\"appId\":\"FSAID_1314644\",\"sign\":\"8D562971D0988E3725A979B5007B7036E79E27A586794555DDBACC3557281C75A608AE10624B41B4B6EA761D89A20485\",\"account\":\"13680227842\"}";
        String ea = "61126";
        System.out.println(sendPost(str, ea, code));
    }

    private String sendPost(String url, String ea, String code) {
        BufferedReader reader = null;
        StringBuilder response = new StringBuilder();
        try {
            URL httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            String content = "ea=" + URLEncoder.encode(ea, "UTF-8");
            content += "&code=" + URLEncoder.encode(code, "UTF-8");

            out.writeBytes(content);

            out.flush();
            out.close();

            conn.connect();
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
            e.printStackTrace();
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

    @Test
    public void test30() {
        String currentTimeString = TimeUtil.getCurrentTimeString().replace(":", "");
        BufferedWriter bufferedWriter = null;
        String str = null;
        try {
            File file = new File("d:" + File.separator + "orgLog" + File.separator + currentTimeString + ".txt");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < 100; i++) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test31() throws UnsupportedEncodingException {
        String encode = "{\"returnInfo\":{\"returnCode\":\"0000\",\"returnMessage\":\"处理成功\"},\"invoice\":[{\"invoiceTypeCode\":\"92\",\"goodsName\":\"\",\"invoiceDate\":\"20190505\",\"totalAmount\":\"255.50\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"e90fc4815edd4a83bbf5de6f7496aaa0\",\"invoiceCount\":\"1\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"\",\"invoiceNo\":\"\",\"invoiceAmount\":\"255.50\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"运城\",\"arriveCity\":\"天津\",\"trainNumber\":\"K866\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/e90fc4815edd4a83bbf5de6f7496aaa0.zip\",\"passenger\":\"谷宏毅\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":null},{\"invoiceTypeCode\":\"94\",\"goodsName\":\"\",\"invoiceDate\":\"20190126\",\"totalAmount\":\"20.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"50c1d3bebc394371a2c88473ef439c34\",\"invoiceCount\":\"1\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"141001920092\",\"invoiceNo\":\"13725022\",\"invoiceAmount\":\"0\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"洛阳站\",\"arriveCity\":\"汝阳\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/50c1d3bebc394371a2c88473ef439c34.zip\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":[{\"goodsName\":\"\",\"model\":\"\",\"unit\":\"\",\"invoiceNo\":\"\",\"invoiceCode\":\"\",\"num\":\"0.0\",\"unitPrice\":\"\",\"noTaxAmount\":\"\",\"taxRate\":\"\",\"taxAmount\":\"\",\"detailNo\":\"\",\"expenseItem\":\"\",\"plateNo\":\"\",\"type\":\"\",\"trafficDateStart\":\"\",\"trafficDateEnd\":\"\"}]},{\"invoiceTypeCode\":\"91\",\"goodsName\":\"\",\"invoiceDate\":\"20171023\",\"totalAmount\":\"62.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"e8cc9c14a9ac46fdb6f6e6e2c4b9262f\",\"invoiceCount\":\"\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"\",\"invoiceNo\":\"34940057\",\"invoiceAmount\":\"\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"\",\"arriveCity\":\"\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/e8cc9c14a9ac46fdb6f6e6e2c4b9262f.zip\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":[{\"goodsName\":\"\",\"model\":\"\",\"unit\":\"\",\"invoiceNo\":\"\",\"invoiceCode\":\"\",\"num\":\"0.0\",\"unitPrice\":\"\",\"noTaxAmount\":\"\",\"taxRate\":\"\",\"taxAmount\":\"\",\"detailNo\":\"\",\"expenseItem\":\"\",\"plateNo\":\"\",\"type\":\"\",\"trafficDateStart\":\"\",\"trafficDateEnd\":\"\"}]},{\"invoiceTypeCode\":\"91\",\"goodsName\":\"\",\"invoiceDate\":\"20171031\",\"totalAmount\":\"65.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"1b8388f2cd874c76a7f2a7016ce220ab\",\"invoiceCount\":\"\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"\",\"invoiceNo\":\"64123594\",\"invoiceAmount\":\"\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"\",\"arriveCity\":\"\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/1b8388f2cd874c76a7f2a7016ce220ab.zip\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":[{\"goodsName\":\"\",\"model\":\"\",\"unit\":\"\",\"invoiceNo\":\"\",\"invoiceCode\":\"\",\"num\":\"0.0\",\"unitPrice\":\"\",\"noTaxAmount\":\"\",\"taxRate\":\"\",\"taxAmount\":\"\",\"detailNo\":\"\",\"expenseItem\":\"\",\"plateNo\":\"\",\"type\":\"\",\"trafficDateStart\":\"\",\"trafficDateEnd\":\"\"}]},{\"invoiceTypeCode\":\"94\",\"goodsName\":\"\",\"invoiceDate\":\"20190126\",\"totalAmount\":\"20.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"0c497e41cd064906a09401f223be9f00\",\"invoiceCount\":\"1\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"141001920092\",\"invoiceNo\":\"13725022\",\"invoiceAmount\":\"0.00\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"洛阳站\",\"arriveCity\":\"汝阳\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/0c497e41cd064906a09401f223be9f00.zip\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":null},{\"invoiceTypeCode\":\"94\",\"goodsName\":\"\",\"invoiceDate\":\"20190126\",\"totalAmount\":\"20.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"140aab49258b45ea9338273f5c3fadf6\",\"invoiceCount\":\"1\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"141001920092\",\"invoiceNo\":\"13725022\",\"invoiceAmount\":\"0.00\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"洛阳站\",\"arriveCity\":\"汝阳\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/140aab49258b45ea9338273f5c3fadf6.zip\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":null},{\"invoiceTypeCode\":\"90\",\"goodsName\":\"\",\"invoiceDate\":\"20191009\",\"totalAmount\":\"860.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"0d12c318830c427191f232e9e5f898eb\",\"invoiceCount\":\"1\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"\",\"invoiceNo\":\"\",\"invoiceAmount\":\"760\",\"verifyCode\":\"1053\",\"dataSourceCode\":\"\",\"departCity\":\"T2北京\",\"arriveCity\":\"上海虹桥\",\"trainNumber\":\"11\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/0d12c318830c427191f232e9e5f898eb.zip\",\"passenger\":\"孔慧婷\",\"seatLevel\":\"\",\"identityNumber\":\"32128419910817522X\",\"electronicTicketNumber\":\"78131675771054\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"760.00\",\"caacDevelopmentFund\":\"50.00\",\"fuelSurcharge\":\"50.00\",\"detailList\":null},{\"invoiceTypeCode\":\"00\",\"goodsName\":\"\",\"invoiceDate\":\"20191010\",\"totalAmount\":\"123.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"0\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"undefined\",\"invoiceCount\":\"\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"\",\"invoiceNo\":\"123456\",\"invoiceAmount\":\"\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"\",\"arriveCity\":\"\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":null},{\"invoiceTypeCode\":\"91\",\"goodsName\":\"\",\"invoiceDate\":\"20190926\",\"totalAmount\":\"27.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"417160501c0c4182b34ccb0fb66b192c\",\"invoiceCount\":\"\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"\",\"invoiceNo\":\"01096034\",\"invoiceAmount\":\"\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"\",\"arriveCity\":\"\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/417160501c0c4182b34ccb0fb66b192c.zip\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":[{\"goodsName\":\"\",\"model\":\"\",\"unit\":\"\",\"invoiceNo\":\"\",\"invoiceCode\":\"\",\"num\":\"0.0\",\"unitPrice\":\"\",\"noTaxAmount\":\"\",\"taxRate\":\"\",\"taxAmount\":\"\",\"detailNo\":\"\",\"expenseItem\":\"\",\"plateNo\":\"\",\"type\":\"\",\"trafficDateStart\":\"\",\"trafficDateEnd\":\"\"}]},{\"invoiceTypeCode\":\"91\",\"goodsName\":\"\",\"invoiceDate\":\"20190926\",\"totalAmount\":\"27.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"ebe0d9c2403e4de9855b1905d83a1c84\",\"invoiceCount\":\"\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"\",\"invoiceNo\":\"01096034\",\"invoiceAmount\":\"\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"\",\"arriveCity\":\"\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/ebe0d9c2403e4de9855b1905d83a1c84.zip\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":[{\"goodsName\":\"\",\"model\":\"\",\"unit\":\"\",\"invoiceNo\":\"\",\"invoiceCode\":\"\",\"num\":\"0.0\",\"unitPrice\":\"\",\"noTaxAmount\":\"\",\"taxRate\":\"\",\"taxAmount\":\"\",\"detailNo\":\"\",\"expenseItem\":\"\",\"plateNo\":\"\",\"type\":\"\",\"trafficDateStart\":\"\",\"trafficDateEnd\":\"\"}]},{\"invoiceTypeCode\":\"91\",\"goodsName\":\"\",\"invoiceDate\":\"20190926\",\"totalAmount\":\"27.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"98adb9010d0649308679a9d39c10086c\",\"invoiceCount\":\"\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"\",\"invoiceNo\":\"01096034\",\"invoiceAmount\":\"\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"\",\"arriveCity\":\"\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/98adb9010d0649308679a9d39c10086c.zip\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":[{\"goodsName\":\"\",\"model\":\"\",\"unit\":\"\",\"invoiceNo\":\"\",\"invoiceCode\":\"\",\"num\":\"0.0\",\"unitPrice\":\"\",\"noTaxAmount\":\"\",\"taxRate\":\"\",\"taxAmount\":\"\",\"detailNo\":\"\",\"expenseItem\":\"\",\"plateNo\":\"\",\"type\":\"\",\"trafficDateStart\":\"\",\"trafficDateEnd\":\"\"}]},{\"invoiceTypeCode\":\"91\",\"goodsName\":\"\",\"invoiceDate\":\"20190926\",\"totalAmount\":\"27.00\",\"kplx\":\"\",\"isCanceled\":\"\",\"isBlush\":\"\",\"reimburseState\":\"0\",\"reimburseSerialNo\":\"\",\"reimburseUserId\":\"\",\"checkState\":\"0\",\"isException\":\"\",\"uuid\":\"51cf9e29d9744cf9ac4fa55cb4e60935\",\"invoiceCount\":\"\",\"taxAmount\":\"0.00\",\"buyerName\":\"\",\"buyerTaxNo\":\"\",\"buyerAddressTel\":\"\",\"buyerBankAccount\":\"\",\"salerName\":\"\",\"salerTaxNo\":\"\",\"salerAddressTel\":\"\",\"salerBankAccount\":\"\",\"invoiceCode\":\"\",\"invoiceNo\":\"01096034\",\"invoiceAmount\":\"\",\"verifyCode\":\"\",\"dataSourceCode\":\"\",\"departCity\":\"\",\"arriveCity\":\"\",\"trainNumber\":\"\",\"startDate\":\"\",\"endDate\":\"\",\"userId\":\"1111\",\"imagePath\":\"/bx/bxftp/image/pic/000001/1111/2019/51cf9e29d9744cf9ac4fa55cb4e60935.zip\",\"passenger\":\"\",\"seatLevel\":\"\",\"identityNumber\":\"\",\"electronicTicketNumber\":\"\",\"issueBy\":\"\",\"dateOfIssue\":\"\",\"flightNumber\":\"\",\"isDeductible\":\"\",\"billName\":\"\",\"expenseType\":\"\",\"fare\":\"\",\"caacDevelopmentFund\":\"\",\"fuelSurcharge\":\"\",\"detailList\":[{\"goodsName\":\"\",\"model\":\"\",\"unit\":\"\",\"invoiceNo\":\"\",\"invoiceCode\":\"\",\"num\":\"0.0\",\"unitPrice\":\"\",\"noTaxAmount\":\"\",\"taxRate\":\"\",\"taxAmount\":\"\",\"detailNo\":\"\",\"expenseItem\":\"\",\"plateNo\":\"\",\"type\":\"\",\"trafficDateStart\":\"\",\"trafficDateEnd\":\"\"}]}],\"total\":\"207\"}";
        JSONObject returnObject = JSONObject.parseObject(encode);

        JSONArray jsonArray = returnObject.getJSONArray("invoice");
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONArray detailList = jsonArray.getJSONObject(i).getJSONArray("detailList");
            System.out.println(detailList);
        }


    }

    @Test
    public void test32() throws UnsupportedEncodingException, DocumentException {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<ReturnModel>        \n" +
                "  <Result>         \n" +
                "    <IDCodeName>           \n" +
                "      <Remark>1001009140300002</Remark>           \n" +
                "      <M_iD>1002005263216753</M_iD>        \n" +
                "    </IDCodeName>         \n" +
                "    <IDCodeName>           \n" +
                "      <Remark>1001009140300002</Remark>           \n" +
                "      <M_iD>1002005263216762</M_iD>        \n" +
                "    </IDCodeName>       \n" +
                "  </Result>        \n" +
                "  <State>SUCCESS</State>        \n" +
                "  <IsApproved>false</IsApproved>        \n" +
                "  <Msg>凭证创建成功 m_voucherID:1002005263216753 sOB:1001009140200035 voucherCategory:1001009140300002m_voucherID:1002005263216762 sOB:1001009140200035 voucherCategory:1001009140300002</Msg>   \n" +
                "</ReturnModel>\n";
        Document doc = DocumentHelper.parseText(xml);
        Element rootElt = doc.getRootElement();
        String state = rootElt.elementTextTrim("State");
        String msg = rootElt.elementTextTrim("Msg");
        System.out.println(state);
        System.out.println(msg);
    }

    @Test
    public void test33() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date bdate = simpleDateFormat.parse("2019-11-24");

        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            System.out.println("双休日");
        } else {
            System.out.println("工作日");
        }

    }


    @Test
    public void test34() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 检测双休日
        Date bdate = simpleDateFormat.parse("2020-01-07");
        Calendar cal = Calendar.getInstance();
        cal.setTime(bdate);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            // 是休息日
            System.out.println("休息日");
        } else {
            System.out.println("工作日");
        }

    }

    @Test
    public void test35() {
        String[] indexs = {"-1", "1", "3"};
        Set<String> allSet = new HashSet<String>(Arrays.asList(indexs));
        allSet.add("3");
        Set<String> bfSet = new HashSet<String>();
        //bfSet.add("-1");
        bfSet.add("1");
        bfSet.add("3");
        bfSet.add("");
        allSet.removeAll(bfSet);

        System.out.println(JSONObject.toJSONString(allSet));
    }

    @Test
    public void test36() {
        String a = "1";
        String b = "1";
        System.out.println(a.codePointCount(0, a.length()));
    }

    @Test
    public void test37() {
        String code = "1002010|0|0|0|0|019|11015108386003|0|0|0";
        String s = SwUtil.deleteZero(code, "104");
        System.out.println(s);


    }

}









