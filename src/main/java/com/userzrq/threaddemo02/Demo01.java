package com.userzrq.threaddemo02;

public class Demo01 {

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + "开始执行," + (this.isDaemon() ? "我是守护进程" : "我是用户进程"));
            while (true) ;
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1("子线程1");
        t1.start();
        // 可以看到主线程已经结束了，但是程序无法退出，
        // 原因：子线程1是用户线程，内部有个死循环，一直处于运行状态，无法结束。
        // 用户线程一直处于运行状态，无法结束
        System.out.println("主线程结束");
    }
}
