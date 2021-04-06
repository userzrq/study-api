package com.userzrq.api.appContext.proxy.jdk;

/**
 * @author zhangruiqi 10017
 * @create 2021/3/19
 */
public class Test {

    public static void main(String[] args) {
        //这句要放在生成代理对象之前，目的是为了生成$Proxy0.class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        InterImpl inter = new InterImpl();

        Inter instance = (Inter) new ProxyFactory(inter).getProxyInstance();
        /**
         * $Proxy0类，在内存中动态生成
         */
        System.out.println(instance.getClass());
        instance.test111();
    }
}
