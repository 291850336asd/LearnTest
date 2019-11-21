package com.meng.exapmle;

public class NewClassLoader extends ClassLoader {


    private byte[] codeByte;

    public void setCodeByte(byte[] codeByte) {
        this.codeByte = codeByte;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> cls = findLoadedClass(name);
        if (cls != null) {
            return cls;
        }
        try {
            byte[] bytes = codeByte;
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.loadClass(name);
    }
}
