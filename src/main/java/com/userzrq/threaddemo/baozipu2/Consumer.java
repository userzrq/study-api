package com.userzrq.threaddemo.baozipu2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class Consumer implements Runnable {

    //定义变量:包子
    private BaoZi baoZi;

    public Consumer(BaoZi baoZi) {
        super();
        this.baoZi = baoZi;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (baoZi) {
                if (baoZi.isFlag() == false) {
                    try {
                        // 没有包子就无线等待包子
                        baoZi.wait();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                System.out.println("包子终于好了！ " + Thread.currentThread() + " 要准备开吃了~今天是：" + baoZi.getPi() + baoZi.getXian());

                // 模拟吃包子时间
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("包子已经吃完了。请包子铺生产者赶快生产~");
                baoZi.setFlag(false);
                baoZi.notifyAll();

            }
        }
    }
}
