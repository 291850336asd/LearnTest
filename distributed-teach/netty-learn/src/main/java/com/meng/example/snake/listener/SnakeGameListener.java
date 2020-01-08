package com.meng.example.snake.listener;

import com.meng.example.snake.model.GameEvent;
import com.meng.example.snake.model.GameStatistic;
import com.meng.example.snake.model.VersionData;

public interface SnakeGameListener {

    /**
     * 地图版本变更
     * @param changeData
     * @param currentData
     */
    public void versionChange(VersionData changeData, VersionData currentData);

    /**
     * 积分变更
     * @param statistic
     */
    public void statusChange(GameStatistic statistic);

    /**
     * 事件通知
     * @param events
     */
    public void noticeEvent(GameEvent[] events);

}
