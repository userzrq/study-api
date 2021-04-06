package com.userzrq.jvm.loadConsequence;

public class TestClassLoad {
    public static void main(String[] args) {
        //输出结果  Test SupClass Subclass
        //第一个
        System.out.println(Test.A);
        //第二个
        System.out.println(SubClass.value);
        //第三个
        Test[] testArr = new Test[10];
        //第四个
        Test t = new Test();
        //第五个
        SubClass sub = new SubClass();
        SupClass su1 = new SubClass();
    }
}

class Test {
    static {
        System.out.println("static is running...");
    }

    public static final String A = "123";
}


class SupClass {
    static {
        System.out.println("Sup is Running ...");
    }

    {
        System.out.println("sup source block is running ...");
    }

    public SupClass() {
        System.out.println("sup constructor is running...");
    }

    public static int value = 1;
}


class SubClass extends SupClass {
    static {
        System.out.println("Sub is running ...");
    }

    {
        System.out.println("sub source block is running ...");
    }

    public SubClass() {
        System.out.println("sub constructor is running...");
    }
}
