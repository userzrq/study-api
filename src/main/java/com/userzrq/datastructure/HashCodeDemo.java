package com.userzrq.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashCodeDemo {

    String name;

    public HashCodeDemo(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        // this 值的是方法调用者
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HashCodeDemo test1 = (HashCodeDemo) obj;
        return Objects.equals(name, test1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public static void main(String[] args) {
        Map<HashCodeDemo, String> map = new HashMap<>(4);

        map.put(new HashCodeDemo("hello"), "hello");

        // 没有重写equals和hashcode方法,JDK 默认使用 Objective 类的 hashcode 方法
        // 返回的是一个虚拟内存地址，而每个对象的虚拟地址都是不同的
        String hello = map.get(new HashCodeDemo("hello"));
        System.out.println(hello);
    }
}
