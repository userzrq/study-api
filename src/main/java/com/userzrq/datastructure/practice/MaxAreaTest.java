package com.userzrq.datastructure.practice;

/**
 * 给定n个非负整数a1，a2，…，an，其中每个数字表示坐标(i, ai)处的一个点。
 * 以（i，ai）和（i，0）（i=1,2,3...n）为端点画出n条直线。
 * 你可以从中选择两条线与x轴一起构成一个容器，最大的容器能装多少水？
 */
public class MaxAreaTest {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int i = MaxArea(arr);
        System.out.println(i);
    }

    public static int MaxArea(int[] height) {
        int maxArea = 0;
        int area = 0;
        for (int i = 0; i < height.length; i++) {

            for (int j = i + 1; j < height.length; j++) {
                if (height[i] >= height[j]) {
                    area = height[j] * (j - i);
                } else {
                    area = height[i] * (j - i);
                }

                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }
}
