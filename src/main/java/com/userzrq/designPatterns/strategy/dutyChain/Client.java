package com.userzrq.designPatterns.strategy.dutyChain;

import com.userzrq.designPatterns.strategy.Receipt;
import com.userzrq.designPatterns.strategy.ReceiptBuilder;
import com.userzrq.designPatterns.strategy.dutyChain.impl.ReceiptHandleChain;

import java.util.List;

public class Client {

    public static void main(String[] args) {
        //模拟回执
        List<Receipt> receipts = ReceiptBuilder.generateReceiptList();
        for (Receipt receipt : receipts) {
            // 回执处理链对象
            ReceiptHandleChain receiptHandleChain = new ReceiptHandleChain();
            receiptHandleChain.handleReceipt(receipt);
        }

    }
}
