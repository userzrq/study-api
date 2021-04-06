package com.userzrq.cyclicBarrierTest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 示例4：其中一个人等待中被打断了
 */
public class Demo04 {

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread {

        int sleep;

        public T(String name, int sleep) {
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            // 声明变量的方式
            long startTime = 0, endTime = 0;

            try {
                // 模拟休眠
                TimeUnit.SECONDS.sleep(sleep);
                startTime = System.currentTimeMillis();
                // 调用await()的时候，当前线程将会被阻塞，需要等待其他员工都到达await了才能继续
                System.out.println(this.getName() + "到了!");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();
            System.out.println(this.getName() + ",sleep:" + this.sleep + "等待了" + (endTime - startTime) + " (ms),开始吃饭了！");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <= 10; i++) {
            int sleep = 0;
            if (i == 10) {
                sleep = 10;
            }

            T t = new T("员工" + i, sleep);
            t.start();
            if (i == 5) {
                // 模拟员工5接了个电话，将自己等待吃饭给打断了
                TimeUnit.SECONDS.sleep(1);
                System.out.println(t.getName() + "有点急事，我先开干了");
                t.interrupt();
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }
}
