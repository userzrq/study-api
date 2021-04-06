package com.userzrq.designPatterns.Observers;

/**
 * @author zhangruiqi 10017
 * @create 2021/4/2
 */
public class ObserverTest {

    public static void main(String[] args) {

        // 创建一个观察者目标
        JavaStackObservable javaStackObservable = new JavaStackObservable();

        // 为观察目标添加观察者，相当于监听的感觉
        javaStackObservable.addObserver(new ReaderObserver("小张"));
        javaStackObservable.addObserver(new ReaderObserver("小明"));
        javaStackObservable.addObserver(new ReaderObserver("小爱"));

        // 发表文章
        javaStackObservable.publish("什么是观察者模式?");
    }
}
