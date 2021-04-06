package com.userzrq.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock实现公平锁
 */
public class Demo03 {

    public static int num = 0;

    /**
     * 创建锁（公平锁）
     */
    public static ReentrantLock fairLock = new ReentrantLock(true);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                fairLock.lock();
                try {
                    System.out.println(this.getName() + "获取到了锁");
                } finally {
                    fairLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        T t2 = new T("t2");
        T t3 = new T("t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        // 当设置锁为非公平的，同一线程可能会先后争取到锁
        // t2线程先后两次获得了锁
        // t2获取到了锁
        // t3获取到了锁
        // t3获取到了锁
        // t3获取到了锁
        // t3获取到了锁
        // t3获取到了锁
        // t2获取到了锁
        // t2获取到了锁
        // t2获取到了锁
        // t2获取到了锁
        // t1获取到了锁
        // t1获取到了锁
        // t1获取到了锁
        // t1获取到了锁
        // t1获取到了锁
    }
}
