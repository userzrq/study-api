package com.userzrq.threaddemo.baozipu2;

import java.util.ArrayList;

public class Producer implements Runnable {

    //定义变量:包子
    private BaoZi baoZi;


    public Producer(BaoZi baoZi) {
        super();
        this.baoZi = baoZi;
    }


    @Override
    public void run() {
        int count = 1;
        while (true) {
            synchronized (baoZi) {
                if (baoZi.isFlag() == true) {
                    // 有包子，进入无限等待
                    try {
                        baoZi.wait();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                if (count % 2 == 0) {
                    baoZi.setPi("薄皮");
                    baoZi.setXian("牛肉馅");
                } else {
                    baoZi.setPi("厚皮");
                    baoZi.setXian("白菜馅");
                }

                System.out.println("我们作为生产者，正在加急生产第" + count + "个包子。请耐心等待");
                count++;
                try {
                    // 模拟造包子时间
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("包子已经做好了哟！今天的包子是：" + baoZi.getPi() + baoZi.getXian());
                // 唤醒别的同一个所对象的线程
                baoZi.setFlag(true);
                baoZi.notifyAll();
            }
        }
    }
}
