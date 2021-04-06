package com.userzrq.threaddemo02.threadzhongduan;

import java.util.concurrent.TimeUnit;

/**
 * 通过一个变量控制线程中断
 */
public class Demo02 {

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                if (this.isInterrupted()) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        t.interrupt();
        // interrupt
        // 线程内部有个中断标志，当调用线程的interrupt()实例方法之后，线程的中断标志会被置为true
    }
}
