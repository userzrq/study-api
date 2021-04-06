package com.userzrq.jvm;

public class Temp {
    int t; //   实例变量

    public static void main(String[] args) {
        int t = 1; //   局部变量 引用实例变量的副本
        // int j;     //   局部变量需要被初始化
        System.out.println("局部变量: " + t);
        // System.out.println("局部变量: " + j);   // 未初始化就会报错

        Temp temp = new Temp();
        System.out.println(temp.t); // 打印的是实例变量的默认值
    }
}
