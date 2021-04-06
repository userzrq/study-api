package com.userzrq.jvm.myPackage1;

public class ClassA {
    // 公开
    public int var1;
    // 保护
    protected int var2;
    // 默认   只向同包同类放开
    int var3;
    // 私有
    private int var4;

    public void method() {
        var1 = 1;
        var2 = 1;
        var3 = 1;
        var4 = 1;

        ClassA classA = new ClassA();
        classA.var1 = 1;
        classA.var2 = 1;
        classA.var3 = 1;
        classA.var4 = 1;
    }
}
