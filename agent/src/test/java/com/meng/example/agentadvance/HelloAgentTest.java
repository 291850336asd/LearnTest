package com.meng.example.agentadvance;

import com.meng.example.agentsimple.UserServiceImpl;
import org.junit.Test;

public class HelloAgentTest {

    @Test
    public void helloTest(){
        System.out.println("hello word!");
        new UserServiceImpl().getUser();
    }

}
