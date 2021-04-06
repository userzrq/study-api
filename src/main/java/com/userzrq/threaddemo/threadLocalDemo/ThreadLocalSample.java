package com.userzrq.threaddemo.threadLocalDemo;

public class ThreadLocalSample {

    private static ThreadLocal<Integer> firstNum = new ThreadLocal<>();
    private static ThreadLocal<Integer> secondNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 21;
        }
    };

    private static ThreadLocal<Integer> thirdNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 31;
        }
    };

    public static void main(String[] args) {
        System.out.println("getNum : " + firstNum.get());
        firstNum.set(11);
        System.out.println("getNum : " + firstNum.get());
        System.out.println("getNum : " + secondNum.get());
        System.out.println("getNum : " + thirdNum.get());
    }
}
