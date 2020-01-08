package com.meng.example.snake.model;

import java.util.List;

/**
 * 游戏统计
 */
public class GameStatistic {

    /**
     * 在线人数
     */
    private int onlineCount;

    /**
     *  版本
     */
    private long lastVersion;

    /**
     * 积分排行榜
     */
    private List<IntegralInfo> rankingList;

    /**
     * 当前玩家积分
     */
    private IntegralInfo current;

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public long getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(long lastVersion) {
        this.lastVersion = lastVersion;
    }

    public List<IntegralInfo> getRankingList() {
        return rankingList;
    }

    public void setRankingList(List<IntegralInfo> rankingList) {
        this.rankingList = rankingList;
    }

    public IntegralInfo getCurrent() {
        return current;
    }

    public void setCurrent(IntegralInfo current) {
        this.current = current;
    }
}
