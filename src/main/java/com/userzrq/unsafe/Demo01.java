package com.userzrq.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Demo01 {

    static Unsafe unsafe;

    static {
        try {
            // 通过反射获取Unsafe实例
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(unsafe);
    }
}
