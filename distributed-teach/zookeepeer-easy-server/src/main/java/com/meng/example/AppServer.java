package com.meng.example;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * server
 *
 */
public class AppServer implements Watcher {

    AppRegister register = new AppRegister();

    ZooKeeper zooKeeper;

    public AppServer(){
    }

    void register(String serverInfo){
        zooKeeper = register.getAppRegisterConnect(this);
        try {
            zooKeeper.create(AppRegister.ROOT + "/server", serverInfo.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void process(WatchedEvent event) {

    }

    public static void main(String[] args) {
        AppServer appServer = new AppServer();
        NodeState nodeState = new NodeState();
        nodeState.setClientName(null);
        nodeState.setIp("192.168.9.151");
        nodeState.setPort("80");
        nodeState.setStatus("init");
        nodeState.setServerName("我是server");
        appServer.register(JSON.toJSONString(nodeState));
        System.out.println("服务注册成功");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
