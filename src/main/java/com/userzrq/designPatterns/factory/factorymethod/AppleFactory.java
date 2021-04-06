package com.userzrq.designPatterns.factory.factorymethod;

import com.userzrq.designPatterns.factory.simplefactory.Apple;
import com.userzrq.designPatterns.factory.simplefactory.Fruit;

public class AppleFactory {

    public Fruit create() {
        return new Apple();
    }
}
