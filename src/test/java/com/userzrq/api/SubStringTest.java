package com.userzrq.api;


public class SubStringTest {
    public static void main(String[] args) {

        String a = " Preface I hope for this book that it may come into the hands of those that were kind to my others ";
        String b = " Preface ";
        String substring = a.substring(b.length());
        String trim = a.trim();

        System.out.println(substring);
        System.out.println(trim);
    }
}
