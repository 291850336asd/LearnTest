package com.meng.exapmle.agent;


public class UserServiceImpl implements UserService{

    public void getUser() {
        System.out.println("raw getUser method");
    }

    public void  addUser(String name, String sex) {
        System.out.println("raw addUser method : " + name + " - " + sex);

    }
    public void addUser2(String name, String sex) {
        System.out.println("raw addUser2 method : " + name + " - " + sex);
    }
}
