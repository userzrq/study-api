package com.userzrq.datastructure.HashDemo;

import java.util.HashMap;

public class NewCapacityDemo {

    static float load_factory = 0.75f;
    static int entry_count = 11;

    /**
     * HashMap底层的table在entry_count>table_size*load_factory时会扩容。
     * 为了不让HashMap扩容，需要table_size >= entry_count / load_factor。
     * 公式((float)s / loadFactor) + 1.0F中的size是使用float计算的，+1.0F是因为((float)s / loadFactor)使用float计算，在转换成整数的时候会进行舍入，
     * 为了保证最终计算出来的size足够大不至于触发扩容，所以进行了+ 1.0F操作。
     */
    public static void main(String[] args) {
        float ft = ((float) entry_count / load_factory) + 1.0f;
        System.out.println(ft);

        int t = (int)ft;    // 15
        System.out.println(t);

        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("1",123);
        map1.put("2",234);

        HashMap<String, Object> map2 = new HashMap<>(map1);

        System.out.println(map2);
    }
}
