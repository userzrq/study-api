package com.userzrq.java8.transaction;

import java.util.concurrent.CompletableFuture;

public class Test2 {

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            System.out.println("启动A");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "completableFutureA";
        }).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            throw new RuntimeException(throwable.getMessage());
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("异常报错");
        }).exceptionally(throwable -> {
            System.out.println(throwable.getMessage());
            throw new RuntimeException(throwable.getMessage());
        }), (s1, s2) -> s1 + " " + s2);


        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            System.out.println("启动B");
            try {
                //异步调用
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //异常处理
                e.printStackTrace();
            }
            return "completableFutureB";
        });
        CompletableFuture<String> futureC = futureA.thenApplyAsync((c) -> {

            System.out.println("启动C");
            try {
                //异步调用
            } catch (Exception e) {
                //异常处理
                e.printStackTrace();
            }
            return "completableFutureC";
        });
//        System.out.println("阻塞读取");
        CompletableFuture result = CompletableFuture.allOf(futureA, futureB, futureC);
        System.out.println("读取结果 ：" + futureA.get() + futureB.get() + futureC.get());
        System.out.println(result);
    }
}
