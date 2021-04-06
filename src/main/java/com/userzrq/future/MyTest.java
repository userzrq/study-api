package com.userzrq.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyTest {
    public static void main(String[] args) {

        // 结合线程池的方式进行使用
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> result = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return new Random().nextInt();
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                // 无返回值执行任务
            }
        });

        executor.shutdown();

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
