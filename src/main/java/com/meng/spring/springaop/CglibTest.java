package com.meng.spring.springaop;


import com.meng.spring.test.UserService;
import com.meng.spring.test.UserServiceImp;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {

    public static void main(String[] args) {
        UserServiceImp userServiceImp = new UserServiceImp();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImp.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("前置通知");
                Object object = null;
                try{
                    object = methodProxy.invoke(userServiceImp, objects);
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("异常通知");
                }
                System.out.println("后置通知");
                return object;
            }
        });
        UserService userService = (UserService) enhancer.create();
        userService.setName("sds");
        System.out.println(userService.getName());
    }
}
