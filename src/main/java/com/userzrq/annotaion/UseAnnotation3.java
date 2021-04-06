package com.userzrq.annotaion;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Ann3{
    // 参数为value的注解，可以省略参数名称
    String value();
}

@Ann3("参数为value的注解，可以省略参数名称")
public class UseAnnotation3 {
}
