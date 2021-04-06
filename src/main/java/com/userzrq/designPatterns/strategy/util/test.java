package com.userzrq.designPatterns.strategy.util;

import com.userzrq.designPatterns.strategy.service.IReceiptHandleStrategy;

import java.util.List;

public class test {

    public static void main(String[] args) {

        List<Class<?>> interfaceImpls = SophonUtils.getInterfaceImpls(IReceiptHandleStrategy.class);

        for (Class<?> interfaceImpl : interfaceImpls) {
            System.out.println(interfaceImpl);
        }
    }
}
