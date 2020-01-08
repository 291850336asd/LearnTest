package com.meng.example.snake.model;

/**
 * 食物
 */
public class Food {
    /**
     * 当前位置
     */
    public Integer[] point;

    private int type;

    public Food(Integer[] point, int type) {
        this.point = point;
        this.type = type;
    }

}
