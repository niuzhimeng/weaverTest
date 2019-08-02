package com.test;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lowagie.text.pdf.*;
import com.weaver.general.TimeUtil;
import com.weavernorth.saiwen.myWeb.WebUtil;
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

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

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

    @Test
    public void test13() throws Exception {
        String myXml = "<?xml version=\"1.0\" encoding=\"utf‐16\"?>\n" +
                "<Cbo_SupplierBankAccount_Query_Model>\n" +
                "</Cbo_SupplierBankAccount_Query_Model>";

        // 获取客户信息
        String returnXml = WebUtil.getCustomer(myXml);
        System.out.println(returnXml);

    }

}








