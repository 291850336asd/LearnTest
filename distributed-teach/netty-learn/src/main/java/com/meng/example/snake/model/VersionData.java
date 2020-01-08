package com.meng.example.snake.model;

/**
 * 版本数据
 */
public class VersionData implements Cloneable{
    /**
     * 版本号
     */
    private long version;
    /**
     * 版本构建时间
     */
    private long time;

    Boolean full;
    /**
     * 命令
     */
    private String cmds[];

    /**
     * 命令数据
     */
    private String cmdDatas[];

    public VersionData(long version, long time) {
        this.version = version;
        this.time = time;
    }

    public VersionData() {
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Boolean getFull() {
        return full;
    }

    public void setFull(Boolean full) {
        this.full = full;
    }

    public String[] getCmds() {
        return cmds;
    }

    public void setCmds(String[] cmds) {
        this.cmds = cmds;
    }

    public String[] getCmdDatas() {
        return cmdDatas;
    }

    public void setCmdDatas(String[] cmdDatas) {
        this.cmdDatas = cmdDatas;
    }
}
