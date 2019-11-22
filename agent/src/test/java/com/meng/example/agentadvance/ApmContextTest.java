package com.meng.example.agentadvance;

/**
 * -javaagent:E:\test\project\netty\agent\target\agent-1.0-SNAPSHOT.jar
 */
public class ApmContextTest {

    public static void main(String[] args) {
        TestServiceImpl service=new TestServiceImpl();
        service.getUser("111","hanmei");
    }

}
