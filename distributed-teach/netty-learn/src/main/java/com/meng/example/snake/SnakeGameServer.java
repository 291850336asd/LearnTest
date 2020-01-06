package com.meng.example.snake;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 贪吃蛇服务器端
 */
public class SnakeGameServer {

    private int port;
    final SnakeGameEngine gameEngine;
    private ChannelGroup channels;
    public SnakeGameServer(int port){
        this.port = port;
        this.gameEngine = new SnakeGameEngine(60, 60, 500);
        this.channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    public void run() throws Exception {
        //启动游戏
        gameEngine.start();

        gameEngine.setListener();
    }


}
