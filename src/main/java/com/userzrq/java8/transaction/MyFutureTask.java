package com.userzrq.java8.transaction;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 任务基类
 * 复写FutureTask,来获取任务中的callable
 *
 * @param <T>
 */
public class MyFutureTask<T> extends FutureTask<T> {

    public Callable<T> callable;

    public MyFutureTask(Callable<T> callable) {
        super(callable);
        this.callable = callable;
    }

    public MyFutureTask(Runnable runnable, T result) {
        super(runnable, result);
    }

    public Callable<T> getCallable() {
        return callable;
    }
}
