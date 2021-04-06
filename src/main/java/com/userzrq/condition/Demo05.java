package com.userzrq.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo05 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();


    public static class T1 extends Thread {
        @Override
        public void run() {

            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",start!");
                boolean b = condition.await(5, TimeUnit.SECONDS);
                System.out.println(b);
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

        // condition的方法必须在获取对象的锁
//        lock.lock();
//        try {
            condition.signal();
//        } finally {
//            lock.unlock();
//        }
    }
}
