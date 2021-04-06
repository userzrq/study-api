package com.userzrq.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo3 {

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>();

        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        Set<Map.Entry<String, Object>> entries = map.entrySet();

        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey() + entry.getValue());

        }
    }
}
