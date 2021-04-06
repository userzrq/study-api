package com.userzrq.datastructure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapDemo2 {

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>();

        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        // map节点的遍历器
        Iterator<Map.Entry<String, Object>> iterator1 = map.entrySet().iterator();

        while (iterator1.hasNext()) {
            // 遍历器中的对象为node节点
            // 只要调用next方法 指针就会向后移动一位
            Map.Entry<String, Object> next = iterator1.next();
            //System.out.println(iterator1.next().getKey() + iterator1.next().getValue());
            System.out.println(next.getKey() + " " + next.getValue());
        }

        // map key值的遍历器
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String next = iterator.next();

            System.out.println("key" + next + map.get(next));
        }
    }
}
