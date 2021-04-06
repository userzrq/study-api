package com.userzrq.jvm;

public class StringInternDemo {

    public static void main(String[] args) {

        String s2 = "1";
        // 发现字符串常量池中已经存在"1"字符串对象，直接返回字符串常量池中对堆的引用(但没有接收)-->此时s引用还是指向着堆中的对象
        // s 的指针仍旧指向对象而非常量
        String s = new String("1");
        // String.intern()方法 有返回值
        String intern = s.intern();     // 返回的intern 是字符串常量池中的对于 “1”的引用，引用地址与s2相同
        System.out.println(intern == s2); // true


        String s3 = new String("he") + new String("llo");
        String s4 = new String("h") + new String("ello");
        String s5 = "";

        s5 = s3.intern();
        System.out.println(s3 == s5);
    }
}
