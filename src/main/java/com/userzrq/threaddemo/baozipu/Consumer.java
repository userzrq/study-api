package com.userzrq.threaddemo.baozipu;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class Consumer implements Runnable {

    //定义变量:包子集合
    ArrayList<BaoZi> baoZi;

    public Consumer(ArrayList<BaoZi> baoZi) {
        super();
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (baoZi) {
                if (baoZi.size() == 0) {
                    try {
                        // 没有包子就无线等待包子
                        baoZi.wait();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                BaoZi bz = this.baoZi.get(0);
                System.out.println("包子终于好了！ " + Thread.currentThread() + " 要准备开吃了~今天是：" + bz.getPi() + bz.getXian());
                BaoZi remove = baoZi.remove(0);

                // 模拟吃包子时间
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("包子已经吃完了。请包子铺生产者赶快生产~");
                log.info("包子还有{}个", baoZi.size());
                baoZi.notifyAll();

            }
        }
    }
}
