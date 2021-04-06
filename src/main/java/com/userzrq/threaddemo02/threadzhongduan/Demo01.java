package com.userzrq.threaddemo02.threadzhongduan;

import java.util.concurrent.TimeUnit;

/**
 * 通过一个变量控制线程中断
 */
public class Demo01 {

    public volatile static boolean exit = false;

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                if (exit) {
                    break;
                }
                System.out.println("thread running");
            }
        }
    }

    public static void setExit() {
        exit = true;
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();

        TimeUnit.SECONDS.sleep(2);
        setExit();
    }
}
