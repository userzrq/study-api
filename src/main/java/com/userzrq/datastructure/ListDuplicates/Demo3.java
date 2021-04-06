package com.userzrq.datastructure.ListDuplicates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 3.利用HashSet不能添加重复数据的特性 由于HashSet不能保证添加顺序，所以只能作为判断条件保证顺序：
 */
public class Demo3 {
    public static void main(String[] args) {
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));

        removeDuplicate(numbersList);
    }

    public static void removeDuplicate(List<Integer> list) {
        System.out.println("before duplicate: " + list);
        HashSet<Integer> hashSet = new HashSet<>(list.size());
        ArrayList<Integer> result = new ArrayList<>(list.size());

        // set不能保证添加顺序，但是List的遍历顺序是保持了的
        for (Integer integer : list) {
            if (hashSet.add(integer)) {
                result.add(integer);
            }
        }

        list.clear();
        list.addAll(result);
        System.out.println("after duplicate: " + result);
    }


}
