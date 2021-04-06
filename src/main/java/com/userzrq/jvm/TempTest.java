package com.userzrq.jvm;

public class TempTest {
    static int t;   // 类变量 静态变量 在堆中开辟空间存储

    public static void main(String[] args) {
        System.out.println(t);

        int t = 1;
        System.out.println("局部变量"+ t);

        TempTest tempTest = new TempTest();
        System.out.println(tempTest.t);
    }

}
