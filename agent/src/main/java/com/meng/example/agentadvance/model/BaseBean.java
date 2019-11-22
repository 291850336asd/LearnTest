package com.meng.example.agentadvance.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BaseBean implements Serializable {
    private long recordTime; //记录时间
    private String recordModel; //service controller sql http等  模型值
    private String hostIp;
    private String hostName;
    private String traceId; //链路追踪使用

}
