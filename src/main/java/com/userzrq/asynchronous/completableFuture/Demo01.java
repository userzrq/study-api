package com.userzrq.asynchronous.completableFuture;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public class Demo01 {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            10,
            15,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>()
    );

    static ThreadLocal<String> trackerId = new ThreadLocal<>();

    public static void runAsync1() {
        try {
            CompletableFuture.runAsync(() -> {
                try {
                    trackerId.set(UUID.randomUUID().toString());
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("trackerId: " + trackerId.get() + "正在执行任务111");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, executor).whenComplete(
                    new BiConsumer<Void, Throwable>() {
                        @Override
                        public void accept(Void aVoid, Throwable throwable) {
                            System.out.println("trackerId: " + trackerId.get() + "执行完成111");
                        }
                    }
            );
        } finally {
            trackerId.remove();
        }
    }

    public static void runAsync2() {
        try {
            CompletableFuture.runAsync(() -> {
                try {
                    trackerId.set(UUID.randomUUID().toString());
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("trackerId: " + trackerId.get() + "正在执行任务222");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, executor).whenCompleteAsync(
                    new BiConsumer<Void, Throwable>() {
                        @Override
                        public void accept(Void aVoid, Throwable throwable) {
                            System.out.println("trackerId: " + trackerId.get() + "执行完成222");
                        }
                    }, executor
            );
        } finally {
            trackerId.remove();
        }
    }


    public static void main(String[] args) {
        runAsync1();
        runAsync2();
    }
}
