package com.meng.exapmle;

import com.meng.exapmle.agent.UserService;
import com.meng.exapmle.agent.UserServiceImpl;
import javassist.*;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class JavassistTest {

    @Test
    public void updateMethod() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = new ClassPool();
        pool.appendSystemPath();
        CtClass ctl = pool.get("com.meng.exapmle.agent.UserServiceImpl");
        CtField f = new CtField(pool.get(String.class.getName()), "abc", ctl);
        ctl.addField(f);
        CtMethod mehod = ctl.getDeclaredMethod("getUser");
        mehod.insertBefore("System.out.println(\"abc :\" + abc);");
        CtMethod mehod2 = ctl.getDeclaredMethod("addUser");
        mehod2.insertBefore("abc = $1;");
        ctl.toClass();

        File file = new File(System.getProperty("user.dir") + "/target/UserServiceImpl.class");
        file.createNewFile();
        Files.write(file.toPath(), ctl.toBytecode());
        com.meng.exapmle.agent.UserServiceImpl userService =  new com.meng.exapmle.agent.UserServiceImpl();
        userService.addUser("meng1", "man");
        userService.getUser();

        //尝试再次修改
        //如果一个CtClass对象通过writeFile（）,toClass（）或者toByteCode（）转换成class文件，
        // 那么javassist会冻结这个CtClass对象。后面就不能修改这个CtClass对象了。
        // 这样是为了警告开发者不要修改已经被JVM加载的class文件，因为JVM不允许重新加载一个类。

        System.out.println("-------------");
        //若想对CtClass对象进行修改，必须对其进行解冻，通过defrost()方法进行
        if(ctl.isFrozen()){
            ctl.defrost();
        }
        //再次修改方法
        mehod = ctl.getDeclaredMethod("getUser");
        mehod.insertBefore("System.out.println(\"abc :\" + abc);");
        System.out.println("---------");

        //同一个ClassLoader不能多次加载同一个类。 如果重复的加载同一个类 ，
        // 将会抛出  attempted  duplicate class definition for name: "com/meng/exapmle/agent/UserServiceImpl 异常。
        //  所以，在替换Class的时候，  加载该Class的ClassLoader也必须用新的。 
        //使用新的ClassLoader
        try {
            file = new File(System.getProperty("user.dir") + "/target/UserServiceImpl.class");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            Files.write(file.toPath(), ctl.toBytecode());
            NewClassLoader localClassLoader = new NewClassLoader();
            localClassLoader.setCodeByte(IOUtils.toByteArray(new FileInputStream(file)));
            Class serviceClass = localClassLoader.findClass(ctl.getName());
            //此处必须使用接口否则转化失败是因为classloader不用
            //java.lang.ClassCastException: com.meng.exapmle.agent.UserServiceImpl cannot be cast to com.meng.exapmle.agent.UserServiceImpl
            UserService userService1 = (UserService) serviceClass.newInstance();
            userService1.addUser("haha", "oo");
            userService1.getUser();
            System.out.println("----------");
            //反射调用
            Object userService2 =  serviceClass.newInstance();
            Method method = serviceClass.getMethod("addUser", String.class, String.class);
            method.invoke(userService2,"meng1", "man");
            method = serviceClass.getMethod("getUser");
            method.invoke(userService2);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = new ClassPool();
        pool.appendSystemPath();
        CtClass ctl = pool.get("com.meng.exapmle.agent.UserServiceImpl");
        CtMethod mehod = ctl.getDeclaredMethod("addUser");
        mehod.insertBefore("System.out.println($0);");
        mehod.insertBefore("System.out.println($1);");
        mehod.insertBefore("System.out.println($2);");
        mehod.insertBefore(" addUser2($$);");
        ctl.toClass();
        new UserServiceImpl().addUser("meng", "man");
    }
}


