package com.userzrq.threaddemo.baozipu;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.ArrayList;

public class Producer implements Runnable {

    //定义变量:包子集合
    ArrayList<BaoZi> baoZi;


    public Producer(ArrayList<BaoZi> baoZi) {
        super();
        this.baoZi = baoZi;
    }


    @Override
    public void run() {
        int count = 1;
        while (true) {
            synchronized (baoZi) {
                if (baoZi.size() > 0) {
                    // 有包子，进入无限等待
                    try {
                        baoZi.wait();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                BaoZi bz = new BaoZi();
                if (count % 2 == 0) {
                    bz.setPi("薄皮");
                    bz.setXian("牛肉馅");
                } else {
                    bz.setPi("厚皮");
                    bz.setXian("白菜馅");
                }

                System.out.println("我们作为生产者，正在加急生产第" + count + "个包子。请耐心等待");
                count++;
                baoZi.add(bz);
                try {
                    // 模拟造包子时间
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("包子已经做好了哟！今天的包子是：" + bz.getPi() + bz.getXian());
                // 唤醒别的同一个所对象的线程
                baoZi.notifyAll();
            }
        }
    }
}
