package com.userzrq.datastructure.ListDuplicates;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 1.使用LinkedHashSet删除arraylist中的重复数据
 */
public class Demo1 {

    public static void main(String[] args) {

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));

        System.out.println("before duplicate: " + numbersList);

        Set set = new LinkedHashSet(numbersList);

        ArrayList<Integer> numbersListWithDuplicate = new ArrayList<>(set);

        System.out.println("after duplicate: " + numbersListWithDuplicate);
    }
}
