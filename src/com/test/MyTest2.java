package com.test;

import com.google.gson.Gson;
import com.sap.conn.jco.JCoDestination;
import com.weavernorth.OA2archives.util.ConvertToPdf;
import com.weavernorth.caibai.sap.CaiBaiPoolThree;
import com.weavernorth.workflow.waterpdf.WaterPDF;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyTest2 {

    @Test
    public void test1() throws Exception {
        File file = new File("D:\\test\\2.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        //读取文件(缓存字节流)
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:\\1.txt"));
        //写入相应的文件
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n;
        //循环取出数据
        while ((n = in.read(bytes)) != -1) {
            //写入相关文件 n是读取的长度
            System.out.println(n);
            out.write(bytes, 0, n);
        }
        //清楚缓存
        out.flush();
        //关闭流
        in.close();
        out.close();
    }

    @Test
    public void test2() throws Exception {
        String str = "<root><data><msg>1</msg></data></root>";
        Document doc = DocumentHelper.parseText(str);
        Element rootElt = doc.getRootElement();
        String workCode = rootElt.element("data").elementTextTrim("msg");

        System.out.println(workCode);

    }

    @Test
    public void test3() throws IOException {
        String str = "牛智萌1234876abc";
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(str.getBytes());
        System.out.println("encode: " + encode);

        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = base64Decoder.decodeBuffer(encode);
        System.out.println(new String(bytes, "utf-8"));
    }

    @Test
    public void test4() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        Date dksj = dateFormat.parse("17:18");
        Date jssj = dateFormat.parse("17:30");
        if (dksj.before(jssj)) {
            System.out.println("错误");
        }
    }

    @Test
    public void test5() {
        Map<String, BigDecimal> jtfMap = new HashMap<String, BigDecimal>();
        jtfMap.put("car", new BigDecimal("3.25"));
        jtfMap.put("car", new BigDecimal("4.91").add(jtfMap.get("car")));
        System.out.println(new Gson().toJson(jtfMap));
    }

    @Test
    public void test6() throws UnsupportedEncodingException {
        String receive = "workflow/request/tuanChe/OfsToDo.jsp?flowUrl=http://cloud.italent.cn/ItalentTransfer?iTalentFrame=Ly9hcHByb3ZhbC5pdGFsZW50LmNuI0RldGFpbFBhZ2U/bWV0YU9iak5hbWU9QXBwcm92YWxDZW50cmUuQXBwcm92YWxPYmplY3QmaWQ9ODAwMjc0NDUtODY0OS00ZWExLThlNjgtY2Q3MDRmZmRkYWFiJmZvcm1zdGF0ZT1zaG93JnRhc2tPYmplY3RJZD03MmY2ZGFmZS0xMDRjLTQ4M2UtYTJiZS04MTJhODAwZDczOTMmcHJvY2Vzc0luc3RhbmNlSWQ9ODAwMjc0NDUtODY0OS00ZWExLThlNjgtY2Q3MDRmZmRkYWFiJnM9QXR0ZW5kYW5jZS4xNQ**, 长度： 434";
        String encode = URLEncoder.encode(receive, "utf-8");
        String decode = URLDecoder.decode(encode, "utf-8");
        System.out.println(encode);
        System.out.println(decode);

    }

    @Test
    public void test7() throws Exception {
        FileInputStream inputStream = new FileInputStream("E:\\11.doc");
        OutputStream outputStream = new FileOutputStream("E:\\12.doc");


        HWPFDocument document = new HWPFDocument(inputStream);
        Range bodyRange = document.getRange();
        System.out.println(bodyRange.toString());
        System.out.println(bodyRange.text());

        HashMap<String, String> contentMap = new HashMap<String, String>();
        contentMap.put("name", "牛智萌");


        // 替换内容
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
        }

        //导出到文件
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.write(byteArrayOutputStream);

            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test8() {
        for (int i = 1; i <= 5; i++) {
            //将空格和*分开看，看" "的变化i=1时，它是4，2的时候是3，找规律
            for (int j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            //找规律，i是 1 3 5 7 9基数
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print('*');
            }
            //换一行
            System.out.println();
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= i; j++) {//空格 1 2 3 4 so
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * (4 - i + 1) - 1; k++) {//* 7 5 3 1 倒着来的基数
                System.out.print('*');
            }
            System.out.println();
        }
    }

    @Test
    public void test9() throws Exception {
//        ConvertToPdf conpdf = new ConvertToPdf();
//        boolean isOk = conpdf.convert2PDF("D:/WEAVER/ecology/contactReal/327123.docx","D:/WEAVER/ecology/contactReal/327123.pdf");
        WaterPDF.addPdfMark("C:\\Users\\29529\\Desktop\\test.pdf","C:\\Users\\29529\\Desktop/327123.pdf", "", 0);
    }


    @Test
    public void test10(){
        ConvertToPdf conpdf = new ConvertToPdf();
        boolean isOk = conpdf.convert2PDF("C:\\Users\\29529\\Desktop\\PDF加水印Action-配置手册.doc", "C:\\Users\\29529\\Desktop\\test.pdf");
    }

    @Test
    public void test11() throws Exception {
        JCoDestination jCoDestination = CaiBaiPoolThree.getJCoDestination();
        jCoDestination.ping();
//        JCoFunction function = jCoDestination.getRepository().getFunctionTemplate("ZMMEX0010_JJJB_IF").getFunction();
//
//        JCoParameterList tableParameterList = function.getTableParameterList();
//
//        System.out.println(1);
//        function.getImportParameterList().getTable("IE_TAB");

    }


}

















