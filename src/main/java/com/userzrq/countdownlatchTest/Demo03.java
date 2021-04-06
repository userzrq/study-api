package com.userzrq.countdownlatchTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 等待指定时间
 */
public class Demo03 {

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
                // 模拟耗时操作，休眠sleepSeconds秒
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
        Demo02.T t1 = new Demo02.T("解析sheet 1线程", 2, countDownLatch);
        t1.start();

        Demo02.T t2 = new Demo02.T("解析sheet 2线程", 5, countDownLatch);
        t2.start();

        // 线程是已经启动了，但没在2000ms内完成对countDownLatch的扣除，不管计数器是否为0，await方法都会返回
        boolean result = countDownLatch.await(2, TimeUnit.SECONDS);
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 end");
        long endTime = System.currentTimeMillis();
        System.out.println("主线程耗时" + (endTime - startTime) + "ms,result:" + result);
    }
}
