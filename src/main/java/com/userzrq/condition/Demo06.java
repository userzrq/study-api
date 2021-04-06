package com.userzrq.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo06 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {
        @Override
        public void run() {
            lock.lock();

            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",start!");
                long r = condition.awaitNanos(TimeUnit.SECONDS.toNanos(5));
                // 返回正数，表示返回时距离超时时间还有多久
                System.out.println(r);

                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
