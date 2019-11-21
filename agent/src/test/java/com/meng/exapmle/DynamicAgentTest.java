package com.meng.exapmle;


import org.junit.Ignore;
import org.junit.Test;
import com.sun.tools.attach.VirtualMachine;

import java.lang.management.ManagementFactory;


public class DynamicAgentTest {

    @Ignore
    @Test
    public void premainTest() {

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("输出进程ID："+ ManagementFactory.getRuntimeMXBean().getName());
        while (true) {
            Thread.sleep(100);
        }
    }

    @Ignore
    @Test
    public void agentAttach() throws Exception {
        String targetPid = "30344";
        VirtualMachine vm = VirtualMachine.attach(targetPid);
        vm.loadAgent(System.getProperty("user.dir") + "/target/agent-1.0-SNAPSHOT.jar",
                "toagent");
    }
}
