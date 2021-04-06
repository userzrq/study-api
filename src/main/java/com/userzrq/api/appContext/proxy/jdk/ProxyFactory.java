package com.userzrq.api.appContext.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * 用于生成代理对象的类
 *
 * @author zhangruiqi 10017
 * @create 2021/3/19
 */
public class ProxyFactory implements InvocationHandler {

    /**
     * 需要被代理的对象
     */
    private Object object;

    /**
     * 构造器传入
     *
     * @param object
     */
    public ProxyFactory(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(object, args);
    }

    /**
     * 生成并返回代理对象的方法
     *
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                this);
    }
}
