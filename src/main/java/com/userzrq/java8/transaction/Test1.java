package com.userzrq.java8.transaction;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test1 {

    public static void main(String[] args) {


        CountDownLatch countDownLatch = new CountDownLatch(1);
        ScheduledExecutorService scheduleExecutor = new ScheduledThreadPoolExecutor(1);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " " ); //+ atomicInteger.getAndIncrement());
                CompletableFuture<String> futureA = CompletableFuture.supplyAsync(()->{
                    System.out.println("启动A");
                    try {
                        //异步调用
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //异常处理
                        e.printStackTrace();
                    }
                    return "completableFutureA";
                }).exceptionally(throwable -> {
                    System.out.println(throwable.getMessage());
                    countDownLatch.countDown();
                    throw new RuntimeException(throwable.getMessage());
                }).thenCombine(CompletableFuture.runAsync(() -> {
                    throw new RuntimeException("异常报错"+Thread.currentThread());
                }).exceptionally(throwable->{
                    // flag = true;
                    countDownLatch.countDown();
                    System.out.println(throwable.getMessage());
                    throw new RuntimeException(throwable.getMessage());
                }),(s1,s2)->s1+ " " + s2);
            }
        };
        ScheduledFuture scheduledFuture = scheduleExecutor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
        try {
            countDownLatch.await();
            scheduledFuture.cancel(true);
            if (scheduledFuture.isCancelled()) {
                System.out.println("is Cancelled");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
