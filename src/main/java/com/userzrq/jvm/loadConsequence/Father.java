package com.userzrq.jvm.loadConsequence;

public class Father {
    public String fStr1 = "father1";
    protected String fStr2 = "father2";
    private String fStr3 = "father3";

    {
        System.out.println("Father common block be called");
    }

    static {
        System.out.println("Father static block be called");
    }

    public Father() {
        System.out.println("Father constructor fStr1: " + fStr1);
        System.out.println("Father constructor fStr2: " + fStr2);
        System.out.println("Father constructor fStr3: " + fStr3);
        System.out.println("Father constructor be called");
    }
}
