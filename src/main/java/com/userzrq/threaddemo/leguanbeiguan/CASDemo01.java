package com.userzrq.threaddemo.leguanbeiguan;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 10017
 */
public class CASDemo01 {
    /**
     * ------------------------- 乐观锁的调用方式 -------------------------
     */
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        //执行自增1
        atomicInteger.incrementAndGet();
    }

    /**
     * ------------------------- 悲观锁的调用方式 -------------------------
     */
    public synchronized void testMethod() {

    }

    private ReentrantLock lock = new ReentrantLock();

    public void modifyPulbicResources() {
        lock.lock();

        lock.unlock();
    }

}
