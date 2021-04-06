package com.userzrq.threaddemo;


public class ThreadDemo03 {
    static Object object = new Object();

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("in " + this.getName());
                Thread.currentThread().suspend();
                System.out.println("thread resume " + this.getName());
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        T1 t1 = new T1("t1");
        t1.start();
        Thread.sleep(100);
        T1 t2 = new T1("t2");
        // t2线程启动，但是t1线程并未释放锁，t2线程抢不到监视器
        t2.start();
        t1.resume();

        // 为什么 t2.resume(); 会先于t2.start(); 被调用
        Thread.sleep(100);
        // resume 如果在 suspend 之前被执行，将不会生效，被挂起的线程将会被一直挂起，且不释放锁
        t2.resume();
        t1.join();
        t2.join();
    }

}
