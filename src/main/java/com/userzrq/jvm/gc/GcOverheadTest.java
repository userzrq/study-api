package com.userzrq.jvm.gc;

import java.util.Map;
import java.util.Random;

public class GcOverheadTest {

    public static void main(String[] args) {

        Map map = System.getProperties();

        Random random = new Random();
        while (true) {
            map.put(random.nextInt(), "value");
        }
    }
}
