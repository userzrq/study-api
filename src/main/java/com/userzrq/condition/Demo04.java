package com.userzrq.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * await(long time, TimeUnit unit)超时之后自动返回
 */
public class Demo04 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",start");
                boolean b = condition.await(2, TimeUnit.SECONDS);
                // false 表示自动返回,Object.await()并没有这种方法
                System.out.println(b);
                System.out.println(System.currentTimeMillis() + "," + this.getName() + ",end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();
    }
}
