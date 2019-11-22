package com.meng.exapmle;

import com.meng.exapmle.agentsimple.UserServiceImpl;
import org.junit.Test;

public class HelloAgentTest {

    @Test
    public void helloTest(){
        System.out.println("hello word!");
        new UserServiceImpl().getUser();
    }

}
