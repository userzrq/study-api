package com.userzrq.datastructure.HashDemo;


import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 多线程环境下使用HashMap用于获取安全的集合
 */

public class SynchronizedDemo {
    public static Map<String, Object> innerMap = new HashMap<>();
    public static Map<String, Object> map = Collections.synchronizedMap(innerMap);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                // innerMap.put(Integer.valueOf(finalI).toString(), Integer.valueOf(finalI));
                map.put(Integer.valueOf(finalI).toString(), Integer.valueOf(finalI));
            });
        }

        Iterator<Map.Entry<String, Object>> innerIterator = innerMap.entrySet().iterator();
        while (innerIterator.hasNext()) {
            System.out.println("innerMap:" + innerIterator.next());
        }

        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println("map:" + iterator.next());
        }
    }
}
