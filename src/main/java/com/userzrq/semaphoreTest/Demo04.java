package com.userzrq.semaphoreTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 在规定的时间内希望获取许可（尝试获取）
 */
public class Demo04 {

    static Semaphore semaphore = new Semaphore(1);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {

            Thread thread = Thread.currentThread();

            boolean acquireSuccess = false;

            try {
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",尝试获取许可,当前可用许可数量:" + semaphore.availablePermits());
                // 尝试在1秒内获取许可，获取成功返回true，否则返回false
                acquireSuccess = semaphore.tryAcquire(1, TimeUnit.SECONDS);

                if (acquireSuccess) {
                    System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",获取许可成功,当前可用许可数量:" + semaphore.availablePermits());
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",获取许可失败,当前可用许可数量:" + semaphore.availablePermits());
                }
            } catch (InterruptedException e) {
                // 方法可能会响应线程中断信号，调用线程的 interrupt()方法后，会让这些方法触发InterruptedException异常
                e.printStackTrace();
            } finally {
                if (acquireSuccess) {
                    semaphore.release();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        T t2 = new T("t2");
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        T t3 = new T("t3");
        t3.start();
    }
}
