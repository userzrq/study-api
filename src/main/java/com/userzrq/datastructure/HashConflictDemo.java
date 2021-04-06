package com.userzrq.datastructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class HashConflictDemo {

    public static Integer hashCode(String str, Integer multiplier) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = multiplier * hash + str.charAt(i);
        }
        return hash;

    }


    /**
     * 计算 hash code 冲突率，顺便分析一下 hash code 最大值和最小值，并输出
     *
     * @param multiplier
     * @param hashs
     */
    public static void calculateConflictRate(Integer multiplier, List<Integer> hashs) {
        Comparator<Integer> cp = (x, y) -> x > y ? 1 : (x < y ? -1 : 0);

        int maxHash = hashs.stream().max(cp).get();

        int minHash = hashs.stream().min(cp).get();

        // 计算冲突数及冲突率
        int uniqueHashNum = (int) hashs.stream().distinct().count();
        int conflictNum = hashs.size() - uniqueHashNum;
        double conflictRate = (conflictNum * 1.0) / hashs.size();
        System.out.println(String.format("multiplier=%4d, minHash=%11d, maxHash=%10d, conflictNum=%6d, conflictRate=%.4f%%", multiplier, minHash, maxHash, conflictNum, conflictRate * 100));

    }

    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 100; i += 2) {
            List<Integer> list = new ArrayList<>();
            String randomString = "";
            for (int j = 0; j < 50; j++) {
                randomString = HashConflictDemo.getRandomString(10);
                list.add(HashConflictDemo.hashCode(randomString, i));
            }
            System.out.println(list);
            HashConflictDemo.calculateConflictRate(i, list);
        }

    }
}
