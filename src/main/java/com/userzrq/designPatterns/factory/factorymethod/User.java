package com.userzrq.designPatterns.factory.factorymethod;

import com.userzrq.designPatterns.factory.simplefactory.Fruit;

public class User {

    /**
     * 当生产的产品种类越来越多时，工厂类不会变成超级类
     * 符合单一职责原则
     */
    public void eat() {
        AppleFactory appleFactory = new AppleFactory();
        PearFactory pearFactory = new PearFactory();

        Fruit apple = appleFactory.create();
        Fruit pear = pearFactory.create();

        apple.eat();
        pear.eat();

    }
}
