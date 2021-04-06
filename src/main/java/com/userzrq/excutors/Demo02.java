package com.userzrq.excutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * scheduleAtFixedRate:固定的频率执行任务
 */
public class Demo02 {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        // 任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

        /**
         * 四个参数 执行的任务、延时执行第一次的时间、间隔时间、时间单位
         * 第1次：T1+initialDelay
         * 第2次：T1+initialDelay+period
         * 第3次：T1+initialDelay+2*period
         * 第n次：T1+initialDelay+(n-1)*period
         */
        executorService.scheduleAtFixedRate(() -> {
            int currCount = count.getAndIncrement();
            System.out.println(Thread.currentThread().getName());

            System.out.println(System.currentTimeMillis() + "第" +currCount +"次开始执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "第" +currCount +"次执行完毕");
        }, 1, 2, TimeUnit.SECONDS);

        // 如果任务执行的时间大于 延时执行的时间间隔
        // 那么下一次任务将在上一次任务执行完立即执行

    }
}
