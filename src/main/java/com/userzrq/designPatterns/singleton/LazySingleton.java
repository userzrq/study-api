package com.userzrq.designPatterns.singleton;

/**
 * 普通的懒汉式单例 是线程不安全的
 * 如果有多个线程同一时间调用 getInstance 方法，instance 变量可能会被实例化多次
 * 为了保证线程安全，我们需要给判空过程加上锁
 */
public class LazySingleton {

    /**
     * 先声明一个空变量，尚未分配内存空间
     */
    private static volatile LazySingleton instance = null;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {

        if (instance == null) {
            // 初始化
            instance = new LazySingleton();
        }
        return instance;
    }
}
