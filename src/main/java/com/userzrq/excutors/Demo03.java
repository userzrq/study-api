package com.userzrq.excutors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * scheduleWithFixedDelay:固定的间隔执行任务
 * <p>
 * 在上一个任务结束后增加恒定的时间间隔（任务的执行时间可能有长有短）
 */
public class Demo03 {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());

        AtomicInteger count = new AtomicInteger(1);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

        /**
         * command：表示要执行的任务
         * initialDelay：表示延迟多久执行第一次
         * period：表示下次执行时间和上次执行结束时间之间的间隔时间
         * unit：参数2和参数3的时间单位，是个枚举，可以是天、小时、分钟、秒、毫秒、纳秒等
         *
         * 第1次：T1+initialDelay，执行结束时间：E1
         * 第2次：E1+period，执行结束时间：E2
         * 第3次：E2+period，执行结束时间：E3
         * 第4次：E3+period，执行结束时间：E4
         * 第n次：上次执行结束时间+period
         */
        ScheduledFuture<?> scheduledFuture = executorService.scheduleWithFixedDelay(() -> {
            int currCount = count.getAndIncrement();
            System.out.println(Thread.currentThread().getName());

            System.out.println(System.currentTimeMillis() + "第" + currCount + "次开始执行");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次执行完毕");

        }, 1, 3, TimeUnit.SECONDS);
    }
}
