package com.meng.example.snake.model;

/**
 * 方向
 */
public enum  SnakeDirection {


    LEFT(37), UP(38), RIGHT(39), DOWN(40);

    int code;

    SnakeDirection(int code){
        this.code = code;
    }
    public static SnakeDirection toDirection(int code){
        switch (code){
            case 37:
                return LEFT;
            case 38:
                return UP;
            case 39:
                return RIGHT;
            case 40:
                return DOWN;
            default:
                throw new RuntimeException("error code:" + code);
        }
    }

    public int CodeValue(){
        return this.code;
    }


    public static final int LEFTNUM = 37;
    public static final int UPNUM = 38;
    public static final int RIGHTNUM = 39;
    public static final int DOWNNUM = 40;

}
