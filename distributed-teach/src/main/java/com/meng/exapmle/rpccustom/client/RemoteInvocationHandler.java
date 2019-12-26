package com.meng.exapmle.rpccustom.client;

import com.meng.exapmle.rpccustom.RPCRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler<T> implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler() {
    }

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = new RPCRequest();
        request.setMethodName(method.getName());
        request.setParams(args);
        RPCNetTransport rpcNetTransport = new RPCNetTransport(host, port);
        return rpcNetTransport.sendRequest(request);
    }
}
