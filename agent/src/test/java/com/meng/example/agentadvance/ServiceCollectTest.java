package com.meng.example.agentadvance;

import com.meng.example.agentadvance.collects.ServiceCollect;
import javassist.ByteArrayClassPath;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ServiceCollectTest {

    private ApmContext context;
    private MockInstrumentation instrumentation;
    private ServiceCollect serviceCollect;


    @Ignore
    @Before
    public void init() {
        instrumentation = new MockInstrumentation();
        context = new ApmContext(null, instrumentation);
        serviceCollect = new ServiceCollect(context, instrumentation);
    }

    @Ignore
    @Test
    public void collectTest() throws IOException, CannotCompileException, NotFoundException {
        String name = "com.meng.example.agentadvance.TestServiceImpl";
        byte[] classBytes = serviceCollect.transform(ServiceCollectTest.class.getClassLoader(), name);
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new ByteArrayClassPath(name, classBytes));
        pool.get(name).toClass();
        new TestServiceImpl().getUser("111","dddd");
    }
}
