package com.userzrq.threadSafety;

/**
 * 造成线程安全问题的主要诱因：
 * 一是存在共享数据(也称临界资源)
 * 二是存在多条线程共同操作共享数据
 */
public class Demo01 {

    static int num = 0;

    /**
     * 需要保证同一时刻有且只有一个线程在操作共享数据，其他线程必须等待处理完成再进行。
     * 互斥锁，即能达到互斥访问目的的锁，也就是说当一个共享数据被当前正在访问的线程加上互斥锁后，在同一个时刻，其他线程只能处于等待的状态，直到当前线程处理完毕释放该锁。
     * 关键字 synchronized可以保证在同一个时刻，只有一个线程可以执行某个方法或者某个代码块(主要是对方法或者代码块中存在共享数据的操作)，
     * 同时我们还应该注意到synchronized另外一个重要的作用，synchronized可保证一个线程的变化(主要是共享数据的变化)被其他线程所看到（保证可见性，完全可以替代volatile功能），这点确实也是很重要的。
     */
    public static synchronized void m1() {
        for (int i = 0; i < 10000; i++) {
            num++;
        }
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            Demo01.m1();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T1 t2 = new T1();
        T1 t3 = new T1();

        t1.start();
        t2.start();
        t3.start();

        // 等待3个线程结束打印num
        t1.join();
        t2.join();
        t3.join();

        // 期望的结果是30000
        System.out.println(Demo01.num);
    }
}
