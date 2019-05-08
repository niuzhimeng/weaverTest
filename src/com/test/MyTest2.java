package com.test;

import org.junit.Test;

import java.io.*;

public class MyTest2 {

    @Test
    public void test1() throws Exception {
        File file = new File("D:/1.txt");
        //读取文件(缓存字节流)
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("d:\\E9流程表单前端接口.pdf"));
        //写入相应的文件
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("d:\\2.pdf"));
        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = in.read(bytes)) != -1) {
            //写入相关文件
            System.out.println(n);
            out.write(bytes, 0, n);
        }
        //清楚缓存
        out.flush();
        //关闭流
        in.close();
        out.close();
    }
}