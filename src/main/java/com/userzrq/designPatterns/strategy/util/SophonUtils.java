package com.userzrq.designPatterns.strategy.util;

import com.google.common.collect.Lists;
import com.userzrq.designPatterns.strategy.exception.ParamException;


//import javax.validation.constraints.NotNull;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SophonUtils {

    public static List<Class<?>> getInterfaceImpls(Class<?> target) {
        List<Class<?>> subClasses = new ArrayList<>();
        try {
            // 判断class对象是否是一个接口
            if (target.isInterface()) {
//                @NotNull
                String basePackage = target.getClassLoader().getResource("").getPath();
                System.out.println("basePackage: " + basePackage);
                File[] files = new File(basePackage).listFiles();
                // 存放class路径的list
                ArrayList<String> classpaths = Lists.newArrayList();
                System.out.println("files: " + files);
                for (File file : files) {
                    // 扫描项目编译后的所有类
                    if (file.isDirectory()) {
                        listPackages(file.getName(), classpaths);
                    }
                }
                // 获取所有类,然后判断是否是 target 接口的实现类
                for (String classpath : classpaths) {
                    System.out.println("classPath: " + classpath);
                    Class<?> classObject = Class.forName(classpath);
                    if (classObject.getSuperclass() == null) continue; // 判断该对象的父类是否为null
                    System.out.println("Superclass " + classObject.getSuperclass());

                    Set<Class<?>> interfaces = new HashSet<>(Arrays.asList(classObject.getInterfaces()));
                    System.out.println("Interfaces " + interfaces);
                    if (interfaces.contains(target)) {
                        subClasses.add(Class.forName(classObject.getName()));
                    }
                }
            } else {
                throw new ParamException("Class对象不是一个interface");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return subClasses;
    }


    public static void listPackages(String basePackage, List<String> classes) {
        URL url = SophonUtils.class.getClassLoader()
                .getResource("./" + basePackage.replaceAll("\\.", "/"));
        File directory = new File(url.getFile());
        for (File file : directory.listFiles()) {
            // 如果是一个目录就继续往下读取(递归调用)
            if (file.isDirectory()) {
                listPackages(basePackage + "." + file.getName(), classes);
            } else {
                // 如果不是一个目录,判断是不是以.class结尾的文件,如果不是则不作处理
                String classpath = file.getName();
                if (".class".equals(classpath.substring(classpath.length() - ".class".length()))) {
                    classes.add(basePackage + "." + classpath.replaceAll(".class", ""));
                }
            }
        }
    }
}
