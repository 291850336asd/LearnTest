package com.meng.example.generialization.server;

import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

public class MyGenericService implements GenericService {

    public Object $invoke(String methodName, String[] parameterTypes, Object[] args) throws GenericException {
        if ("sayHello1".equals(methodName)) {
            return "Welcome " + args[0];
        }
        return null;
    }
}