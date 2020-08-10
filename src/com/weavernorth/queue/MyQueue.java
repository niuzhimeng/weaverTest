package com.weavernorth.queue;

import org.junit.Test;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyQueue {


    @Test
    public void test() {
        // 队列读取文件夹下所有文件， 代替递归
        File file = new File("E:\\WEAVER\\ecology\\classbean\\com\\weavernorth\\downfile");

        Queue<File> queue = new LinkedBlockingQueue<File>(1000 * 100);
        queue.offer(file);

        while (!queue.isEmpty()) {
            File currFile = queue.poll();
            if (currFile.isDirectory()) {
                File[] files = currFile.listFiles();
                if (files != null) {
                    for (File listFile : files) {
                        queue.offer(listFile);
                    }
                }
            } else {
                System.out.println(currFile.getName());
            }
        }
    }

    @Test
    public void test1() {
        // 递归
        File file = new File("E:\\WEAVER\\ecology\\classbean\\com\\weavernorth\\downfile");
        if (file.exists()) {
            diGui(file);
        }

    }

    private void diGui(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    diGui(file1);
                }
            }
        } else {
            System.out.println(file.getName());
        }
    }

    @Test
    public void test2() {
        // 递归阶乘
        System.out.println(jiecheng(5));
    }

    private int jiecheng(int start) {
        if (start <= 1) {
            return 1;
        } else {
            return start * jiecheng(start - 1);
        }
    }

    @Test
    public void test3() {
        // 队列实现阶乘
        int i = queueJieCheng(5);
        System.out.println(i);
    }

    private int queueJieCheng(int start) {
        Queue<Integer> queue = new LinkedBlockingQueue<Integer>(1);
        queue.offer(1);

        int i = 1;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll <= start) {
                i *= poll;
                queue.offer(++poll);
            }

        }

        return i;
    }

    @Test
    public void test4() {
        int result = 0;
        int i = 5;
        while (i > 0) {
            if (i == 1) {
                break;
            }

            if (i == 5) {
                result = i * --i;
            } else {
                result *= --i;
            }

        }

        System.out.println(result);
    }


    @Test
    public void test5(){
        File file = new File("E:\\WEAVER\\安装包说明文档.txt");
        System.out.println(file.listFiles());

    }
}
