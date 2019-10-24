package com.meng.spring.test;


public class UserServiceImp implements UserService{
    private String name;

    private UserDao userDao;
    public UserServiceImp(){
        System.out.println("UserServiceImp()");
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
