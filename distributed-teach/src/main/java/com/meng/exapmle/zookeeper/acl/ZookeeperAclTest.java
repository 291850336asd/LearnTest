package com.meng.exapmle.zookeeper.acl;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperAclTest {
    final static Logger logger = LoggerFactory.getLogger(ZookeeperAclTest.class);

    private static String connectString = "192.168.163.128:2181";

    public static void main(String[] args) throws Exception {

        ZookeeperAcl zookeeperAcl=new ZookeeperAcl(new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
        String path="/meng";
        zookeeperAcl.addPersistent2(path,"meng");
        System.out.println(zookeeperAcl.getData(path));

        try {
            //不用授权
            ZooKeeper zk1 = new ZooKeeper(connectString, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                }
            });
            Thread.sleep(2000);
            logger.info("不用授权:"+ zk1.getData(path, false, null));
            zk1.close();
        } catch (Exception e) {
            logger.info("不用授权:"+e.getMessage());
            e.printStackTrace();
        }


        try {
            //授权
            logger.info("正确授权获取数据"+zookeeperAcl.getData(path));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            //错误授权
            ZooKeeper zk1 = new ZooKeeper(connectString, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                }
            });
            zk1.addAuthInfo("digest","123".getBytes());
            Thread.sleep(2000);
            logger.info("错误授权:"+ zk1.getData(path, false, null));
            zk1.close();
        } catch (Exception e) {
            logger.info("错误授权:"+ e.getMessage());
        }

        try {
            //正确授权
            ZooKeeper zk1 = new ZooKeeper(connectString, 5000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                }
            });
            zk1.addAuthInfo("digest","111".getBytes());
            Thread.sleep(2000);
            logger.info("正确授权："+ zk1.getData(path, false, null));
            zk1.close();
        } catch (Exception e) {
            logger.info("正确授权："+ e.getMessage());
        }


        zookeeperAcl.delete(path);
        zookeeperAcl.close();
    }
}