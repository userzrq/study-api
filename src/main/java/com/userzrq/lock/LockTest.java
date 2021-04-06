package com.userzrq.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private Lock lock = new ReentrantLock();

    /**
     * 使用完毕释放后其他线程才能获取锁
     *
     * @param thread
     */
    public void lockTest(Thread thread) {
        lock.lock();//获取锁
        try {
            System.out.println("线程" + thread.getName() + "获取当前锁");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("线程" + thread.getName() + "发生了异常释放锁");
        } finally {
            System.out.println("线程" + thread.getName() + "执行完释放锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.lockTest(Thread.currentThread());
            }
        }, "thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.lockTest(Thread.currentThread());
            }
        }, "thread2");

        thread1.start();
        thread2.start();
    }
}
