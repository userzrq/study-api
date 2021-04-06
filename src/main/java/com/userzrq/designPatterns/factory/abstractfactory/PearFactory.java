package com.userzrq.designPatterns.factory.abstractfactory;

import com.userzrq.designPatterns.factory.simplefactory.Fruit;
import com.userzrq.designPatterns.factory.simplefactory.Pear;

public class PearFactory implements IFactory {
    @Override
    public Fruit create() {
        return new Pear();
    }
}
