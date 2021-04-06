package com.userzrq.excutors;

import cn.hutool.cron.TaskLauncher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步执行一批任务，有一个完成立即返回，其他取消
 */
public class Demo14 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Callable<Integer>> list = new ArrayList<>();

        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = 2 * i;
            String taskName = "任务" + i;
            list.add(() -> {
                // 定义Callable的具体任务内容
                TimeUnit.SECONDS.sleep(j);
                System.out.println(taskName + "任务完成");
                return j;
            });
        }
        Integer integer = invokeAny(executorService, list);
        System.out.println("耗时:" + (System.currentTimeMillis() - startTime) + ",执行结果:" + integer);
        executorService.shutdown();
    }


    public static <T> T invokeAny(ExecutorService executorService, Collection<Callable<T>> tasks) throws InterruptedException, ExecutionException {
        ExecutorCompletionService<T> completionService = new ExecutorCompletionService<>(executorService);
//        List<Future<T>> list = new ArrayList<>();
        for (Callable<T> task : tasks) {
            //list.add(completionService.submit(task));
            completionService.submit(task);
        }

        int size = tasks.size();
        try {
            for (int i = 0; i < size; ++i) {
                // take阻塞式等待，返回并移除一个已经完成的任务
                T t = completionService.take().get();
                if (!Objects.isNull(t)) {
                    return t;
                }
            }
        } finally {
            for (int i = 0; i < size - 1; i++) {
                completionService.take().cancel(true);
            }
        }
        return null;
    }
}
