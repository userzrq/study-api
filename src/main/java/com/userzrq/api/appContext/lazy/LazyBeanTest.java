package com.userzrq.api.appContext.lazy;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LazyBeanTest {

    @Test
    public void lazyInitBean() {
        System.out.println("spring容器启动中...");
        String beanXml = "classpath:lazyInitBean.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml); //启动spring容器
        System.out.println("spring容器启动完毕...");
        System.out.println("从容器中开始查找LazyInitBean");

        // 当调用context.getBean方法的时候，LazyInitBean才被创建的。
        LazyInitBean lazyInitBean = context.getBean(LazyInitBean.class);
        System.out.println("LazyInitBean:" + lazyInitBean);
    }

    @Test
    public void actualTimeDependencyLazyBean() {
        System.out.println("spring容器启动中...");
        String beanXml = "classpath:lazyInitBean.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml); //启动spring容器
        System.out.println("spring容器启动完毕...");
    }
}
