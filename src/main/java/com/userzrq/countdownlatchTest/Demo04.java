package com.userzrq.countdownlatchTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 2个CountDown结合使用的示例
 */
public class Demo04 {

    public static class T extends Thread {
        // 跑步耗时
        int runCostSeconds;
        CountDownLatch commanderCd;
        CountDownLatch countDown;

        public T(String name, int runCostSeconds, CountDownLatch commanderCd, CountDownLatch countDown) {
            super(name);
            this.runCostSeconds = runCostSeconds;
            this.commanderCd = commanderCd;
            this.countDown = countDown;
        }

        @Override
        public void run() {
            Thread thread = Thread.currentThread();

            try {
                //等待比赛开始的信号
                commanderCd.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long startTime = System.currentTimeMillis();
            System.out.println(startTime + "," + thread.getName() + ",开始跑!");
            try {
                //模拟耗时操作，休眠runCostSeconds秒
                TimeUnit.SECONDS.sleep(this.runCostSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 跑到终点后，参加比赛的人少一个
                countDown.countDown();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + "," + thread.getName() + ",跑步结束,耗时:" + (endTime - startTime));

        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "线程 start");
        CountDownLatch commanderCd = new CountDownLatch(1);
        CountDownLatch countDown = new CountDownLatch(3);

        long startTime = System.currentTimeMillis();
        T t1 = new T("小张", 2, commanderCd, countDown);
        t1.start();
        T t2 = new T("小史", 5, commanderCd, countDown);
        t2.start();
        T t3 = new T("小李", 10, commanderCd, countDown);
        t3.start();

        // 主线程休眠5秒，模拟倒计时
        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis() + ",枪响了,开跑!");
        commanderCd.countDown();

        // 等待所有人都跑完
        countDown.await();
        long endTime = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + "所有人都跑完了,主线程耗时" + (endTime - startTime) + "ms");

    }
}
