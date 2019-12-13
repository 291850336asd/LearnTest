package com.meng.exapmle.zookeeper.watcher;

public class ZookeeperWatcherTest {
    static String root = "/meng";
    public static void main(String[] args) throws Exception {
        //建立watcher
        ZooKeeperWatcher zkWatch = new ZooKeeperWatcher();
        // Thread.sleep(1000);
        // 清理节点
        // zkWatch.deleteRecursive(root);
        if (null != zkWatch.addPersistent(root, System.currentTimeMillis() + "")) {
            Thread.sleep(1000);
            // 读取数据
            zkWatch.getData(root, true);
            // 读取子节点
            zkWatch.getChildren(root, true);//设置为false就不会监听了
            // 更新数据
            zkWatch.setData(root, System.currentTimeMillis() + "");
            Thread.sleep(1000);
            // 创建子节点
            zkWatch.addPersistent(root + "/1", System.currentTimeMillis() + "");
            Thread.sleep(1000);
            zkWatch.setData(root + "/1", System.currentTimeMillis() + "");
            zkWatch.deleteRecursive(root);

        }

    }
}
