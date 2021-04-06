package com.userzrq.designPatterns.singleton;

public class SingletonDemo {

    private SingletonDemo() {

    }

    private static class SingletonDemoHandler {
        private static SingletonDemo instance = new SingletonDemo();
    }

    public static SingletonDemo getInstance() {
        return SingletonDemoHandler.instance;
    }
}
