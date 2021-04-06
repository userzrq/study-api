package com.userzrq.condition.baozipu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    List<Baozi> baozis = new ArrayList<>();

    public Producer(List<Baozi> baozis) {
        this.baozis = baozis;
    }

    @Override
    public void run() {
        int count = 1;
        // 只要线程启动就一直执行该方法
        while (true) {

            synchronized (baozis) {
                // 有包子我就不做了，我等待
                if (baozis.size() > 0) {
                    try {
                        baozis.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Baozi baozi;
                // 如果没有包子就开始生产包子
                if (count % 2 == 0) {
                    baozi = new Baozi("薄皮", "芹菜鲜肉馅");
                } else {
                    baozi = new Baozi("厚皮", "猪肉白菜馅");
                }
                count++;
                System.out.println("生产者正在生产今天第" + count + "个包子,请稍等");
                baozis.add(baozi);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 唤醒
                baozis.notifyAll();
            }
        }

    }
}
