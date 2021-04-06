package com.userzrq.api.appContext.lookup;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LookupMethodTest {

    @Test
    public void lookupmethod() {
        String beanXml = "classpath:lookupmethod.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml);

        System.out.println(context.getBean(ServiceA.class)); //@1
        System.out.println(context.getBean(ServiceA.class)); //@2

        System.out.println("serviceB中的serviceA");
        // 源码中 getServiceA() 这个方法返回的是null
        ServiceB serviceB = context.getBean(ServiceB.class); //@3
        serviceB.say();
        serviceB.say();
    }
}
