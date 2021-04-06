package com.userzrq.condition.baozipu;

import com.userzrq.threaddemo.baozipu.BaoZi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    List<Baozi> baozis = new ArrayList<>();

    public Consumer(List<Baozi> baozis) {
        this.baozis = baozis;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (baozis) {
                if (baozis.size() == 0) {
                    System.out.println("包子没了,请生产者快点生产");
                    try {
                        baozis.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Baozi baozi = this.baozis.get(0);
                    System.out.println("包子终于好了！ " + Thread.currentThread() + " 要准备开吃了~今天是：" + baozi.getPi() + baozi.getXian());
                    baozis.remove(0);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("包子还有" + baozis.size() + "个");
                    baozis.notifyAll();
                }
            }
        }
    }
}
