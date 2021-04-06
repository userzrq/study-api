package com.userzrq.threaddemo02;

import java.util.concurrent.TimeUnit;

/**
 * Thread parent = currentThread();
 * this.daemon = parent.isDaemon();
 * dameon的默认值为为父线程的daemon(boolean)
 * 也就是说，父线程如果为用户线程，子线程默认也是用户现场，父线程如果是守护线程，子线程默认也是守护线程。
 */
public class Demo04 {

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + ".dameon." + this.isDaemon());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ".dameon." + Thread.currentThread().isDaemon());

        T1 t1 = new T1("t1");
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println(this.getName() + ".dameon." + this.isDaemon());
                T1 t3 = new T1("t3");
                t3.start();
            }
        };

        t2.setName("t2");
        t2.setDaemon(true);
        // 运行结果
        // t2.dameon.true
        // t3.dameon.true
        t2.start();
        TimeUnit.SECONDS.sleep(2);
    }
}
