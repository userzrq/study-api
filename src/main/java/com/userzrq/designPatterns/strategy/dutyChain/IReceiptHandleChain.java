package com.userzrq.designPatterns.strategy.dutyChain;

import com.userzrq.designPatterns.strategy.Receipt;

/**
 * @Description: 责任链接口
 * @Auther:
 */
public interface IReceiptHandleChain {

    void handleReceipt(Receipt receipt);
}
