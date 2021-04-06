package com.userzrq.threaddemo02;

import java.util.concurrent.TimeUnit;

public class Demo03 {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        // 不能在线程start后再设置守护线程
        t1.setDaemon(true);
    }
}
