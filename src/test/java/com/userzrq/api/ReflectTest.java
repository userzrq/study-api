package com.userzrq.api;

import com.userzrq.standard.ErrorResult;
import com.userzrq.standard.ResultCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author zhangruiqi 10017
 * @create 2021/3/12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReflectTest {

    @Test
    public void test() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.userzrq.standard.ErrorResult");

        // 指定构造器的参数类型
        Constructor<?> constructor = clazz.getConstructor(ResultCode.class, Object.class);
        ErrorResult result = (ErrorResult) constructor.newInstance(ResultCode.PARAM_NOT_COMPLETE, "第一次初始化");
        System.out.println(result.toString());
        Field[] declaredFields = clazz.getDeclaredFields();
        Arrays.stream(declaredFields).forEach((field) -> {
            boolean flag = field.isAccessible();

            if ("message".equals(field.getName())) {
                field.setAccessible(true);
                try {
                    field.set(result, "错误提示");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            //还原可访问权限
            field.setAccessible(flag);
        });

        System.out.println(result.toString());
    }
}
