package com.userzrq.threaddemo;


public class ThreadDemo06 {
    // volatile 修饰共享变量
    // 线程中修改了工作内存中的副本之后，立即将其刷新到主内存；
    // 工作内存中每次读取共享变量时，都去主内存中重新读取，然后拷贝到工作内存。


    // 使用 volatile 修饰的关键字
    // 线程中读取的时候，每次读取都会去主内存中读取共享变量最新的值，然后将其复制到工作内存
    // 线程中修改了工作内存中变量的副本，修改之后会立即刷新到主内存
    public volatile static boolean flag = true;

    public static class T1 extends Thread {

        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("线程 " + this.getName() + " in");
            while (flag) {
                // print内部有synchronized，进入synchronized时会清空工作内存中所有数据副本

                // System.out.println("线程 " + this.getName() + " running");
                ;
            }
            System.out.println("线程 " + this.getName() + " stop");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new T1("t1").start();

        Thread.sleep(100);

        flag = false;
    }
}
