package com.userzrq.aop.staticproxy;

/**
 * @author zhangruiqi 10017
 * @create 2021/3/9
 */
public class Proxy implements Subject {

    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        // 执行代理逻辑
        System.out.println("卖房前......");

        // 执行目标对象方法
        this.realSubject.request();

        // 执行代理逻辑
        System.out.println("卖房后......");
    }

    public static void main(String[] args) {

        RealSubject realSubject = new RealSubject();

        Proxy proxy = new Proxy(realSubject);

        proxy.request();
    }
}
