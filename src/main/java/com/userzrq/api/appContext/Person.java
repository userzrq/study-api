package com.userzrq.api.appContext;

import lombok.Data;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Data
public class Person implements InitializingBean,DisposableBean {

    private Integer id;
    private String name;

//    public void init() {
////        System.out.println("Person类被初始化了");
////    }

    public Person() {
        this.name = "我是通过Person的无参构造方法创建的!";
    }

    public Person(String name, int id) {
        this.name = name;
        this.id = id;

    }

    //@Override
    // 当容器创建多个对象，避免重复指定销毁方法的复杂性，可以直接重写销毁方法
    public void destroy() throws Exception {
        System.out.println("Person对象被销毁了");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Person对象被初始化了");
    }
}
