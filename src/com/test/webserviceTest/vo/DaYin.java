package com.test.webserviceTest.vo;

public class DaYin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(800);
                System.out.println(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
