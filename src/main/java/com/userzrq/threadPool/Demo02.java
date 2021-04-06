package com.userzrq.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue队列的线程池
 */
public class Demo02 {

    public static void main(String[] args) {
        // 静态工厂方法
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 50; i++) {
            int j = i;
            String taskName = "任务" + j;

            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "处理" + taskName);
                try {
                    TimeUnit.SECONDS.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
