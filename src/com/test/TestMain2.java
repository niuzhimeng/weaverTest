package com.test;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import weaver.general.TimeUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class TestMain2 {

    @Test
    public void test1() {
        String s = String.format("%03d", 1);
        System.out.println(s);
        System.out.println(TimeUtil.getCurrentDateString());
        String currYear = "2021-08-19".substring(2, 4);
        System.out.println(currYear);
    }

    @Test
    public void test2() {
        String string = "213.1233777773213121232432432542343214444444444";
        String lshStr = String.format("%.8f", Double.valueOf(string));
        System.out.println(lshStr);
    }

    @Test
    public void test3() {
        String flowStr = "OA_INVESTMENT"; // 约定字符串

        String workCode = "1001"; // 工号
        String requestId = "98121"; // 请求id
        long currentTime = System.currentTimeMillis(); // 当前时间戳13位，有效时间10分钟
        System.out.println(currentTime + flowStr + workCode);

        String token = stringToMD5(currentTime + flowStr + workCode); // 生成的token
        String url = "http://localhost:8080/devjsp8/shunxin/OpenWorkflow.jsp?workCode=" + workCode + "&requestId=" + requestId +
                "&currentTime=" + currentTime + "&token=" + token;
        System.out.println(url);
    }

    public String stringToMD5(String plainText) {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code.insert(0, "0");
        }
        return md5code.toString().toUpperCase();
    }

    @Test
    public void test4() {
        Element root = DocumentHelper.createElement("ROOT");
        Element item = root.addElement("item");
        item.addElement("XBLNR").setText("FI0120200824001S");
        item.addElement("BUKRS").setText("N100");
        item.addElement("USNAM").setText("孙晓威");
        item.addElement("BLDAT").setText("2020-08-24");
        item.addElement("BUDAT").setText("2020-08-24");

        item.addElement("BLART").setText("SA");
        item.addElement("WAERS").setText("CNY");
        item.addElement("BKTXT").setText("孙思宇_123");
        // 拼接明细
        Element item1 = item.addElement("ITEM1");
        for (int i = 0; i < 3; i++) {
            Element itemTemp = item1.addElement("item");
            itemTemp.addElement("BSCHL").setText("40");
            itemTemp.addElement("LIFNR").setText("");
            itemTemp.addElement("HKONT").setText("6601080000");
            itemTemp.addElement("MWSKZ").setText("J0");
            itemTemp.addElement("KOSTL").setText("N100000019");

            itemTemp.addElement("PRCTR").setText("N1000000");
            itemTemp.addElement("AUFNR").setText("");
            itemTemp.addElement("WRBTR").setText("100.00");
            itemTemp.addElement("VALUT").setText("");
            itemTemp.addElement("RSTGR").setText("");

            itemTemp.addElement("SGTXT").setText("孙思宇_123");
            itemTemp.addElement("KUNNR").setText("");
            itemTemp.addElement("UMSKZ").setText("");
            itemTemp.addElement("SAMNR").setText("");
            itemTemp.addElement("SPART").setText("");

            itemTemp.addElement("ANLN1").setText("");
            itemTemp.addElement("ZFBDT").setText("2020-08-24");
            itemTemp.addElement("KKBER").setText("");
            itemTemp.addElement("XREF3").setText("");
            itemTemp.addElement("ZUONR").setText("");
        }

        Document document = DocumentHelper.createDocument(root);
        String pushXml = document.asXML();
        System.out.println(pushXml);
    }

    @Test
    public void test5() {
        String encodeString = getMD5OfStr("zj@hjsoft.comHJEHR1500374506");
        //6fe3b406b2c1febc5b15d05ceadd93cf
        System.out.println(encodeString);
        System.out.println("yuwen".hashCode());

    }

    public static String getMD5OfStr(String unencodeStr) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(unencodeStr.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        return md5StrBuff.toString();
    }


    @Test
    public void test6() throws UnsupportedEncodingException {
        String url = "/spa/workflow/index_mobx.jsp#/main/workflow/queryFlowResult?fromwhere=jsonFilter&eid=172&tabid=2";
        String encode = URLEncoder.encode(url, "utf-8");
        System.out.println(encode);
    }



    @Test
    public void test7() {

    }

}

