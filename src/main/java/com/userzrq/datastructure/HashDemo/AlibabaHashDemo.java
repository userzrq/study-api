package com.userzrq.datastructure.HashDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * 设置一个合理的初始化值可以有效提高性能
 */
public class AlibabaHashDemo {

    public static void main(String[] args) {
        int aHundredMillion = 10000000;

        Map<Integer, Integer> map = new HashMap<>();

        long s1 = System.currentTimeMillis();
        for (int i = 0; i < aHundredMillion; i++) {
            map.put(i, i);
        }
        long s2 = System.currentTimeMillis();

        System.out.println("未初始化容量，耗时 ： " + (s2 - s1));


        Map<Integer, Integer> map1 = new HashMap<>(aHundredMillion / 2);

        long s5 = System.currentTimeMillis();
        for (int i = 0; i < aHundredMillion; i++) {
            map1.put(i, i);
        }
        long s6 = System.currentTimeMillis();

        System.out.println("初始化容量5000000，耗时 ： " + (s6 - s5));


        Map<Integer, Integer> map2 = new HashMap<>(aHundredMillion);

        long s3 = System.currentTimeMillis();
        for (int i = 0; i < aHundredMillion; i++) {
            map2.put(i, i);
        }
        long s4 = System.currentTimeMillis();

        System.out.println("初始化容量为10000000，耗时 ： " + (s4 - s3));
    }
}

