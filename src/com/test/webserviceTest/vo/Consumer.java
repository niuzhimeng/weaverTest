package com.test.webserviceTest.vo;

public class Consumer implements Runnable {
    @Override
    public void run() {
        while (true) {
            MyQueue instance = MyQueue.getInstance();
            try {
                Thread.sleep(500);
                System.out.println("消费：" + instance.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
