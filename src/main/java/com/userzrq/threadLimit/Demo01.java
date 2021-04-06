package com.userzrq.threadLimit;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.xml.ws.Service;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Demo01 {

    static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                boolean flag = false;

                try {
                    flag = semaphore.tryAcquire(100, TimeUnit.MICROSECONDS);
                    if (flag) {
                        System.out.println(Thread.currentThread().getName() + ",尝试下单中....");
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName() + ",下单成功");
                    } else {
                        System.out.println(Thread.currentThread().getName() + ",秒杀失败，请稍后重试！");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 添加布尔类型的标记变量，只有真正抢到的才释放信号量
                    if (flag) {
                        semaphore.release();
                    }
                }
            }).start();
        }


    }
}
