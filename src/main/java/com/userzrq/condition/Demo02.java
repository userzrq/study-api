package com.userzrq.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized / Object.wait() / Object.wait()组合
 * <p>
 * Condition 和 Lock 配合
 * <p>
 * Condition能够支持不响应中断，而通过使用Object方式不支持
 * Condition能够支持多个等待队列（new 多个Condition对象），而Object方式只能支持一个
 * Condition能够支持超时时间的设置，而Object不支持
 */
public class Demo02 {

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "锁释放成功!");
        }
    }


    public static class T2 extends Thread {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备获取锁!");
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "获取锁成功!");
                // 在signal()方法被调用后，系统会从Condition对象的等待队列中唤醒一个线程
                // 一旦线程被唤醒，被唤醒的线程会尝试重新获取锁，一旦获取成功，就可以继续执行了。
                condition.signal();
                System.out.println(System.currentTimeMillis() + "," + this.getName() + " signal!");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + this.getName() + "准备释放锁!");
            } finally {
                lock.unlock();
            }
            System.out.println(System.currentTimeMillis() + "," + this.getName() + "锁释放成功!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo01.T1 t1 = new Demo01.T1();
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        Demo01.T2 t2 = new Demo01.T2();
        t2.setName("t2");
        t2.start();
    }
}
