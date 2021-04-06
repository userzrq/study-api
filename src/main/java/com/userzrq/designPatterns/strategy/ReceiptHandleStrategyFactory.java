package com.userzrq.designPatterns.strategy;

import com.userzrq.designPatterns.strategy.service.IReceiptHandleStrategy;
import com.userzrq.designPatterns.strategy.service.impl.Mt1101ReceiptHandleStrategy;
import com.userzrq.designPatterns.strategy.service.impl.Mt2101ReceiptHandleStrategy;
import com.userzrq.designPatterns.strategy.service.impl.Mt8104ReceiptHandleStrategy;
import com.userzrq.designPatterns.strategy.service.impl.Mt9999ReceiptHandleStrategy;
import com.userzrq.designPatterns.strategy.util.SophonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptHandleStrategyFactory {

    private static Map<String, IReceiptHandleStrategy> receiptHandleStrategyMap;

    private ReceiptHandleStrategyFactory() {
        receiptHandleStrategyMap = new HashMap<>();
        // 手动的方式往字典集中添加元素
        receiptHandleStrategyMap.put("MT1101", new Mt1101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT2101", new Mt2101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT8104", new Mt8104ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT9999", new Mt9999ReceiptHandleStrategy());


        List<Class<?>> interfaceImpls = SophonUtils.getInterfaceImpls(IReceiptHandleStrategy.class);

        for (Class<?> interfaceImpl : interfaceImpls) {
//            receiptHandleStrategyMap.put();
        }
    }

    public static IReceiptHandleStrategy getReceiptHandleStrategy(String receiptType) {
        return receiptHandleStrategyMap.get(receiptType);
    }
}
