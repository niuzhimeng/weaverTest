package com.weavernorth.gaoji.util;

import com.squareup.okhttp.*;
import weaver.general.BaseBean;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * okhttp工具类
 * Created by nzm on 2017/3/6.
 */
public class OkHttpUtils {
    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static BaseBean baseBean = new BaseBean();

    static {
        //超时配置
        client.setConnectTimeout(300, TimeUnit.SECONDS);
        client.setReadTimeout(300, TimeUnit.SECONDS);
    }

    /**
     * 普通的get方法
     *
     * @param url
     * @return
     */
    public static String get(String url) {
        return getMethod(url);
    }

    private static String getMethod(String url) {
        String result = null;
        Request request = new Request.Builder().url(url).build();
        try {
            result = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * POST请求，json格式
     *
     * @param url  访问URL
     * @param json 传送的json串
     * @return 返回的数据
     */
    public static String post(String url, String json) {
        String result = "";
        try {
            OkHttpClient client = getConnection();
            RequestBody formBody = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(url).post(formBody).build();

            Response response;
            response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * put方法（提交json串和headers）
     *
     * @param url
     * @param json
     * @param headers
     * @return
     */
    public static String postJsonHeader(String url, String json, Map<String, String> headers) {
        String result = "";
        try {
            baseBean.writeLog("okhttp发起请求=========url: "+url);
            OkHttpClient client = getConnection();
            RequestBody formBody = RequestBody.create(JSON, json);
            Request request;
            Request.Builder builder = new Request.Builder();
            for (Map.Entry<String, String> entity : headers.entrySet()) {
                builder.header(entity.getKey(), entity.getValue());
            }
            baseBean.writeLog("okhttp请求PO开始");
            request = builder.url(url).put(formBody).build();
            baseBean.writeLog("okhttp请求PO结束");
            Response response;
            response = client.newCall(request).execute();
            baseBean.writeLog("okhttp返回response: " + response);
            result = String.valueOf(response.code());
        } catch (Exception e) {
            baseBean.writeLog("OKHTTP 异常" + e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 访问https证书验证
     *
     * @return
     * @throws Exception
     */
    public static OkHttpClient getConnection() throws Exception {
        try {
            SSLContext ctx = SSLContext.getInstance("SSL");
            ctx.init(new KeyManager[0], new TrustManager[]{new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

            }}, new SecureRandom());
            client.setSslSocketFactory(ctx.getSocketFactory());
            client.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return client;
    }


}
