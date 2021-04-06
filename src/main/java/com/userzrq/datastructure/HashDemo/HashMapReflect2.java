package com.userzrq.datastructure.HashDemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HashMapReflect2 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Map<String, Object> map = new HashMap<>();
        map.put("1",123);

        Class<? extends Map> class1 = map.getClass();

        Method capacity = class1.getDeclaredMethod("capacity");
        // 暴力访问私有方法
        capacity.setAccessible(true);

        Method size = class1.getDeclaredMethod("size");

        System.out.println(capacity.invoke(map));
        System.out.println(size.invoke(map));
    }
}
