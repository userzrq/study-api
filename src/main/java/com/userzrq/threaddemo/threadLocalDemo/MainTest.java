package com.userzrq.threaddemo.threadLocalDemo;

public class MainTest {

    public static void main(String[] args) {
        Long l1 = 1L << 32;     // 4294967296
        System.out.println(l1);

        Long l2 = (long) ((1L << 31) * (Math.sqrt(5) - 1));     // 2654435769

        System.out.println(l2);
        System.out.println(l1 - l2);
    }
}
