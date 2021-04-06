package com.userzrq.excutors;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduleThreadPoolExecutor 延时执行
 */
public class Demo01 {


    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);


        /**
         * 3个参数依次为，需要执行的任务，需要延迟的时间，时间单位
         */
        executorService.schedule(() -> {
            System.out.println(System.currentTimeMillis() + "开始执行");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(System.currentTimeMillis() + "执行结束");
        }, 2, TimeUnit.SECONDS);

    }
}
