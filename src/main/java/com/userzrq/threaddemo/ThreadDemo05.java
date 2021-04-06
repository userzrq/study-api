package com.userzrq.threaddemo;


import java.util.concurrent.TimeUnit;

public class ThreadDemo05 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // sleep方法由于中断而抛出异常之后，线程的中断标志会被清除（置为false），所以在异常中需要执行this.interrupt()方法，将中断标志位置为true
                        this.interrupt();
                    }
                    if (this.isInterrupted()) {
                        System.out.println(" I am going to quit");
                        break;
                    }
                }
            }
        };

        thread.setName("thread 01");
        thread.start();
        TimeUnit.SECONDS.sleep(10);

        // interrupt 会终止线程的sleep状态，
        thread.interrupt();
    }
}
