package com.userzrq.semaphoreTest;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Demo01 {
    static Semaphore semaphore = new Semaphore(2);

    public static class T extends Thread {

        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            Thread thread = Thread.currentThread();

            try {
                // 为获取到许可的线程会阻塞在 acquire()方法上，直到获取到许可才能继续。
                semaphore.acquire();
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",获取许可!");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",释放许可!");
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new T("t" + i).start();
        }
    }
}
