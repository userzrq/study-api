package com.userzrq.designPatterns.factory.simplefactory;

public class FruitFactory {

    public Fruit create(String type) {
        switch (type) {
            case "apple":
                /**
                 * AppleSeed appleSeed = new AppleSeed();
                 * Sunlight sunlight = new Sunlight();
                 * Water water = new Water();
                 * return new Apple(appleSeed, sunlight , water);
                 */
                return new Apple();
            case "pear":
                return new Pear();
            default:
                throw new IllegalArgumentException("暂时没有这种水果");
        }
    }
}
