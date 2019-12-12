package com.meng.example;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;


/**
 * 注册中心
 */
public class AppRegister {

    public static final String ROOT = "/appinfos";

    ZooKeeper zooKeeper;

    public ZooKeeper getAppRegisterConnect(Watcher watcher){
        try {
            zooKeeper = new ZooKeeper("192.168.163.128:2181", 6000, watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
