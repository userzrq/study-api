package com.userzrq.aop.dynamicproxy;

/**
 * @author zhangruiqi 10017
 * @create 2021/3/9
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("卖房");
    }
}
