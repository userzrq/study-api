package com.userzrq.threaddemo;

public class ThreadDemo04 {
    static int num = 0;

    public static class T1 extends Thread {

        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ",start " + this.getName());
            for (int i = 0; i < 10; i++) {
                num++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() + ",end " + this.getName());
        }

        public static void main(String[] args) throws InterruptedException {
            T1 t1 = new T1("t1");
            t1.start();
            // 线程T1需要等待T2、T3完成之后才能继续执行，那么在T1线程中需要分别调用T2和T3的join()方法。

            // 线程T1需要等待T1完成之后才能继续执行，那么在T1线程中需要调用T1的join()方法。
            t1.join();
            System.out.println(System.currentTimeMillis() + ",num " + num);

            // 1597975440637,start t1
            // 1597975442653,end t1
            // 1597975442653,num 10

        }
    }
}
