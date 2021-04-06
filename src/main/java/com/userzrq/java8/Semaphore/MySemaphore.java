package com.userzrq.java8.Semaphore;


import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class MySemaphore extends Semaphore {

    /**
     * 定义线程安全的、存放Thread类型的队列
     */
    private final ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue();

    public MySemaphore(int permits) {
        super(permits);
    }

    @Override
    public boolean tryAcquire() {
        final boolean acquired = super.tryAcquire();
        if (acquired) {
            this.queue.add(Thread.currentThread());
        }
        return acquired;
    }

    @Override
    public void release(int permits) {
        final Thread currentThread = Thread.currentThread();

        if (queue.contains(currentThread)) {
            super.release(permits);
        }

        queue.remove(currentThread);
    }
}
