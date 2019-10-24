package com.meng.spring.springaop;

import com.meng.spring.test.UserService;
import com.meng.spring.test.UserServiceImp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JavaProxyTest {

    public static void main(String[] args) {
        UserServiceImp userServiceImp = new UserServiceImp();
        UserService service = (UserService) Proxy.newProxyInstance(JavaProxyTest.class.getClassLoader(),
                new Class[]{UserService.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object object = null;
                        System.out.println("前置通知");
                        try {
                           object = method.invoke(userServiceImp, args);
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("异常通知");
                        }
                        System.out.println("后置通知");
                        return object;
                    }
                });
        service.setName("sdsds");
        System.out.println(service.getName());
    }


}
