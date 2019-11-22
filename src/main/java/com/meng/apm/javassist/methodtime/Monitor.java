package com.meng.apm.javassist.methodtime;

import javassist.*;

public class Monitor {

    public static void main(String[] args) {
        ClassPool pool = new ClassPool(true);
        try {
            CtClass targetClass = pool.get("com.meng.apm.javassist.methodtime.BitStringUtil");
            CtMethod addStringMethod = targetClass.getDeclaredMethod("addString");
            CtMethod agentMethod = CtNewMethod.copy(addStringMethod, "addString$agentsimple", targetClass, null);
            targetClass.addMethod(agentMethod);

            String agentSrc = "{"
                    + "long begin = System.nanoTime();"
                    + "Object result=addString$agentsimple($$);"
                    + "long end = System.nanoTime();"
                    + "System.out.println(end-begin);"
                    + "return result;"
                    + "}";
            addStringMethod.setBody(agentSrc);

//            String src="{"
//                    + "long begin = System.nanoTime();"
//                    + "Object result="+ addStringMethod.getName()+"$agentsimple($$);"
//                    + "long end = System.nanoTime();"
//                    + "System.out.println(end-begin);"
//                    + "return ($r)result;"
//                    + "}";
//            addStringMethod.setBody(src);
            targetClass.toClass();
            BitStringUtil util = new BitStringUtil();
            util.addString(1000);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }
}
