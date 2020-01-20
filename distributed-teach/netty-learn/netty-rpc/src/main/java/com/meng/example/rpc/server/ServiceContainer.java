package com.meng.example.rpc.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ServiceContainer {

    /**
     * 服务的实例
     */
    private Object instance;

    /**
     * 函数
     * key = methodName
     */
    private HashMap<String, Method> methodMap = new HashMap<>();

    /**
     * 函数参数集
     * key = methodName
     */
    private HashMap<String, Class[]> paramsMap = new HashMap<>();

    /**
     * 返回值类型
     * key = methodName
     */
    private HashMap<String, Class> returnMap = new HashMap<>();


    public ServiceContainer(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        //初始化容器
        this.instance = clazz.newInstance();
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            this.methodMap.put(methodName, methods[i]);
            this.paramsMap.put(methodName, methods[i].getParameterTypes());
            this.returnMap.put(methodName, methods[i].getReturnType());
        }
    }

    /**
     * invoke
     *
     * @param methodName
     * @param args
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object invoke(String methodName, Object... args) throws InvocationTargetException, IllegalAccessException {
        return this.methodMap.get(methodName).invoke(this.instance, args);
    }

}
