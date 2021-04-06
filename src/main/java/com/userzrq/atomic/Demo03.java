package com.userzrq.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 数组类型原子类
 * <p>
 * AtomicIntegerArray：整形数组原子操作类
 * AtomicLongArray：长整形数组原子操作类
 * AtomicReferenceArray ：引用类型数组原子操作类
 *
 * @author 10017
 */
public class Demo03 {

    static AtomicIntegerArray pageRequest = new AtomicIntegerArray(new int[10]);

    public static void request(int page) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);

        int pageCountIndex = page - 1;
        pageRequest.incrementAndGet(pageCountIndex);
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    // 每个线程每页访问10次，每页访问1000次
                    for (int page = 1; page <= 10; page++) {
                        for (int j = 0; j < 10; j++) {
                            request(page);
                        }
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
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime));

        for (int pageIndex = 0; pageIndex < 10; pageIndex++) {
            System.out.println("第" + (pageIndex + 1) + "个页面访问次数为" + pageRequest.get(pageIndex));
        }
    }
}
