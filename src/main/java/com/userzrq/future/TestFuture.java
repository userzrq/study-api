package com.userzrq.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFuture {

    public static void main(String[] args) {

        // 不使用线程池的使用方式

        new Thread(new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        })).start();

        new Thread(new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                new Random().nextInt();
            }
        }, Integer.class)).start();


        FutureTask<Integer> task1 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        task1.run();
        new Thread(task1).start();

        FutureTask<Class<Integer>> task2 = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                new Random().nextInt();
            }
        }, Integer.class);
        new Thread(task2).start();

        try {
            // 阻塞式等待
            System.out.println(task1.get());
            System.out.println(task2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
