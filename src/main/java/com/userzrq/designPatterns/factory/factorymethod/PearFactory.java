package com.userzrq.designPatterns.factory.factorymethod;

import com.userzrq.designPatterns.factory.simplefactory.Fruit;
import com.userzrq.designPatterns.factory.simplefactory.Pear;

public class PearFactory {

    public Fruit create() {
        return new Pear();
    }
}
