package com.userzrq.designPatterns.singleton;

public class HungrySingleton {

    /**
     * 但饿汉式有一个弊端，那就是即使这个单例不需要使用，它也会在类加载之后立即创建出来
     * 占用一块内存，并增加类初始化时间。
     */

    /**
     * static
     */
    private static HungrySingleton instance = new HungrySingleton();

    /**
     * 私有化构造器 无法从外部实例化此类
     */
    private HungrySingleton() {

    }

    /**
     * 必须通过 getInstance 方法才能获取到唯一的 instance 实例
     * @return
     */
    public static HungrySingleton getInstance() {
        return instance;
    }
}
