package com.userzrq.jvm;

public class MainApp {
    public static void main(String[] args) {
        Animal puppy = new Animal("Puppy");
        puppy.printName();

    }
}

// Animal.java
class Animal {
    public String name;

    public Animal(String name) {
        this.name = name;
    }

    public void printName() {
        System.out.println("Animal [" + name + "]");
    }
}
