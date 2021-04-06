package com.userzrq.threadSafety.synchronizedDemo;

public class Demo03 implements Runnable {
    static Demo03 instance = new Demo03();
    static int i = 0;

    @Override
    public void run() {

        // instance 当前实例对象
        // this 表示调用对象本身，也就是该类的实例对象
        // class对象锁
        synchronized (Demo03.class) {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(Demo03.i);
    }
}
