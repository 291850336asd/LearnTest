package com.meng.example.easy;

public class UserServiceImpl implements UserService {
    public String getUserName(int userId) {
        return "user id " + userId;
    }
}
