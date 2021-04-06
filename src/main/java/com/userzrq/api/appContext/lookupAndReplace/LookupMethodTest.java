package com.userzrq.api.appContext.lookupAndReplace;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LookupMethodTest {

    @Test
    public void normalBean() {
        String beanXml = "classpath:normalBean.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml);
        // 容器中的A是多例的，B是单例的
        System.out.println(context.getBean(ServiceA.class)); //@1
        System.out.println(context.getBean(ServiceA.class)); //@2

        System.out.println("serviceB中的serviceA");
        ServiceB serviceB = context.getBean(ServiceB.class); //@3
        // 从容器中获取一个serviceA将其注入到serviceB中，所以自始至终serviceB中的serviceA都是同一个对象

        System.out.println(serviceB.getServiceA()); //@4  com.userzrq.api.appContext.lookupAndReplace.ServiceA@473b46c3
        System.out.println(serviceB.getServiceA()); //@5  com.userzrq.api.appContext.lookupAndReplace.ServiceA@473b46c3
    }

    @Test
    public void alicationcontextaware() {
        String beanXml = "classpath:alicationcontextaware.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml);

        System.out.println(context.getBean(ServiceA.class)); //@1
        System.out.println(context.getBean(ServiceA.class)); //@2

        System.out.println("serviceB中的serviceA");

        ServiceB serviceB = context.getBean(ServiceB.class); //@3
        System.out.println("---->" + serviceB + "<----");
        // say方法每次都会从容器中取新的A对象，且A对象是多例的
        serviceB.say();
        serviceB.say();
    }

}
