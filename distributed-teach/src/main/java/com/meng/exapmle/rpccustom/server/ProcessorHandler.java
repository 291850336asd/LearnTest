package com.meng.exapmle.rpccustom.server;

import com.meng.exapmle.rpccustom.RPCRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements Runnable{

    Socket socket;
    Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    public ProcessorHandler(){}

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RPCRequest request = (RPCRequest) inputStream.readObject();
            Object result = invoke(request);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Object invoke(RPCRequest request){
        Object[] args = request.getParams();
        Class<?>[] argTypes = new Class[args.length];
        for (int i=0; i< args.length; i++){
            argTypes[i] = args[i].getClass();
        }
        try {
            Method method = service.getClass().getMethod(request.getMethodName(), argTypes);
           return method.invoke(service, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
