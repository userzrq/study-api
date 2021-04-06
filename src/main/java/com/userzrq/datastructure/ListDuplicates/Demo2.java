package com.userzrq.datastructure.ListDuplicates;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 2.使用java8新特性stream进行List去重
 */
public class Demo2 {

    public static void main(String[] args) {

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));

        List<Integer> collect = numbersList.stream().distinct().collect(Collectors.toList());

        System.out.println("after duplicate: " + collect);
    }
}