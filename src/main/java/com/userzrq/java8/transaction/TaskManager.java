package com.userzrq.java8.transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class TaskManager {

    private List<MyFutureTask> taskList = new LinkedList<>();

    private boolean isSuccesss = false;

    public TaskManager() {
    }

    public void addTask(Task task) {
        taskList.add(new MyFutureTask(task));
    }

    public void done() {
        // runAsync 没有返回结果
        CompletableFuture[] completableFutures = taskList.stream().map(task -> CompletableFuture.runAsync(task).whenCompleteAsync(this::done1))
                .collect(Collectors.toList())
                .toArray(new CompletableFuture[taskList.size()]);
        CompletableFuture.allOf(completableFutures).join();
    }

    public void done1(Void v, Throwable t) {
        if (!isSuccesss) {
            isSuccesss = true;
            try {
                for (MyFutureTask futureTask : taskList) {
                    // 在阻塞式等待中出现异常时
                    futureTask.get();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // List中的每一个任务，如果任务完成了就执行
                // 一个任务执行失败，
                for (MyFutureTask futureTask : taskList) {
                    if (!futureTask.isCancelled()) {
                        futureTask.cancel(true);
                        Task task = (Task) futureTask.getCallable();
                        task.rollback();
                    }
                }
            }
        }
    }
}
