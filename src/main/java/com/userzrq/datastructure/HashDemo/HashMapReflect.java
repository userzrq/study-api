package com.userzrq.datastructure.HashDemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HashMapReflect {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, String> map1 = new HashMap<String, String>(9);

        Map<String, String> map2 = new HashMap<String, String>(9);
        map1.put("超哥真帅", "超哥是我男神");

        Class<? extends Map> class1 = map1.getClass();
        Class<? extends Map> class2 = map2.getClass();

        Method capacity = class1.getDeclaredMethod("capacity");
        capacity.setAccessible(true);
        System.out.println("Map初始化容量" + capacity.invoke(map1));

        System.out.println(class1.equals(class2));
    }
}
