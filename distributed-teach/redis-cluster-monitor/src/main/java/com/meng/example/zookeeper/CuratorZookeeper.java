package com.meng.example.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.WatchedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

/**
 * zookeeper
 */
public class CuratorZookeeper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorZookeeper.class);

    private static Charset charset = Charset.forName("utf-8");

    private CuratorFramework zkTools;

    private JedisGroups values;

    public CuratorZookeeper(String host,JedisGroups values){

        this.values = values;

        zkTools = CuratorFrameworkFactory.builder().connectString(host)
                // .namespace("")
                .retryPolicy(new RetryNTimes(2000, 20000)).build();
        zkTools.start();
        zkTools.getConnectionStateListenable().addListener(new MyConnectionStateListener());
    }

    public void addWatcher() throws Exception{
        String zkPath = values.getWatchPath() + "/" + values.getBusiness();
        ZooKeeperWatch watcher = new ZooKeeperWatch();
        zkTools.getChildren().usingWatcher(watcher).forPath(zkPath);
    }

    /**
     * 获取节点数据
     * @param path
     * @return
     * @throws Exception
     */
    private String getData(String path) throws Exception{
        if(!zkTools.getZookeeperClient().isConnected()){
            return null;
        }
        byte[] buffer = zkTools.getData().forPath(path);
        return new String(buffer, charset);
    }

    /**
     * 获取子节点
     * @return
     */
    public List<String> getChildrens(){
        String zkPath = values.getWatchPath() + "/" + values.getBusiness();
        List<String> path = null;
        try{
            path = zkTools.getChildren().forPath(zkPath);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        return path;
    }

    public class ZooKeeperWatch implements CuratorWatcher {
        @Override
        public void process(WatchedEvent event) throws Exception {
            LOGGER.info(" event type:"+ event.getType());
            addWatcher();
            values.notifyListeners(event);
        }
    }

    public class MyConnectionStateListener implements ConnectionStateListener{

        @Override
        public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
            LOGGER.info("listener entry:"+connectionState.name());
            if(connectionState == ConnectionState.CONNECTED || connectionState == ConnectionState.RECONNECTED){
                try{
                    addWatcher();
                    LOGGER.debug("add listener success");
                }catch (Exception e){
                    e.printStackTrace();
                    LOGGER.error(e.getMessage());;
                }
            }
        }
    }

}
