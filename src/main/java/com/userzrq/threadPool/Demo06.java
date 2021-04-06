package com.userzrq.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 4种常见饱和策略
 * <p>
 * 自定义饱和策略
 *
 * @author 10017
 */
public class Demo06 {

    static class Task implements Runnable {
        String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "处理" + this.name);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" + "name='" + name + '\'' + '}';
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                60L,
                TimeUnit.SECONDS,
                // 处理1个 在队列中放一个
                new ArrayBlockingQueue<Runnable>(1),
                Executors.defaultThreadFactory(),
                (r, executors) -> {
                    // 自定义饱和策略
                    // 记录一下无法处理的任务
                    System.out.println("无法记录的任务：" + r.toString());
                }
        );

        for (int i = 0; i < 5; i++) {
            executor.execute(new Task("任务" + i));
        }
        executor.shutdown();

        // 待线程池完全关闭时
        if(executor.isShutdown() && executor.isTerminated()){

        }
    }
}
