package com.weavernorth;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64Util {
    private static final String ENCODING = "UTF-8";

    /**
     * 解码
     *
     * @param requestString
     * @return
     * @throws IOException
     */
    public static byte[] base64Decoder(String requestString) throws IOException {
        return new BASE64Decoder().decodeBuffer(requestString);
    }

    /*
     * 编码
     */
    public static String base64encoder(byte[] bytes) throws IOException {
        BASE64Encoder enc = new BASE64Encoder();
        String encStr = enc.encode(bytes);
        return encStr;
    }

    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String encode(String input) {
        try {
            input = new String(Base64.encodeBase64(input.getBytes(ENCODING)), ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            input = "";
        }
        return input;
    }

    public static String encode(byte[] input) {
        String ouput = "";
        try {
            ouput = new String(Base64.encodeBase64(input), ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            ouput = "";
        }
        return ouput;
    }

    public static String decode(String input) {
        String ouput = "";
        try {
            byte[] decodeBase64 = Base64.decodeBase64(input.getBytes(ENCODING));
            ouput = new String(decodeBase64, ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ouput;
    }
}
