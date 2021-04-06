package com.userzrq.bigdecimal;


import java.math.BigDecimal;

public class DecimalTest {

    public static void main(String[] args) {

        BigDecimal money = new BigDecimal("57.46").multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_DOWN);

        System.out.println("money123=" + String.valueOf(money));
    }

}
