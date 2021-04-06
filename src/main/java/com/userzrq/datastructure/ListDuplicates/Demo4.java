package com.userzrq.datastructure.ListDuplicates;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 4.利用List的contains方法循环遍历,重新排序,只添加一次数据,避免重复：
 */
public class Demo4 {

    public static void main(String[] args) {
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println("before duplicate: " + numbersList);

        ArrayList<Integer> result = new ArrayList<>();
        for (Integer integer : numbersList) {
            if (result.contains(integer)) {
                continue;
            } else {
                result.add(integer);
            }
        }
        System.out.println("after duplicate: " + result);

    }
}
