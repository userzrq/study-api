package com.userzrq.threadSafety.synchronizedDemo;

/**
 * synchronized作用于实例对象
 *
 * 注意：add()方法是实例方法，两个线程操作add()时，需要先获取demo2的锁，没有获取到锁的，将等待，直到其他线程释放锁为止。
 *
 * synchronize作用于实例方法需要注意：
 *
 * 实例方法上加synchronized，线程安全的前提是，多个线程操作的是同一个实例，如果多个线程作用于不同的实例，那么线程安全是无法保证的
 *
 * 同一个实例的多个实例方法上有synchronized，这些方法都是互斥的，同一时间只允许一个线程操作同一个实例的其中的一个synchronized方法
 */
public class Demo01 {
    int num = 0;

    public synchronized void add() {
        num++;
    }

    public static class T extends Thread {
        private Demo01 demo01;

        public T(Demo01 demo01) {
            this.demo01 = demo01;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                this.demo01.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo01 demo01 = new Demo01();

        T t1 = new T(demo01);
        T t2 = new T(demo01);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(demo01.num);
    }
}
