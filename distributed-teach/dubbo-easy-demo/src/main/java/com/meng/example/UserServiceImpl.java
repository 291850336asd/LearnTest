package com.meng.example;

public class UserServiceImpl implements UserService {
    public String getUserName(int userId) {
        return "user id " + userId;
    }
}
