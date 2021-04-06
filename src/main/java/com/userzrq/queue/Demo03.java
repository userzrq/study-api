package com.userzrq.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Demo03 {

    static SynchronousQueue<String> queue = new SynchronousQueue<String>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                long startTime = System.currentTimeMillis();
                queue.put("java高并发系列");
                long endTime = System.currentTimeMillis();
                System.out.println(String.format("[%s,%s,take耗时:%s],%s", startTime, endTime, (endTime - startTime), Thread.currentThread().getName()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(5);
        String result = queue.take();

        System.out.println("result: " + result);
    }
}
