package com.userzrq.threaddemo02.threadzhongduan;

import java.util.concurrent.TimeUnit;

/**
 * 线程阻塞状态中如何中断？
 */
public class Demo03 {

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                // 循环处理业务
                // 模拟阻塞代码
                // 当线程处于阻塞状态时，调用线程的interrupt()实例方法，
                // 线程内部会触发InterruptedException异常，并且会清除线程内部的中断标志（即将中断标志置为false）
                try {
                    TimeUnit.SECONDS.sleep(1000);

                    // 触发异常时，线程内部的中断标示被清楚 == false
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    // **在catch中又调用了this.interrupt();一次，将中断标志置为true**
                    this.interrupt();
                }

                //
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
    }
}
