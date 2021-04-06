package com.userzrq.datastructure.practice;


import java.util.Objects;

/**
 * 假设你有一个数组，其中第i个元素表示某只股票在第i天的价格。
 * 设计一个算法来寻找最大的利润。
 * 你可以完成任意数量的交易(例如，多次购买和出售股票的一股)。
 * 但是，你不能同时进行多个交易(即，你必须在再次购买之前卖出之前买的股票)。
 * <p>
 * <p>
 * 只需要一个持续增长的阶段即可
 */
public class Solution {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 9, 6, 9, 1, 7, 1, 1, 5, 9, 9, 9};
        int i = maxProfit(arr);
        System.out.println(i);
    }

    public static int maxProfit(int[] prices) {
        if (Objects.isNull(prices) || prices.length < 2) {
            return 0;
        }

        int totalProfit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                totalProfit += prices[i + 1] - prices[i];
            }
        }

        return totalProfit;
    }
}
