package com.test;

import com.meng.mybatis.Person;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;

public class PersonTest {

    @Test
    public void testMybatis() {
        String resource = "/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = getClass().getResourceAsStream(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Person person = session.selectOne(
                    "com.meng.mybatis.Person.selectPerson", 1);
            System.out.println(person);
        }
    }

    @Test
    public void parseConfiguration() {
        Configuration config = new Configuration();
        InputStream inputStream = getClass().getResourceAsStream("/person.xml");
        XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, config, "/person.xml",
                config.getSqlFragments());
        builder.parse();
        config.getMappedStatement("com.meng.mybatis.Person.selectPerson");

    }


    @Test
    public void test1() {
        System.out.println(selectPersonSql());
    }


    private String selectPersonSql() {
        return new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
            SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
            FROM("PERSON P");
            FROM("ACCOUNT A");
            INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
            INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
            WHERE("P.ID = A.ID");
            WHERE("P.FIRST_NAME like ?");
            OR();
            WHERE("P.LAST_NAME like ?");
            GROUP_BY("P.ID");
            HAVING("P.LAST_NAME like ?");
            OR();
            HAVING("P.FIRST_NAME like ?");
            ORDER_BY("P.ID");
            ORDER_BY("P.FULL_NAME");
        }}.toString();
    }

}
