package com.userzrq.counter;

import java.util.concurrent.CountDownLatch;

public class Demo01 {

    static int count = 0;

    public static synchronized void incr() {
        count++;
    }

    public static void m1() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadCount = 50;
        CountDownLatch countDownLatch = new CountDownLatch(50);

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000000; j++) {
                        incr();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();

        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("结果：%s,耗时(ms)：%s", count, (endTime - startTime)));
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            count = 0;
            m1();
        }
    }
}
