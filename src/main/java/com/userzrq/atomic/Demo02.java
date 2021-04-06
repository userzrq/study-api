package com.userzrq.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基本类型原子类
 *
 * @author 10017
 */
public class Demo02 {

    // 原子类访问次数，底层CAS保证线程安全
    static AtomicInteger count = new AtomicInteger();

    public static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        count.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        int threadCount = 100;
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < threadCount; i++) {

            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {

                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });

            thread.start();
        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - startTime) + ",count=" + count);
    }
}
