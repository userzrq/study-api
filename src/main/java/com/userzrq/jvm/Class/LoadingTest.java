package com.userzrq.jvm.Class;

public class LoadingTest {
    public static void main(String[] args) {
        System.out.println("inside main");
        Dog dog = new Dog();
        // getClass() 方法可以理解为 “我是由哪个类创建的对象”
        Class<? extends Dog> aClass = dog.getClass();       // class com.userzrq.jvm.Class.Dog
        System.out.println(aClass);
        System.out.println("after creating Dog");
        try {
            // 通过全类型加载
            Class cat = Class.forName("com.userzrq.jvm.Class.Cat");
        } catch (ClassNotFoundException e) {
            System.out.println("Couldn't find Cat");
        }
        System.out.println("finish main");
    }
}

class Cat {
    static {
        System.out.println("Loading Cat");
    }
}

class Dog {
    static {
        System.out.println("Loading Dog");
    }
}
