package com.meng.example.monitor.interceptor;

import com.meng.example.Constants;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MonitorInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = invocation.proceed();
        long endTime = System.currentTimeMillis();
        StringBuilder methodFullName = new StringBuilder("[");
        methodFullName.append(invocation.getMethod());
        methodFullName.append("]");
        String usedNumName = methodFullName + Constants.DEFAULT_SEPARATOR + Constants.MONITOR_JEDIS_USED_NUM_NAME;
        String usedTimeName = methodFullName + Constants.DEFAULT_SEPARATOR + Constants.MONITOR_JEDIS_USED_TIME_NAME;
        Integer usedNum = Constants.MONITOR_MAP.get(usedNumName);
        if(usedNum == null){
            usedNum = 0;
        }
        usedNum +=1;
        Integer usedTime = Constants.MONITOR_MAP.get(usedTimeName);
        if(usedTime == null){
            usedTime = 0;
        }
        usedTime += Long.bitCount(endTime - beginTime);
        Constants.MONITOR_MAP.put(usedNumName, usedNum);
        Constants.MONITOR_MAP.put(usedTimeName, usedTime);
        return result;
    }
}
