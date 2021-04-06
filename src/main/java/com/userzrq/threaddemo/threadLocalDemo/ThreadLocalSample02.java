package com.userzrq.threaddemo.threadLocalDemo;

public class ThreadLocalSample02 {
    /**
     * 如果我们需要一个和某个线程绑定的 Integer 值的话，我们可以这样
     */
    private static ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();

    private static ThreadLocal<Integer> threadLocal =  ThreadLocal.withInitial(()->1);

    public static void main(String[] args) {
        threadLocalValue.set(1);
        Integer integer = threadLocalValue.get();


    }
}
