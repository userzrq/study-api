package com.userzrq.designPatterns.strategy.dutyChain.impl;

import com.userzrq.designPatterns.strategy.Receipt;
import com.userzrq.designPatterns.strategy.dutyChain.IReceiptHandleChain;
import com.userzrq.designPatterns.strategy.dutyChain.IReceiptHandler;
import org.apache.commons.lang.StringUtils;


public class Mt1101ReceiptHandler implements IReceiptHandler {

    @Override
    public void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain) {
        if (StringUtils.equals("MT1101", receipt.getType())) {
            System.out.println("解析报文MT1101:" + receipt.getMessage());
        }

        // 不符合处理条件，处理不了的就往下传递
        else {
            handleChain.handleReceipt(receipt);
        }
    }

}
