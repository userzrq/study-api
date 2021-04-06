package com.userzrq.jvm.Class;

public class ClassTest {

    public static void main(String[] args) {

        System.out.println(int.class.getName());
        System.out.println(char.class.getName());
        System.out.println(short.class.getName());
        System.out.println(long.class.getName());
        System.out.println(byte.class.getName());
        System.out.println(float.class.getName());
        System.out.println(double.class.getName());
        System.out.println(boolean.class.getName());
        System.out.println(void.class.getName());
        System.out.println(char[].class.getName());     // [C
        System.out.println(char[][].class.getName());   // [[C
    }
}
