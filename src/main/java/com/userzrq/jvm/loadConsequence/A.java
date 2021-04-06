package com.userzrq.jvm.loadConsequence;

import lombok.Data;

@Data
public class A {

    public static String a = printStr("父类的静态变量");
    public String st = printStr("父类的非静态变量");

    static {
        printStr("父类的静态代码块");
    }

    {
        printStr("父类的非静态代码块");
    }

    public A() {
        printStr("父类构造方法");
        System.out.println(this.toString());
    }


    public static String printStr(String str) {
        System.out.println(str);
        return str;
    }


}
