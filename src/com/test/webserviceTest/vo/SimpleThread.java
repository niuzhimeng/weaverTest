package com.test.webserviceTest.vo;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleThread {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        Runnable task = new Runnable() {
            public void run() {
                for (int i = 0; i < 1000000; ++i) {
                    atomicInteger.getAndIncrement();
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        // 等待t1和t2执行完成
        t1.join();
        t2.join();

        System.out.println("count = " + atomicInteger.toString());
    }
}
