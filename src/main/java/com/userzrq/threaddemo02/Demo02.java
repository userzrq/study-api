package com.userzrq.threaddemo02;

public class Demo02 {

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
        T1 t1 = new T1("线程1");
        // 将线程1设置成守护线程
        t1.setDaemon(true);
        t1.start();
        System.out.println("主线程结束");
    }

    //结论：当程序中所有的用户线程执行完毕之后，不管守护线程是否结束，系统都会自动退出。

}
