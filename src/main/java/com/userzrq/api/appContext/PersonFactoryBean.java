package com.userzrq.api.appContext;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

public class PersonFactoryBean implements FactoryBean<Person> {

    int count = 1;

    /**
     * 返回创建好的对象
     */
    @Nullable
    @Override
    public Person getObject() throws Exception { //@1
        Person person = new Person();
        person.setName("我是通过FactoryBean创建的第" + count++ + "对象");//@4
        return person;
    }


    /**
     * 返回创建好的对象类型
     */
    @Nullable
    @Override
    public Class<?> getObjectType() {
        return Person.class; //@2
    }

    // 当这个方法返回false的时候，表示由这个FactoryBean创建的对象是多例的
    // 那么我们每次从容器中getBean的时候都会去重新调用FactoryBean中的getObject方法获取一个新的对象。
    @Override
    public boolean isSingleton() {
        return true; //@3  返回true,表示创建的对象是单例的，那么我们每次从容器中获取这个对象的时候都是同一个对象

    }
}
