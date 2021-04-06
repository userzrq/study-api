package com.userzrq.semaphoreTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 释放许可正确的姿势
 */
public class Demo03 {

    static Semaphore semaphore = new Semaphore(1);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            Thread thread = Thread.currentThread();

            boolean acquiresuccess = false;

            try {
                semaphore.acquire();
                acquiresuccess = true;
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",获取许可!");
                // 休眠100秒
                TimeUnit.SECONDS.sleep(100);
                System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",运行结束!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 当线程出现异常时就会执行finally中的代码，造成semaphore的量比之前还要高
                if (acquiresuccess) {
                    semaphore.release();
                }
            }
            System.out.println(System.currentTimeMillis() + "," + thread.getName() + ",当前可用许可数量:" + semaphore.availablePermits());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        t1.start();
        // 休眠1s
        TimeUnit.SECONDS.sleep(1);

        T t2 = new T("t2");
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        T t3 = new T("t3");
        t3.start();

        // 给t2 t3发送中断信号

        // t2 t3 处于acquire()方法的阻塞中
        // 发送中断信号之后，acquire()方法会触发 InterruptedException异常，t2、t3最终没有获取到许可，但是他们都执行了finally中的释放许可的操作
        t2.interrupt();
        t3.interrupt();
    }
}
