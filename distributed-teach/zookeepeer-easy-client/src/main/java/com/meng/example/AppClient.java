package com.meng.example;

import com.alibaba.fastjson.JSON;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * client
 *
 */
public class AppClient implements Watcher
{
    AppRegister register = new AppRegister();
    ZooKeeper zooKeeper;
    private String clientName;

    void subscribe(String clientName){
        zooKeeper = register.getAppRegisterConnect(this);

        try {
            List<String> appInfos = zooKeeper.getChildren(AppRegister.ROOT, true);
            if(appInfos == null || appInfos.isEmpty()){
                throw new RuntimeException("没有注册服务");
            }

            for (String appItem : appInfos){
                byte[] itemBytes = zooKeeper.getData(AppRegister.ROOT + "/" + appItem, true, null);
                System.out.println("appitem:  "  + appItem + "  data: " + new String(itemBytes));
                NodeState nodeState = JSON.parseObject(new String(itemBytes), NodeState.class);
                if(nodeState != null && nodeState.getStatus().equals("init")){
                    nodeState.setClientName(clientName);
                    nodeState.setStatus("clientOK");
                    nodeState.setNode(appItem);
                    zooKeeper.setData(AppRegister.ROOT + "/" + appItem, JSON.toJSONString(nodeState).getBytes(),-1);
                } else {
                    System.out.println("没有找到注册的server");
                }
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        AppClient appClient = new AppClient();
        appClient.setClientName("我是client");
        appClient.subscribe("我是client");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.getType().getIntValue());
        if(event != null){
            Event.EventType eventType = event.getType();
            if(eventType == Event.EventType.NodeChildrenChanged){
                subscribe(getClientName());
            }
        }
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }
}
