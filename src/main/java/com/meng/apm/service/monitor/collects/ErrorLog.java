package com.meng.apm.service.monitor.collects;

import com.meng.apm.service.monitor.NotProguard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotProguard
public class ErrorLog {
    private String logType;
    private String statck;
    private String errorMsg;
    private String errorType;
    private String ip;
    private String keyId;
    private Long createTime;
}
