package com.userzrq.designPatterns.singleton;

/**
 * 当多个线程调用 getInstance 时，每次都需要执行 synchronized 同步化方法
 * 这样会严重影响程序的执行效率
 * 所以更好的做法是在同步化之前，再加上一层检查
 */
public class SynchronizedLazySingleton {

    private static SynchronizedLazySingleton instance = null;

    private SynchronizedLazySingleton() {

    }

    public static SynchronizedLazySingleton getInstance() {
        // 双检锁方式实现的线程安全的单例模式
        // 先判断示例为不为空再进同步方法

        if (instance == null) {
            synchronized (SynchronizedLazySingleton.class) {
                if (instance == null) {
                    instance = new SynchronizedLazySingleton();
                }
            }
        }

        return instance;
    }
}
