package com.userzrq.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.首先synchronized是java内置关键字，在jvm层面，Lock是个java类；
 * <p>
 * 2.synchronized无法判断是否获取锁的状态，Lock可以判断是否获取到锁；
 * <p>
 * 3.synchronized会自动释放锁(a 线程执行完同步代码会释放锁 ；b 线程执行过程中发生异常会释放锁)，Lock需在finally中手工释放锁（unlock()方法释放锁），否则容易造成线程死锁；
 * <p>
 * 4.用synchronized关键字的两个线程1和线程2，如果当前线程1获得锁，线程2线程等待。如果线程1阻塞，线程2则会一直等待下去，而Lock锁就不一定会等待下去，如果尝试获取不到锁，线程可以不用一直等待就结束了；
 * <p>
 * 5.synchronized的锁可重入、不可中断、非公平，而Lock锁可重入、可判断、可公平（两者皆可）
 * <p>
 * 6.Lock锁适合大量同步的代码的同步问题，synchronized锁适合代码少量的同步问题。
 *
 * @author 10017
 */
public class LockTest3 {
    private Lock lock = new ReentrantLock();

    public void tryLockParamTest(Thread thread) throws InterruptedException {
        if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
            try {
                System.out.println("线程" + thread.getName() + "获取当前锁");
                Thread.sleep(4000);
            } catch (Exception e) {
                System.out.println("线程" + thread.getName() + "发生了异常释放锁");
            } finally {
                System.out.println("线程" + thread.getName() + "执行完毕释放锁");
                lock.unlock();
            }
        } else {
            System.out.println("线程" + Thread.currentThread().getName() + "当前锁被别人占用,等待3s后仍无法获取,放弃");
        }

    }

    public static void main(String[] args) {
        LockTest3 lockTest = new LockTest3();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockTest.tryLockParamTest(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockTest.tryLockParamTest(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
