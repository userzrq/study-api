package com.userzrq.excutors;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 买新房了，然后在网上下单买冰箱、洗衣机，电器商家不同，所以送货耗时不一样，
 * 然后等他们送货，快递只愿送到楼下，然后我们自己将其搬到楼上的家中。
 */
public class Demo10 {

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

        //创建一个线程池，用来异步下单
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //异步下单购买冰箱
        Future<GoodModel> bxFuture = executorService.submit(buyGoods("冰箱", 5));
        //异步下单购买洗衣机
        Future<GoodModel> xyjFuture = executorService.submit(buyGoods("洗衣机", 2));
        //关闭线程池
        executorService.shutdown();

        // 先到的先送上去
        // 但事实是不知道哪个耗时更长的
        //等待洗衣机送到
        GoodModel xyjGoodModel = xyjFuture.get();
        //将洗衣机搬上楼
        moveUp(xyjGoodModel);
        //等待冰箱送到
        GoodModel bxGoodModel = bxFuture.get();
        //将冰箱搬上楼
        moveUp(bxGoodModel);

        long et = System.currentTimeMillis();
        System.out.println(et + "货物已送到家里咯，哈哈哈！");
        System.out.println("总耗时:" + (et - st));
    }
}
