package com.userzrq.designPatterns.strategy;

import com.userzrq.designPatterns.strategy.service.IReceiptHandleStrategy;

import java.util.List;

public class Client {

    public static void main(String[] args) {

        // 模拟回执
        List<Receipt> receipts = ReceiptBuilder.generateReceiptList();
        // 策略上下文对象(相当于一个容器，根据需要调用的目标对象选择策略
        // 将获取的策略设置到容器中
        // 并执行其具体的方案)
        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();

        for (Receipt receipt : receipts) {
            // 获取策略
            IReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory.getReceiptHandleStrategy(receipt.getType());
            // 设置策略
            receiptStrategyContext.setiReceiptHandleStrategy(receiptHandleStrategy);
            // 执行策略
            receiptHandleStrategy.handleReceipt(receipt);
        }


    }
}
