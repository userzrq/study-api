package com.userzrq.threadResult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Demo05 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(System.currentTimeMillis());
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 10);


        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            futureTask.run();
        }).start();

        System.out.println(System.currentTimeMillis());
        Integer result = futureTask.get();

        System.out.println(System.currentTimeMillis() + ":" + result);
    }
}
