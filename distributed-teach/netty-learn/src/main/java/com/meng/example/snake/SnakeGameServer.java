package com.meng.example.snake;

import com.alibaba.fastjson.JSON;
import com.meng.example.snake.gameengine.SnakeGameEngine;
import com.meng.example.snake.handler.HttpRequestHandler;
import com.meng.example.snake.handler.SnakeGameHandler;
import com.meng.example.snake.listener.SnakeGameListener;
import com.meng.example.snake.model.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;

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
       try {
           ServerBootstrap serverBootstrap = new ServerBootstrap();
           serverBootstrap.group(bossGroup, workerGroup)
                   .channel(NioServerSocketChannel.class)
                   .childHandler(new ChannelInitializer() {
                       @Override
                       protected void initChannel(Channel ch) throws Exception {
                           ChannelPipeline pipeline = ch.pipeline();
                           pipeline.addLast("http-decodec", new HttpRequestDecoder());
                           pipeline.addLast("http-aggregator", new HttpObjectAggregator(65536));
                           pipeline.addLast("http-encodec", new HttpResponseEncoder());
                           pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                           pipeline.addLast("http-request", new HttpRequestHandler("/ws"));
                           pipeline.addLast("webSocket-protocol",new WebSocketServerProtocolHandler("/ws"));
                           pipeline.addLast("webSocket-request", new SnakeGameHandler(gameEngine, channels));
                       }
                   }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
           System.out.println("SnakeGameServer 启动了" + port);
           // 绑定端口，开始接收进来的连接
           ChannelFuture f = serverBootstrap.bind(port).sync();
           // 等待服务器  socket 关闭 。
           f.channel().closeFuture().sync();
       }finally {
           workerGroup.shutdownGracefully();
           bossGroup.shutdownGracefully();
           System.out.println("SnakeGameServer 关闭了");
       }
    }


    private void startGame(){
        gameEngine.start();
        gameEngine.setListener(new SnakeGameListener() {
            @Override
            public void versionChange(VersionData changeData, VersionData currentData) {
                sendVersionData(changeData);
            }

            @Override
            public void statusChange(GameStatistic statistics) {
                sendStatusData(statistics);
            }

            @Override
            public void noticeEvent(GameEvent[] events) {
                sendEvent(events);
            }
        });
    }


    /**
     * 发送事件
     * @param events
     */
    public void sendEvent(GameEvent[] events){
        String prefix = "event\r\n";
        for (Channel channel : channels) {
            for (GameEvent event : events) {
                if (event.getAccountId() == null ||
                        event.getAccountId().equals(channel.id().asShortText())) {
                    channel.writeAndFlush(new TextWebSocketFrame(prefix+JSON.toJSONString(event)));
                }
            }
        }
    }


    /**
     * 发送统计信息
     * @param statistics
     */
    public void sendStatusData(GameStatistic statistics){
        String prefix = "status\r\n";
        for (Channel channel : channels) {
            IntegralInfo info = gameEngine.getIntegralInfoByAccountId(channel.id().asShortText());
            statistics.setCurrent(info);
            channel.writeAndFlush(new TextWebSocketFrame(prefix + JSON.toJSONString(statistics)));
        }
    }


    /**
     * 发送画面信息
     * @param data
     */
    public void sendVersionData(VersionData data){
        VersionData copy=new VersionData(); // 副本
        BeanUtils.copyProperties(data,copy);
        String str = JSON.toJSONString(data);
        String prefix = "version\r\n";
        String[] cmds, cmdDatas;
        for(Channel channel : channels){
            DrawingCommand cmd = gameEngine.getDrawingCommand(channel.id().asShortText());
            if(cmd != null){
                // 基于当前角色通道的 特殊作画指令
                cmds = Arrays.copyOf(data.getCmds(), data.getCmds().length + 1);
                cmds[cmds.length - 1] = cmd.getCmd();
                cmdDatas = Arrays.copyOf(data.getCmdDatas(), data.getCmdDatas().length + 1);
                cmdDatas[cmdDatas.length - 1] = cmd.getCmdData();
                copy.setCmds(cmds);
                copy.setCmdDatas(cmdDatas);
                channel.writeAndFlush(new TextWebSocketFrame(prefix + JSON.toJSONString(copy)));
            } else {
                channel.writeAndFlush(new TextWebSocketFrame(str));
            }
        }

    }

}
