package com.meng.exapmle;

import com.meng.exapmle.agent.UserServiceImpl;
import javassist.*;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
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
        UserServiceImpl userService =  new UserServiceImpl();
        userService.addUser("meng1", "man");
        userService.getUser();
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


