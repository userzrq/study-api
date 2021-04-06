package com.userzrq.java8.transaction;

import java.util.concurrent.Callable;

public interface Task<T> extends Callable<T> {

    /**
     * 任务回滚方法
     */
    void rollback();
}
