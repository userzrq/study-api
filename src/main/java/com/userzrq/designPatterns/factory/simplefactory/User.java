package com.userzrq.designPatterns.factory.simplefactory;

/**
 * 调用者
 */
public class User {

    private void eat(){
        FruitFactory fruitFactory = new FruitFactory();
        Fruit apple = fruitFactory.create("苹果");
        Fruit pear = fruitFactory.create("梨子");

        apple.eat();
        pear.eat();
    }
}
