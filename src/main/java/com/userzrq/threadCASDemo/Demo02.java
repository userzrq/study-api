package com.userzrq.threadCASDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo02 {
    /**
     * 访问次数
     * 此示例中只有主线程，如何还有其他线程，变量的可见性可能就不能保证，加上volatile保证可见性
     */
    volatile static int count = 0;

    // 模拟访问
    public static void request() throws InterruptedException {
        // 模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        int expectedCount;
        do {
            expectedCount = getCount();
        } while (!compareAndSwap(expectedCount, expectedCount + 1));

    }

    public static int getCount() {
        return count;
    }

    /**
     * 该方法也必须是线程安全的，不然多个去读，然后多个又同时写
     *
     * @param expectedCount
     * @param newCount
     * @return
     */
    public static synchronized boolean compareAndSwap(int expectedCount, int newCount) {
        // 判断最新的count是不是和预想的count值一样
        if (getCount() == expectedCount) {
            count = newCount;
            return true;
        }
        return false;
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
