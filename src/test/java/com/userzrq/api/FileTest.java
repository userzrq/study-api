package com.userzrq.api;



import java.io.File;


public class FileTest {

    public static void main(String[] args) {
        File file = new File("D:\\Book\\Book2\\Chapter100.txt");

        String substring = file.getName().substring(7, file.getName().length() - 4);
        System.out.println(substring);

        int i = Integer.parseInt(file.getName().substring(7, file.getName().length() - 4));
        System.out.println(i);
    }

}
