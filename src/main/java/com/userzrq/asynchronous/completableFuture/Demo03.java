package com.userzrq.asynchronous.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

public class Demo03 {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            10,
            15,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>()
    );


    public static void test1() {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("Thread Id:" + Thread.currentThread().getId());
                System.out.println("result1: " + result);
                return result;
            }
        }, executor).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                long result = 5 * aLong;
                System.out.println("Thread Id:" + Thread.currentThread().getId());
                System.out.println("result2: " + result);
                return result;
            }
        });
    }

    public static void test2() {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                System.out.println("Thread Id:" + Thread.currentThread().getId());
                System.out.println("result1: " + result);
                return result;
            }
        }, executor).thenApplyAsync(new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                long result = 5 * aLong;
                System.out.println("Thread Id:" + Thread.currentThread().getId());
                System.out.println("result2: " + result);
                return result;
            }
        }, executor);
    }


    public static void main(String[] args) {
        test1();
        test2();
    }
}
