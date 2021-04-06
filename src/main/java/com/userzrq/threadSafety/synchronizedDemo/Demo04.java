package com.userzrq.threadSafety.synchronizedDemo;

public class Demo04 {
    /**
     * 作用于当前类的 实例对象
     */
    public synchronized void m1() {
    }

    /**
     * 作用于当前类的 实例对象
     */
    public synchronized void m2() {
    }

    public void m3() {
        synchronized (this) {
        }
    }

    /**
     * 作用于当前类Class对象
     */
    public static synchronized void m4() {
    }

    /**
     * 作用于当前类Class对象
     */
    public static void m5() {
        synchronized (Demo04.class) {

        }
    }

    public static class T extends Thread {
        Demo04 demo04;

        public T(Demo04 demo04) {
            this.demo04 = demo04;
        }

        @Override
        public void run() {
            super.run();
        }
    }

    public static void main(String[] args) {
        Demo04 d1 = new Demo04();
        Thread t1 = new Thread(() -> {
            d1.m1();
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            d1.m2();
        });
        t2.start();

        Thread t3 = new Thread(() -> {
            d1.m2();
        });
        t3.start();


        Demo04 d2 = new Demo04();
        Thread t4 = new Thread(() -> {
            d2.m2();
        });
        t4.start();

        Thread t5 = new Thread(() -> {
            Demo04.m4();
        });
        t5.start();

        Thread t6 = new Thread(() -> {
            Demo04.m5();
        });
        t6.start();
    }
}
