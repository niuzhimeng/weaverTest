package com.test.webserviceTest.vo;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static MyQueue myQueue = MyQueue.getInstance();

    public static void main(String[] args) throws Exception {
        executorService.execute(new Producer());
        executorService.execute(new Consumer());

        Scanner sc = new Scanner(System.in);
        //利用hasNextXXX()判断是否还有下一输入项
        while (sc.hasNext()) {
            //利用nextXXX()方法输出内容
            String str = sc.next();
            myQueue.add((str));
        }
    }
}
