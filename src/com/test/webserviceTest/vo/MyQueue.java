package com.test.webserviceTest.vo;

import java.util.concurrent.LinkedBlockingQueue;

public class MyQueue {

    private static MyQueue myQueue = new MyQueue();

    private MyQueue() {
    }

    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(5);

    public void add(String value) throws InterruptedException {
        queue.put(value);
        System.out.println("queue长度： " + queue.size());
    }

    public String take() throws InterruptedException {
        return queue.take();
    }

    public static MyQueue getInstance() {
        return myQueue;
    }


}
