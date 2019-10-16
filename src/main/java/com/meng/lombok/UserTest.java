package com.meng.lombok;

public class UserTest {

    //https://juejin.im/post/5a6eceb8f265da3e467555fe
    public static void main(String[] args) {
        User user = new User();
        user.setAge(1).setId("1");

        UserBuild userBuild = UserBuild.builder().name("s").age(1).id("1").build();
    }
}
