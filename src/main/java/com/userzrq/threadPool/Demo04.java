package com.userzrq.threadPool;

import jodd.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义创建线程的工厂
 *
 * @author 10017
 */
public class Demo04 {

    public static class T extends Thread {
        public T() {
            super("T-class");
        }

        @Override
        public void run() {
            System.out.println("Thread name " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ThreadFactory namedFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").get();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                10,
                60,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                namedFactory
        );

        for (int i = 0; i < 10; i++) {
            try {
                executor.execute(new T());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
