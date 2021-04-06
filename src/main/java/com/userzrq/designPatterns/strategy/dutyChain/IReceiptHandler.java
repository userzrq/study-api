package com.userzrq.designPatterns.strategy.dutyChain;

import com.userzrq.designPatterns.strategy.Receipt;

/**
 * @Description: 抽象回执处理者接口
 * @Auther:
 */
public interface IReceiptHandler {

    void handleReceipt(Receipt receipt, IReceiptHandleChain handleChain);
}
