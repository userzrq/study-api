package com.userzrq.designPatterns.factory.abstractfactory;

import com.userzrq.designPatterns.factory.simplefactory.Apple;
import com.userzrq.designPatterns.factory.simplefactory.Fruit;

public class AppleFactory implements IFactory {
    @Override
    public Fruit create() {
        return new Apple();
    }
}
