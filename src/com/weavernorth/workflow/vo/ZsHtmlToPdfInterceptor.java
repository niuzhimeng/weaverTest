package com.weavernorth.workflow.vo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ZsHtmlToPdfInterceptor extends Thread {
    private InputStream is;

    public ZsHtmlToPdfInterceptor(InputStream is){
        this.is = is;
    }

    @Override
    public void run(){
        try{
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                //输出内容
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
