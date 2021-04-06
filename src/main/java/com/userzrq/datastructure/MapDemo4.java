package com.userzrq.datastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapDemo4 {

    String[] arr;
    String arr1[];

    char value[];

    public static void main(String[] args) {
        char a = 'a';
        Map<String, Object> map = new HashMap<>();

        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        Collection<Object> values = map.values();

        for (Object value : values) {
            System.out.println(value);
        }

        System.out.println((int) a);
    }
}
