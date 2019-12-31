package com.meng.example.enums;

/**
 * 监控的数据发送方式
 */
public enum MonitorPushTypeEnum {

    HTTP_SYNC(MonitorTransferTypeEnum.HTTP),
    HTTP_ASYN(MonitorTransferTypeEnum.HTTP),
    HTTP_SERVLET(MonitorTransferTypeEnum.HTTP),
    SOCKET_ASYN(MonitorTransferTypeEnum.SOCKET);

    private MonitorTransferTypeEnum type;

    MonitorPushTypeEnum(MonitorTransferTypeEnum type){
        this.type = type;
    }

    public MonitorTransferTypeEnum getType() {
        return type;
    }

    public void setType(MonitorTransferTypeEnum type) {
        this.type = type;
    }
}
