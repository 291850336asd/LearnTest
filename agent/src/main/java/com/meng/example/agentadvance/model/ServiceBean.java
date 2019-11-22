package com.meng.example.agentadvance.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ServiceBean extends BaseBean implements Serializable {

    public Long begin;
    public Long end;
    public Long usedTime; //统计使用
    public String errorMsg;
    public String errorType;
    public String serviceName; //服务名称
    public String simpleName; //服务简称
    public String methodName; //方法名称

}
