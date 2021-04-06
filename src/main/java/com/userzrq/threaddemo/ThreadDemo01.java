package com.userzrq.threaddemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class ThreadDemo01 {

    @Test
    public void rundemo01() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                System.out.println("taco tuesday");
            }
        };
        thread.start();
    }


    @Test
    public void rundemo002() throws InterruptedException {
        Thread thread = new Thread() {

            @Override
            public void run() {
                log.info("start");
                boolean flag = true;
                while (flag) {
                    ;
                }
                log.info("end");
            }
        };
        thread.setName("thread1");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        // 强制停止正在执行的方法
        thread.stop();
        log.info("{}", thread.getState());
        TimeUnit.SECONDS.sleep(1);
        log.info("{}", thread.getState());
    }

    static volatile boolean isStop = false;

    @Test
    public void rundemo003() throws InterruptedException {

        Thread thread = new Thread() {
            @Override
            public void run() {

                while (true) {
                    if (isStop) {
                        System.out.println("I am going to quit");
                        break;
                    }
                }
            }
        };
        thread.setName("threaddemo003");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        isStop = true;
    }


    @Test
    public void rundemo004() throws InterruptedException {

        Thread thread = new Thread() {
            @Override
            public void run() {

                while (true) {
                    if (this.isInterrupted()) {
                        System.out.println("I am going to quit");
                        log.info("{}", this.getState());
                        break;
                    }
                }
            }
        };
        thread.setName("threaddemo003");
        thread.start();
        log.info("{}", thread.getState());
        TimeUnit.SECONDS.sleep(10);
        log.info("{}", thread.getState());
        thread.interrupt();
        log.info("{}", thread.getState());
    }

    @Test
    public void rundemo005() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // sleep方法由于中断而抛出异常之后，线程的中断标志会被清除（置为false），所以在异常中需要执行this.interrupt()方法，将中断标志位置为true
                        this.interrupt();
                        log.info("{}", this.getState());
                    }
                    if(this.isInterrupted()){
                        System.out.println("I am going to quit");
                        break;
                    }
                }
            }
        };

        thread.setName("threaddemo005");
        log.info("{}", thread.getState());  // New
        thread.start();
        log.info("{}", thread.getState());  // RUNNABLE
        TimeUnit.SECONDS.sleep(1);
        log.info("{}", thread.getState());  // RUNNABLE
        thread.interrupt();
        log.info("{}", thread.getState());  // TERMINATED
    }
}
