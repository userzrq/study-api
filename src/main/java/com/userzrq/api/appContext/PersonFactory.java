package com.userzrq.api.appContext;

public class PersonFactory {
    public Person buildUser1() {

        System.out.println("----------------------1");

        Person userModel = new Person();
        userModel.setName("bean实例方法创建的对象!");
        return userModel;
    }


    public Person buildUser2(String name, int age) {

        System.out.println("----------------------2");

        Person userModel = new Person();
        userModel.setName(name);
        userModel.setId(age);
        return userModel;
    }
}
