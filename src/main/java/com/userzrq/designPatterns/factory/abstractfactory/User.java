package com.userzrq.designPatterns.factory.abstractfactory;

import com.userzrq.designPatterns.factory.simplefactory.Fruit;

public class User {

    private void eat1() {
        IFactory factory = new AppleFactory();
        Fruit fruit = factory.create();
        fruit.eat();
    }

    private void eat2() {
        IFactory factory = new PearFactory();
        Fruit fruit = factory.create();
        fruit.eat();
    }
}
