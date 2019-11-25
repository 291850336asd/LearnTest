package com.meng.example.agentadvance;

import com.meng.example.agentadvance.collects.JdbcCommonCollects;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcCollectTest {

    private ApmContext context;
    private MockInstrumentation instrumentation;
    private JdbcCommonCollects jdbcCollect;

    @Ignore
    @Before
    public void init() {
        instrumentation = new MockInstrumentation();
        context = new ApmContext(null, instrumentation);
        jdbcCollect = new JdbcCommonCollects(context, instrumentation);
    }

    @Ignore
    @Test
    public void sqlTest() throws Exception {
        String name = "com.mysql.jdbc.NonRegisteringDriver";
        byte[] classBytes = jdbcCollect.transform(
                ServiceCollectTest.class.getClassLoader(), name);
        ClassPool pool = new ClassPool();
        pool.insertClassPath(new ByteArrayClassPath(name, classBytes));
        pool.get(name).toClass();
        Class.forName(name);
        Connection conn = DriverManager
                .getConnection(
                        "jdbc:mysql://192.168.163.128:3306/test??useUnicode=true&;characterEncoding=UTF8",
                        "root", "root");
        PreparedStatement statment = conn
                .prepareStatement("select * from member_xp limit 1");
        statment.executeQuery();
        statment.close();
        conn.close();
    }

}
