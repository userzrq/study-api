package com.userzrq.threadSafety.synchronizedDemo;

/**
 * synchronized作用于静态方法
 */
public class Demo02 {
    static int num = 0;

    /**
     * synchronized 修饰的静态方法
     */
    public static synchronized void m1() {
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            Demo02.m1();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T1 t2 = new T1();
        T1 t3 = new T1();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(Demo02.num);
    }
}
