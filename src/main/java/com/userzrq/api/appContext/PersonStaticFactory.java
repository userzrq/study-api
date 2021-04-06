package com.userzrq.api.appContext;

public class PersonStaticFactory {
    /**
     * 静态无参方法创建Person
     *
     * @return
     */
    public static Person buildPerson1() {

        System.out.println(Person.class + ".buildPerson1()");

        Person person = new Person();
        person.setName("我是无参静态构造方法创建的!");
        return person;
    }

    /**
     * 静态有参方法创建Person
     *
     * @param name 名称
     * @param age  年龄
     * @return
     */
    public static Person buildPerson2(String name, int age) {

        System.out.println(Person.class + ".buildPerson2()");

        Person person = new Person();
        person.setName(name);
        person.setId(age);
        return person;
    }
}
