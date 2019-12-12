package com.meng.example.controller;


import com.alibaba.fastjson.JSON;
import com.meng.example.AppRegister;
import com.meng.example.NodeState;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    AppRegister zookeeperRegister=new AppRegister();

    @RequestMapping(value = "/list",produces="text/html;charset=utf-8")
    public String list(HttpServletRequest request){
        ZooKeeper zooKeeper=zookeeperRegister.getAppRegisterConnect(new Watcher() {
            public void process(WatchedEvent event) {

            }
        });

        try {
            List<String> data= zooKeeper.getChildren(AppRegister.ROOT,true);
            List<NodeState>  list=new ArrayList<NodeState>();
            for(String server:data){
               byte[] bytes= zooKeeper.getData(zookeeperRegister.ROOT+"/"+server,false,null);
                NodeState tlState=JSON.parseObject(new String(bytes, "utf-8"),NodeState.class);
                list.add(tlState);

            }
            System.out.println(list.size());
            request.setAttribute("servetList",list);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "admin";


    }

}
