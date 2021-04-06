package com.userzrq.reflection.demo;

import com.userzrq.reflection.annotaionDemo.entity.User;
import com.userzrq.reflection.demo.material.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {
        //Demo1.  通过Java反射机制得到类的包名和类名
        Demo1();
        System.out.println("===============================================");

        //Demo2.  验证所有的类都是Class类的实例对象
        Demo2();
        System.out.println("===============================================");

        //Demo3.  通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]，无参构造
        Demo3();
        System.out.println("===============================================");

        //Demo4:  通过Java反射机制得到一个类的构造函数，并实现构造带参实例对象
        Demo4();
        System.out.println("===============================================");

        //Demo5:  通过Java反射机制操作成员变量, set 和 get
        Demo5();
        System.out.println("===============================================");

        //Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
        Demo6();
        System.out.println("===============================================");

        //Demo7: 通过Java反射机制调用类中方法
        Demo7();
        System.out.println("===============================================");

        //Demo8: 通过Java反射机制获得类加载器
        Demo8();
        System.out.println("===============================================");

    }

    /**
     * Demo1: 通过Java反射机制得到类的包名和类名
     */
    public static void Demo1() {
        User user = new User();
        System.out.println("Demo1:" + user.getClass().getPackage().getName() + ","
                + "完整类名" + user.getClass().getName());
    }

    /**
     * Demo2: 验证所有的类都是Class类的实例对象
     *
     * @throws ClassNotFoundException
     */
    public static void Demo2() throws ClassNotFoundException {
        Class<?> class1 = null;
        Class<?> class2 = null;

        class1 = Class.forName("com.userzrq.reflection.annotaionDemo.entity.User");
        System.out.println("Demo2:(写法1) 包名: " + class1.getPackage().getName() + " , "
                + "完整类名: " + class1.getName());

        class2 = User.class;
        System.out.println("Demo2:(写法2) 包名: " + class2.getPackage().getName() + " , "
                + "完整类名: " + class2.getName());
    }

    /**
     * Demo3: 通过Java反射机制，用Class 创建类对象[这也就是反射存在的意义所在]
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void Demo3() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> class1 = null;
        class1 = Class.forName("com.userzrq.reflection.annotaionDemo.entity.User");

        User user = (User) class1.newInstance();
        user.setUsername("userZrq");
        user.setId(123L);

        System.out.println("Demo3: " + user.getId() + " : " + user.getUsername());
    }

    /**
     * Demo4: 通过Java反射机制得到一个类的构造函数，并实现创建带参实例对象
     *
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IllegalArgumentException
     */
    public static void Demo4() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> class1 = null;
        Constructor<?>[] constructors = Class.forName("com.userzrq.reflection.demo.material.Person").getConstructors();

        Person person1 = (Person) constructors[0].newInstance();
        person1.setAge(30);
        person1.setName("张三");

        Person person2 = (Person) constructors[1].newInstance(50, "李四");
        System.out.println("Demo4: " + person1.getName() + " : " + person1.getAge()
                + "  ,   " + person2.getName() + " : " + person2.getAge()
        );
    }

    /**
     * Demo5: 通过Java反射机制操作成员变量, set 和 get
     *
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static void Demo5() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> class1 = null;
        class1 = Class.forName("com.userzrq.reflection.demo.material.Person");

        Object obj = class1.newInstance();
        Field personAgeField = class1.getDeclaredField("age");

        personAgeField.setAccessible(true);
        personAgeField.set(obj, 66);

        System.out.println("Demo5: 修改属性之后得到属性变量的值：" + personAgeField.get(obj));

    }

    /**
     * Demo6: 通过Java反射机制得到类的一些属性： 继承的接口，父类，函数信息，成员信息，类型等
     *
     * @throws ClassNotFoundException
     */
    public static void Demo6() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.userzrq.reflection.demo.material.SuperMan");

        // 获取父类的 Class对象
        Class<?> superclass = class1.getSuperclass();
        System.out.println("Demo6:  SuperMan类的父类名: " + superclass.getName());
        System.out.println("===============================================");

        Field[] declaredFields = class1.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println("SuperMan类中的成员变量" + declaredFields[i].getName());
        }
        System.out.println("===============================================");

        // 获取类中的方法
        Method[] declaredMethods = class1.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.println("Demo6,取得SuperMan类的方法：");
            System.out.println("函数名: " + declaredMethods[i].getName());
            System.out.println("函数返回类型：" + declaredMethods[i].getReturnType());
            System.out.println("函数访问修饰符：" + Modifier.toString(declaredMethods[i].getModifiers()));
            System.out.println("函数代码写法： " + declaredMethods[i]);
        }
        System.out.println("===============================================");

        // 获取类实现的接口
        Class<?>[] interfaces = class1.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("实现的接口类名: " + interfaces[i].getName());
        }
    }

    /**
     * Demo7: 通过Java反射机制调用类方法
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InstantiationException
     */
    public static void Demo7() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> class1 = null;
        class1 = Class.forName("com.userzrq.reflection.demo.material.SuperMan");

        Method fly = class1.getDeclaredMethod("fly");
        fly.invoke(class1.newInstance());

        Method walk = class1.getDeclaredMethod("walk", int.class);
        walk.invoke(class1.newInstance(), 50);
    }

    /**
     * Demo8: 通过Java反射机制得到类加载器信息
     * <p>
     * 在java中有三种类类加载器。[这段资料网上截取]
     * 1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
     * 2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
     * 3）AppClassLoader 加载classpath指定的类，是最常用的加载器。同时也是java中默认的加载器。
     *
     * @throws ClassNotFoundException
     */
    public static void Demo8() throws ClassNotFoundException {
        Class<?> class1 = null;
        class1 = Class.forName("com.userzrq.reflection.demo.material.SuperMan");

        String name = class1.getClassLoader().getClass().getName();
        System.out.println("Demo8: 类加载器类名: " + name);
    }

}
