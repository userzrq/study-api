package com.userzrq.api.appContext.extend;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanExtendTest {

    @Test
    public void normalBean() {
        String beanXml = "classpath:beanExtend.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml);

        // Error creating bean with name 'baseService': Bean definition is abstract，baseService是抽象的，无法被实例化
        System.out.println("serviceB:" + context.getBean("baseService"));

        System.out.println("serviceB:" + context.getBean(ServiceB.class));
        System.out.println("serviceC:" + context.getBean(ServiceC.class));
    }

}
