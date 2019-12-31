package com.meng.example.zookeeper;


import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * zookeeper
 * redis 服务列表节点
 */
public class JedisGroups {

    private static final Logger logger = Logger.getLogger(JedisGroups.class);

    private final String param = "/services/redisinfos";

    private CuratorZookeeper curator;

    private String business;

    private List<ZkListener> listeners =  Collections.synchronizedList(new LinkedList<ZkListener>());

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public JedisGroups(String host,String business ){
        curator= new CuratorZookeeper(host,this);
        this.business = business;
    }

    public List<String> getValues() {
        return curator.getChildrens();
    }

    public void addChangeListner(ZkListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ZkListener listener) {
        listeners.remove(listener);
    }
    public String getWatchPath() {
        return this.param;
    }

    public void notifyListeners(WatchedEvent type){
        for(ZkListener listener: listeners)
            listener.dataEvent(type);
    }
}
