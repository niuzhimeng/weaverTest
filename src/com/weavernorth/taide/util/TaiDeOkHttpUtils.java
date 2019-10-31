package com.weavernorth.taide.util;

import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * okhttp工具类
 * Created by nzm on 2017/3/6.
 */
public class TaiDeOkHttpUtils {
    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

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
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = RequestBody.create(JSON, json);
            Request request = new Request.Builder().url(url).post(formBody).build();

            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

