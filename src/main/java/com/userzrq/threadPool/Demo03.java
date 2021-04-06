package com.userzrq.threadPool;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * PriorityBlockingQueue优先级队列的线程池
 *
 * @author 10017
 */
public class Demo03 {

    public static class Task implements Runnable, Comparable<Task> {

        private int i;
        private String name;

        public Task(int i, String name) {
            this.i = i;
            this.name = name;
        }

        @Override
        public int compareTo(Task o) {
            return Integer.compare(o.i, this.i);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "处理" + this.name);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                60,
                TimeUnit.SECONDS,
                new PriorityBlockingQueue<>()
        );
        for (int i = 0; i < 10; i++) {
            String taskName = "任务" + i;
            executor.execute(new Task(i,taskName));
        }
        for (int i = 100; i >= 90; i--) {
            String taskName = "任务" + i;
            executor.execute(new Task(i,taskName));
        }
        executor.shutdown();
    }
}
