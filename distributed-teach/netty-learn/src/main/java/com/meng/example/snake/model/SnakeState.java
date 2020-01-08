package com.meng.example.snake.model;

public enum SnakeState {

    /**
     * 待激活
     */
    INACTIVE,
    /**
     * 激活
     */
    ALIVE,

    /**
     * 生长
     */
    GROW,

    /**
     * 待死亡
     */
    DYING,

    /**
     * 死亡
     */
    DIE,

    /**
     * 离线
     */
    OFFLINE;


}
