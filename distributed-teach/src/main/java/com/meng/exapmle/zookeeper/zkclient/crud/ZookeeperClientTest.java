package com.meng.exapmle.zookeeper.zkclient.crud;

public class ZookeeperClientTest {

    public static void main(String[] args) throws Exception {
        ZookeeperClient zookeeperClient=new ZookeeperClient();
        String path="/meng/user";
        /******************序列化***************/
        User user=new User();
        user.setUserid(1);
        user.setUserName("meng");
        zookeeperClient.addPersistent(path, ZkSerializable.objectToByte(user));
        user= (User) ZkSerializable.byteToObject(zookeeperClient.getData2(path));//序列化
        System.out.println(user.getUserName());
        System.out.println(zookeeperClient.getData(path));
        System.out.println(zookeeperClient.getChildren("/meng",null));
        System.out.println(zookeeperClient.getData(path));
        //zookeeperClient.delete(path);
        zookeeperClient.close();
    }

}
