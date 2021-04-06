package com.userzrq.reflection.annotaionDemo;


import com.userzrq.reflection.annotaionDemo.entity.Privilege;
import com.userzrq.reflection.annotaionDemo.entity.User;
import com.userzrq.reflection.annotaionDemo.exception.PrivilegeException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ServiceDaoFactory {
    private static final ServiceDaoFactory FACTORY = new ServiceDaoFactory();

    private ServiceDaoFactory() {
    }

    public static ServiceDaoFactory getInstance() {
        return FACTORY;
    }

    /**
     * 用一个代理的Service对象来处理自定义注解
     *
     * @param className
     * @param clazz
     * @param user
     * @param <T>
     * @return
     */
    public <T> T createDao(String className, Class<T> clazz, final User user) {

        try {
            final T t = (T) Class.forName(className).newInstance();
            // 该Service类的代理对象
            Proxy.newProxyInstance(ServiceDaoFactory.class.getClassLoader(), t.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // 获得用户调用的方法名
                    String methodName = method.getName();
                    System.out.println(methodName);

                    // method 和 method1 有区别吗？
                    // 通过反射 确定真正执行的方法对象（通过参数类型考虑方法重载）
                    Method method1 = t.getClass().getMethod(methodName, method.getParameterTypes());

                    permission permis = method1.getAnnotation(permission.class);

                    // 判断有没有注解,没有注解直接执行
                    if (permis == null) {
                        return method.invoke(t, args);
                    }
                    // 如果注解不为空，得到注解上的权限
                    String privilege = permis.value();

                    Privilege p = new Privilege();
                    p.setName(privilege);

                    //到这里的时候，已经是需要权限了，那么判断用户是否登陆了
                    if (user == null) {
                        throw new PrivilegeException("对不起请先登陆");
                    }


                    //执行到这里用户已经登陆了，判断用户有没有权限
                    Method m = t.getClass().getMethod("findUserPrivilege", String.class);
                    List<Privilege> list = (List<Privilege>) m.invoke(t, user.getId());

                    //看下权限集合中有没有包含方法需要的权限。使用contains方法，在Privilege对象中需要重写hashCode和equals()
                    if (!list.contains(p)) {
                        //这里抛出的异常是代理对象抛出的，sun公司会自动转换成运行期异常抛出，于是在Servlet上我们根据getCause()来判断是不是该异常，从而做出相对应的提示。
                        throw new PrivilegeException("您没有权限，请联系管理员！");
                    }

                    //执行到这里的时候，已经有权限了，所以可以放行了
                    return method.invoke(t, args);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
