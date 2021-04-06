package com.userzrq.designPatterns.factory.abstractfactory;

import com.userzrq.designPatterns.factory.simplefactory.Fruit;

/**
 * 提取出工厂接口
 */
public interface IFactory {
    Fruit create();
}
