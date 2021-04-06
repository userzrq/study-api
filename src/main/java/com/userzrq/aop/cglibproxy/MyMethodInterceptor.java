package com.userzrq.aop.cglibproxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhangruiqi 10017
 * @create 2021/3/9
 */
public class MyMethodInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("目标类增强前！！！");

        Object object = methodProxy.invokeSuper(obj, args);
        System.out.println("目标类增强后！！！");
        return object;
    }
}
