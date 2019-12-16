package com.meng.exapmle.zookeeper.zkclient.watcher;

import java.util.concurrent.TimeUnit;

public class ZkClientWatcherTest {
    public static void main(String[] args) throws InterruptedException {
        ZkClientWatcher zkClientWatche=new ZkClientWatcher();
        String path="/root";
        zkClientWatche.deleteRecursive(path);
        zkClientWatche.createPersistent(path,"hello");
        zkClientWatche.subscribe(path);
        zkClientWatche.subscribe2(path);
       // zkClientWatche.subscribe3(path);//需要启服务
       // Thread.sleep(Integer.MAX_VALUE);
        zkClientWatche.createPersistent(path+"/root2","word");
        TimeUnit.SECONDS.sleep(1);
        zkClientWatche.writeData(path,"hi");
        TimeUnit.SECONDS.sleep(1);
        //zkClientWatche.delete(path);//如果目录下有内容 不能删除 会报 Directory not empty for /root的异常
        zkClientWatche.deleteRecursive(path);
        TimeUnit.SECONDS.sleep(1); //这个main线程就结束

    }
}
