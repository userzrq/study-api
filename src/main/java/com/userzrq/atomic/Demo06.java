package com.userzrq.atomic;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 对象的属性修改原子类
 *
 * @author 10017
 */
public class Demo06 {

    static Demo06 demo06 = new Demo06();

    volatile Boolean isInit = Boolean.FALSE;
    /**
     * 三个参数
     * tclass：需要操作的字段所在的类
     * vclass：操作字段的类型
     * fieldName：字段名称
     */
    AtomicReferenceFieldUpdater<Demo06, Boolean> updater = AtomicReferenceFieldUpdater.newUpdater(Demo06.class, Boolean.class, "isInit");


    public void init() throws InterruptedException {
        //isInit为false的时候，才进行初始化，并将isInit采用原子操作置为true
        if (updater.compareAndSet(demo06, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",开始初始化!");
            //模拟休眠3秒
            TimeUnit.SECONDS.sleep(3);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",初始化完毕!");
        } else {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",有其他线程已经执行了初始化!");
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i<5;i++){
            new Thread(()->{
                try {
                    demo06.init();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
