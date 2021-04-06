package com.userzrq.designPatterns.strategy.dutyChain.impl;


import com.userzrq.designPatterns.strategy.Receipt;
import com.userzrq.designPatterns.strategy.dutyChain.IReceiptHandleChain;
import com.userzrq.designPatterns.strategy.dutyChain.IReceiptHandler;
import com.userzrq.designPatterns.strategy.dutyChain.ReceiptHandlerContainer;

import java.util.List;

/**
 * @Description: 责任链接口的实现类
 * @Auther:
 */
public class ReceiptHandleChain implements IReceiptHandleChain {

    // 记录当前处理者的位置
    private int index = 0;
    // 处理者集合
    private static List<IReceiptHandler> receiptHandlerList;

    static {
        // 从容器中获取处理器对象
        receiptHandlerList = ReceiptHandlerContainer.getReceiptHandlerList();
    }

    @Override
    public void handleReceipt(Receipt receipt) {
        if (receiptHandlerList != null && receiptHandlerList.size() > 0) {
            // 防止索引越界
            if(index != receiptHandlerList.size()){
                IReceiptHandler receiptHandler = receiptHandlerList.get(index++);
                receiptHandler.handleReceipt(receipt,this); // this指调用者，就是调用此方法的责任链实现类
            }
        }
    }
}
