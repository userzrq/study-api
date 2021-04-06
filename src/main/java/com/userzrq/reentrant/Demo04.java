package com.userzrq.reentrant;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock获取锁的过程是可中断的
 * <p>
 * lockInterruptibly 方法，响应中断
 */
public class Demo04 {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    public static class T extends Thread {
        // 锁的标识
        int lock;

        public T(String name, int lock) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                if (this.lock == 1) {
                    lock1.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lockInterruptibly();
                } else {
                    lock2.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    // 线程t2获取不到lock1的锁，在线程
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                // 响应线程中断状态后进行捕获
                System.out.println("中断标志" + this.isInterrupted());
                e.printStackTrace();
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1", 1);
        T t2 = new T("t2", 2);

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(5);
        t2.interrupt();
    }
}
