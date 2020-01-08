package com.meng.example.snake.model;

import java.io.Serializable;

public class GameEvent implements Serializable {
    //通知目标，为null时通知所有客户端
    private transient String accountId;
    //事件类型 die
    private EventType type;

    //事件消息
    private String message;


    public GameEvent(EventType type, String message) {
        this.type = type;
        this.message = message;
    }

    public static enum EventType{
        DIE;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
