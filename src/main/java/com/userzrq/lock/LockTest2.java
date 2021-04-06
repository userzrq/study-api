package com.userzrq.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest2 {

    private Lock lock = new ReentrantLock();

    /**
     * 尝试获取锁 tryLock() 它表示用来尝试获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
     *
     * @param thread
     */
    public void tryLockTest(Thread thread) {
        if (lock.tryLock()) {

            try {
                System.out.println("线程" + thread.getName() + "获取当前锁");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("线程" + thread.getName() + "发生了异常释放锁");
            } finally {
                System.out.println("线程" + thread.getName() + "执行完毕释放锁");
                lock.unlock();
            }
        } else {
            System.out.println("线程" + thread.getName() + "当前锁被别人占用，我无法获取");
        }
    }

    public static void main(String[] args) {
        LockTest2 lockTest2 = new LockTest2();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest2.tryLockTest(Thread.currentThread());
            }
        }, "thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest2.tryLockTest(Thread.currentThread());
            }
        }, "thread2");

        thread1.start();
        thread2.start();
    }
}
