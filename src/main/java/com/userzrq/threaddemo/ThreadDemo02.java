package com.userzrq.threaddemo;

import org.omg.PortableServer.THREAD_POLICY_ID;

public class ThreadDemo02 {

    static Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T1 start!");

                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object");
                    // wait 释放了锁,sleep不释放锁(对象监视器)
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            // T2 获得了object的锁（监视器），T1想对
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start notify T1 Thread");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end");
            }
        }
    }

    public static void main(String[] args) {
        new T1().start();
        new T2().start();
    }
}

