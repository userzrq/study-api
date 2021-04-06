package com.userzrq.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class Demo01 {

    private static int num = 0;

    /**
     * 创建锁
     */
    private static ReentrantLock lock = new ReentrantLock();

    private static void add() {
        // 获取锁
        lock.lock();
        try {
            num++;
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static class T extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                Demo01.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T();
        T t2 = new T();
        T t3 = new T();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(Demo01.num);
    }
}
