package com.userzrq.jvm.loadConsequence;

/**
 * 1． 父类静态成员和静态初始化块 ，按在代码中出现的顺序依次执行
 * 2． 子类静态成员和静态初始化块 ，按在代码中出现的顺序依次执行
 * 3． 父类实例成员和实例初始化块 ，按在代码中出现的顺序依次执行
 * 4． 父类构造方法
 * 5． 子类实例成员和实例初始化块 ，按在代码中出现的顺序依次执行
 * 6． 子类构造方法
 */
public class Base {
    private String name = "公众号";

    public Base() {
        tellName();
        printName();
    }

    public void tellName() {
        System.out.println("Base tell name " + name);
    }

    public void printName() {
        System.out.println("Base print name " + name);
    }
}

class Dervied extends Base {
    String name = "java3y";

    public Dervied() {
        tellName();
        printName();
    }

    // 重写方法
    @Override
    public void tellName() {
        System.out.println("Dervied tell name " + name);
    }

    @Override
    public void printName() {
        System.out.println("Dervied print name " + name);
    }

    public static void main(String[] args) {
        new Dervied();
    }
}
