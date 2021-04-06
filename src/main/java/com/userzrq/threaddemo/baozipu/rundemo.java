package com.userzrq.threaddemo.baozipu;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class rundemo {
    public static void main(String[] args) {

        ArrayList<BaoZi> baOZi = new ArrayList<>();
        Producer bp = new Producer(baOZi);//创建包子铺对象
        Consumer ch = new Consumer(baOZi);//创建吃货对象

        ExecutorService pool = Executors.newFixedThreadPool(2);//创建有4个线程的线程池对象
        pool.submit(bp);//让线程池执行包子铺线程
        pool.submit(ch);//让线程池执行吃货线程\


    }
}
