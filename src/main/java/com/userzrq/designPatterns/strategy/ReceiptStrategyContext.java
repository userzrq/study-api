package com.userzrq.designPatterns.strategy;

import com.userzrq.designPatterns.strategy.service.IReceiptHandleStrategy;

/**
 * @Description: 上下文类，持有策略接口
 * @Auther:
 */
public class ReceiptStrategyContext {

    private IReceiptHandleStrategy iReceiptHandleStrategy;


    public void setiReceiptHandleStrategy(IReceiptHandleStrategy iReceiptHandleStrategy) {
        this.iReceiptHandleStrategy = iReceiptHandleStrategy;
    }

    /**
     * 调用工程类生产具体策略
     * @param receipt
     */
    public void handleReceipt(Receipt receipt) {
        if (iReceiptHandleStrategy != null) {
            iReceiptHandleStrategy.handleReceipt(receipt);
        }
    }
}
