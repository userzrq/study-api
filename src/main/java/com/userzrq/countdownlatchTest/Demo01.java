package com.userzrq.countdownlatchTest;

import java.util.concurrent.TimeUnit;

public class Demo01 {

    public static class T extends Thread {
        // 休眠时间（秒）
        int sleepSeconds;

        public T(String name, int sleepSeconds) {
            super(name);
            this.sleepSeconds = sleepSeconds;
        }

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "," + thread.getName() + ",开始处理!");

            try {
                TimeUnit.SECONDS.sleep(this.sleepSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "," + thread.getName() + ",处理完毕，耗时" + (endTime - startTime) + "ms");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        T t1 = new T("解析线程1", 2);
        t1.start();

        T t2 = new T("解析线程2", 5);
        t2.start();

        t1.join();
        t2.join();
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时" + (endTime - startTime));
    }
}
