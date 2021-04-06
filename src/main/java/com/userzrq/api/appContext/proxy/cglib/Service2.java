package com.userzrq.api.appContext.proxy.cglib;

public class Service2 {
    public void m1() {
        System.out.println("我是m1方法");
        this.m2(); //@1在m1方法中调用了m2方法。
    }

    public void m2() {
        System.out.println("我是m2方法");
    }
}
