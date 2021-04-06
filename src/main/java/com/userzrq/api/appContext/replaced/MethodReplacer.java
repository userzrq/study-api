package com.userzrq.api.appContext.replaced;

import java.lang.reflect.Method;

public interface MethodReplacer {

    /**
     * @param obj 被替换方法的目标对象
     * @param method 目标对象的方法
     * @param args 方法的参数
     * @return return value for the method
     */
    Object reimplement(Object obj, Method method, Object[] args) throws Throwable;
}
