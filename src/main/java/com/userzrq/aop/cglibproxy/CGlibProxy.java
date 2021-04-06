package com.userzrq.aop.cglibproxy;

import com.userzrq.aop.dynamicproxy.RealSubject;
import com.userzrq.aop.dynamicproxy.Subject;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author zhangruiqi 10017
 * @create 2021/3/9
 */
public class CGlibProxy {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new MyMethodInterceptor());

        RealSubject proxySubject = (RealSubject) enhancer.create();
        System.out.println(proxySubject.getClass());

        proxySubject.request();
    }
}
