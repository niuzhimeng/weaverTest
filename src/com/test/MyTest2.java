package com.test;

import com.google.gson.Gson;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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
        String decode = URLEncoder.encode(receive, "gbk");
        System.out.println(decode);

    }

    @Test
    public void test7() {
        DecimalFormat df2 = new DecimalFormat("##########################################0.00");
        String format = df2.format(0.00);
        System.out.println(format);
    }


}