package com.userzrq.aop.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangruiqi 10017
 * @create 2021/3/9
 */
public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object newInstance() {
        /**
         * 代理类的classLoader，字节码文件
         * 委托类实现的接口，JDK 动态代理要实现所有的委托类的接口
         * InvocationHandler 代码增强逻辑接口
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("计算开始时间");
                // 执行目标对象方法,invoke方法中一定要写代理的对象
                method.invoke(target, args);
                System.out.println("计算结束时间");
                return null;
            }
        });
    }

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        System.out.println(realSubject.getClass());

        Subject subject = (Subject) new ProxyFactory(realSubject).newInstance();
        System.out.println(subject.getClass());

        subject.request();
    }
}
