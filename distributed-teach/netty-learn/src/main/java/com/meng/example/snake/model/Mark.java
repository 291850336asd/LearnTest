package com.meng.example.snake.model;

/**
 * 地图标记位
 */
public class Mark {

    public int snakeNodes = 0;
    public int foodNode = 0;

    //判断当前节点
    public boolean isEmpty(){
        return snakeNodes <=0 && foodNode <=0;
    }
}
