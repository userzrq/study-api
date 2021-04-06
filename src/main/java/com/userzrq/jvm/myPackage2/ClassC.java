package com.userzrq.jvm.myPackage2;

import com.userzrq.jvm.myPackage1.ClassA;

public class ClassC extends ClassA {

    public void method() {
        ClassA classA = new ClassA();
        classA.var1 = 1;
        // classA.var2 = 1;    // 成员变量 var2没有被ClassC 继承到，无法被访问
        super.var2 = 1;

        ClassC classC = new ClassC();
        classC.var1 = 2;
        classC.var2 = 2;
    }
}
