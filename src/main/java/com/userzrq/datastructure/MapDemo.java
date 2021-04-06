package com.userzrq.datastructure;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        for (String key : map.keySet()) {
            System.out.println("key:" + key + " , value:" + map.get(key));
        }
    }
}
