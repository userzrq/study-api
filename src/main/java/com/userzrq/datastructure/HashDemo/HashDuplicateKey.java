package com.userzrq.datastructure.HashDemo;

import java.util.HashMap;
import java.util.Map;

public class HashDuplicateKey {

    public static void main(String[] args) {
        int newCap, newThr = 0;
        Map<Integer, String> map = new HashMap<>();

        map.put(3, "insert value 1");

        String put2 = map.put(3, "insert value 2");

        System.out.println(newThr);
        System.out.println(put2);   // insert value 1
    }
}
