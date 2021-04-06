package com.userzrq.threadCASDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo01 {
    // 访问次数
    static int count = 0;

    // 模拟访问
    public static synchronized void request() throws InterruptedException {
        // 模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        count ++;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        // 理论值应该是 100*10= 1000
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

        // 阻塞式等待
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        // main，耗时：124,count=994
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - startTime) + ",count=" + count);
    }
}
