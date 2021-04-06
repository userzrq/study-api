package com.userzrq.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义创建线程的工厂
 *
 * @author 10017
 */
public class Demo05 {

    static AtomicInteger threadNum = new AtomicInteger(1);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                // 自定义线程工厂的名字
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("自定义线程-" + threadNum.getAndIncrement());
                    return thread;
                }
        );


        for (int i = 0; i < 10; i++) {
            String taskName = "任务" + i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "处理" + taskName);
            });

            Future<?> submit = executor.submit(() -> {

            });
        }
        executor.shutdown();
    }
}
