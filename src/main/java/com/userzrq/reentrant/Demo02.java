package com.userzrq.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock的可重入性
 */
public class Demo02 {
    public static int num = 0;

    public static ReentrantLock lock = new ReentrantLock();

    private static void add() {
        // 可重入锁，同一个对象可以多次获取同一把锁
        // 但是获取了几次锁就要解锁几次
        lock.lock();
        lock.lock();
        try {
            num++;
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public static class T extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                Demo02.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new Demo02.T();
        T t2 = new Demo02.T();
        T t3 = new Demo02.T();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(Demo02.num);
    }
}
