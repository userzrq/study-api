package com.userzrq.datastructure.HashDemo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class HashCodeDemo {

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();

        set.add(1);
        set.add(18);
        set.add(2);
        set.add(3);
        set.add(4);

        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
