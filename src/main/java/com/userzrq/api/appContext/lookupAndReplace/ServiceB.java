package com.userzrq.api.appContext.lookupAndReplace;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ServiceB implements ApplicationContextAware {

    private ApplicationContext context;

    public void say() {
        // 调用getServiceA方法获取ServiceA对象，然后将其输出
        ServiceA serviceA = this.getServiceA();//@2
        System.out.println("this:" + this + ",serviceA:" + serviceA);
    }

    public ServiceA getServiceA() {
        // 从容器中主动去获取ServiceA，这样每次获取到的ServiceA都是一个新的实例
        return this.context.getBean(ServiceA.class);//@3
    }

    // 给予自定义的bean中获取applicationContext的能力，获取到容器对象
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
