package com.userzrq.api.appContext.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CostTimeProxy implements MethodInterceptor {

    //
    private Object target;

    public CostTimeProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object target, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long starTime = System.nanoTime();
        Object result = methodProxy.invokeSuper(target, objects);
        long endTime = System.nanoTime();
        System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
        return result;
    }


    public static <T> T creatrProxy(T target) {
        Enhancer enhancer = new Enhancer();
        CostTimeProxy costTimeProxy = new CostTimeProxy(target);
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(costTimeProxy);

        return (T) enhancer.create();

    }
}
