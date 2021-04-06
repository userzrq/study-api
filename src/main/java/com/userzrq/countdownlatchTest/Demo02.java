package com.userzrq.countdownlatchTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo02 {

    public static class T extends Thread {
        int sleepSeconds;
        CountDownLatch countDownLatch;

        public T(String name, int sleepSeconds, CountDownLatch countDownLatch) {
            super(name);
            this.sleepSeconds = sleepSeconds;
            this.countDownLatch = countDownLatch;
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
            } finally {
                countDownLatch.countDown();
            }

            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "," + thread.getName() + ",处理完毕，耗时" + (endTime - startTime) + "ms");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 start");
        CountDownLatch countDownLatch = new CountDownLatch(2);

        long startTime = System.currentTimeMillis();
        T t1 = new T("解析sheet 1线程", 2, countDownLatch);
        t1.start();

        T t2 = new T("解析sheet 2线程", 5, countDownLatch);
        t2.start();

        // 阻塞等待锁的数量降为0后解除阻塞
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 end");
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时" + (endTime - startTime) + "ms");

    }
}
