package com.userzrq.designPatterns.strategy.service.impl;

import com.userzrq.designPatterns.strategy.Receipt;
import com.userzrq.designPatterns.strategy.service.IReceiptHandleStrategy;

public class Mt1101ReceiptHandleStrategy implements IReceiptHandleStrategy {

    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("解析报文MT1101:" + receipt.getMessage());
    }
}

