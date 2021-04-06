package com.userzrq.api.appContext;



import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CandidateInit {
    public static void main(String[] args) {

        String beanXml = "classpath:bean1.xml";
        ClassPathXmlApplicationContext context = IocUtils.context(beanXml);
        System.out.println(context.getBean(SetterBean.class)); //@1

        SetterBean.IService service = context.getBean(SetterBean.IService.class); //@2
        System.out.println(service);

        context.close();
    }

}
