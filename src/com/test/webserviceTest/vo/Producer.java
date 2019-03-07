package com.test.webserviceTest.vo;

public class Producer implements Runnable {
    @Override
    public void run() {
        MyQueue myQueue = MyQueue.getInstance();
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("生产： " + i);
               // Thread.sleep(500);
                myQueue.add(i + "");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
