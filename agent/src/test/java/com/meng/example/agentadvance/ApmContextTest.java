package com.meng.example.agentadvance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * -javaagent:E:\test\project\netty\agent\target\agent-1.0-SNAPSHOT.jar
 */
public class ApmContextTest {

    public static void main(String[] args) {
        TestServiceImpl service=new TestServiceImpl();
        service.getUser("111","hanmei");

        try {
            sqlTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sqlTest() throws Exception {
        String name = "com.mysql.jdbc.Driver";
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
