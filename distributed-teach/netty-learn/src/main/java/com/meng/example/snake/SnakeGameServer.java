package com.meng.example.snake;

import com.meng.example.snake.gameengine.SnakeGameEngine;
import com.meng.example.snake.listener.SnakeGameListener;
import com.meng.example.snake.model.GameEvent;
import com.meng.example.snake.model.GameStatistic;
import com.meng.example.snake.model.VersionData;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * websocket 聊天室服务器
 */
public class SnakeGameServer {

    private int port;
    private ChannelGroup channels;
    final SnakeGameEngine gameEngine;
    public SnakeGameServer(int port) {
        this.port = port;
        channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        gameEngine = new SnakeGameEngine(200, 200, 200);
    }

    public void run() throws Exception{
        //启动游戏
        startGame();
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(3);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
    }


    private void startGame(){
        gameEngine.start();
        gameEngine.setListener(new SnakeGameListener() {
            @Override
            public void versionChange(VersionData changeData, VersionData currentData) {
            }

            @Override
            public void statusChange(GameStatistic statistic) {

            }

            @Override
            public void noticeEvent(GameEvent[] events) {

            }
        });
    }

}
