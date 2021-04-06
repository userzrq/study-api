package com.userzrq.excutors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Demo11 {

    static class GoodModel {
        // 商品名称
        String name;
        // 购物开始时间
        long startTime;
        // 送达时间
        long endTime;

        public GoodModel(String name, long startTime, long endTime) {
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return name + "，下单时间[" + this.startTime + "," + endTime + "]，耗时:" + (this.endTime - this.startTime);
        }
    }

    /**
     * 将商品搬上楼
     *
     * @param goodModel
     * @throws InterruptedException
     */
    static void moveUp(GoodModel goodModel) throws InterruptedException {
        // 休眠5秒，模拟搬上楼耗时
        TimeUnit.SECONDS.sleep(5);
        System.out.println("将商品搬上楼，商品信息:" + goodModel);
    }

    /**
     * 模拟下单
     *
     * @param name     商品名称
     * @param costTime 耗时
     * @return
     */
    static Callable<GoodModel> buyGoods(String name, long costTime) {
        return () -> {
            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "购买" + name + "下单!");
            //模拟送货耗时
            try {
                TimeUnit.SECONDS.sleep(costTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(startTime + name + "送到了!");
            return new GoodModel(name, startTime, endTime);
        };
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long st = System.currentTimeMillis();
        System.out.println(st + "开始购物!");

        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // 创建ExecutorCompletionService对象
        ExecutorCompletionService<GoodModel> completionService = new ExecutorCompletionService<>(executorService);
        //异步下单购买冰箱和洗衣机
        completionService.submit(buyGoods("冰箱", 5));
        completionService.submit(buyGoods("洗衣机", 2));

        executorService.shutdown();


        int goodCount = 2;
        for (int i = 0; i < goodCount; i++) {
            // 阻塞式获取
            GoodModel goodModel = completionService.take().get();
            moveUp(goodModel);
        }

        long et = System.currentTimeMillis();
        System.out.println(et + "货物已送到家里咯，哈哈哈！");
        System.out.println("总耗时:" + (et - st));
    }
}
