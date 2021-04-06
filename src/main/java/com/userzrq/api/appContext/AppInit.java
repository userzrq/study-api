package com.userzrq.api.appContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.ArrayList;

public class AppInit {
    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        // 且该容器通过xml配置的方式对bean进行加载 因此类型为 ClassPathXmlApplicationContext
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        // 说明对象在容器创建过程中已经被实例化
        System.out.println("context 启动成功");

        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        //MessageService messageService = context.getBean(MessageService.class);

        MessageService messageService = (MessageService) context.getBean("messageService");
        Person p19 = (Person) context.getBean("p19");
        Person p20 = (Person) context.getBean("p20");

        Person peroson = (Person) context.getBean("p21");

        // 返回Spring容器中所有bean的名称
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName + ":" + context.getBean(beanName));
        }

        System.out.println("--------------------------");
        //多次获取createByFactoryBean看看是否是同一个对象
        System.out.println("createByFactoryBean:" + context.getBean("createByFactoryBean"));
        System.out.println("createByFactoryBean:" + context.getBean("createByFactoryBean"));


        // 这句将输出: hello world
        //System.out.println(messageService.getMessage());

        context.close();


    }
}
