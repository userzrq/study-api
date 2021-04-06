package com.userzrq.threadlocaldemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo01 {

//    static ThreadLocal<String> trackerId = new ThreadLocal<>();
//
//    static AtomicInteger threadIndex = new AtomicInteger(1);
//
//    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
//            3,
//            3,
//            60,
//            TimeUnit.SECONDS,
//            new LinkedBlockingQueue<>(),
//            r -> {
//                Thread thread = new Thread(r);
//                thread.setName("disposeRequestThread-" + threadIndex.getAndIncrement());
//                return thread;
//            }
//    );
//
//    /**
//     * 记录日志
//     *
//     * @param msg
//     */
//    static void log(String msg) {
//        StackTraceElement stack[] = (new Throwable()).getStackTrace();
//        //获取当前线程存放tranceId口袋中的内容
//        String traceId = trackerId.get();
//        System.out.println("****" + System.currentTimeMillis() + "[traceId:" + traceId + "],[线程:" + Thread.currentThread().getName() + "]," + stack[1] + ":" + msg);
//    }
//
//    /**
//     * 模拟controller
//     */
//    public static void controller(List<String> dataList) {
//        log("接受请求");
//        service(dataList);
//    }
//
//    /**
//     * 模拟service
//     */
//    public static void service(List<String> dataList) {
//        log("执行业务");
//        dao(dataList);
//    }
//
//    /**
//     * 模拟dao
//     */
//    public static void dao(List<String> dataList) {
//        log("执行数据库操作");
//        //模拟插入数据
//        for (String s : dataList) {
//            log("插入数据" + s + "成功");
//        }
//    }
//
//    public static void main(String[] args) {
//        // 需要插入的数据
//        List<String> dataList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            dataList.add("数据" + i);
//        }
//
//        int requestCount = 5;
//        for (int i = 0; i < requestCount; i++) {
//            String traceId = String.valueOf(i);
//            executor.execute(() -> {
//                // lambda表达式重写Runnable函数式接口的run方法
//                // 每个线程存储的数据彼此间都是独立的
//                trackerId.set(traceId);
//
//                try {
//                    controller(dataList);
//                } finally {
//                    trackerId.remove();
//                }
//
//            });
//        }
//
//        executor.shutdown();
//    }
}
