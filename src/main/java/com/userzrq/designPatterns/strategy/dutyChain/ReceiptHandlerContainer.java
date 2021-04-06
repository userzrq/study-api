package com.userzrq.designPatterns.strategy.dutyChain;

import com.userzrq.designPatterns.strategy.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description: 处理者容器
 * @Auther:
 */
public class ReceiptHandlerContainer {

    private ReceiptHandlerContainer() {
    }

    public static List<IReceiptHandler> getReceiptHandlerList() {
        /**
         * 同样可以通过手动添加或者反射的方式获取实现类
         */
        List<IReceiptHandler> receiptHandlerList = new ArrayList<>();
        /**
         * receiptHandlerList.add(new Mt1101ReceiptHandler());
         * receiptHandlerList.add(new Mt2101ReceiptHandler());
         * receiptHandlerList.add(new Mt8104ReceiptHandler());
         * receiptHandlerList.add(new Mt9999ReceiptHandler());
         */

        // 通过反射的方式
        Set<Class<?>> classSet = ReflectionUtil.getClassSetBySuper(IReceiptHandler.class);
        if(classSet != null && classSet.size() >0){
            for (Class<?> clazz : classSet) {
                try {
                    receiptHandlerList.add((IReceiptHandler)clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return receiptHandlerList;
    }
}
