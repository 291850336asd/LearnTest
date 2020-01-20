package com.meng.example.contract.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserInfo implements Serializable {

    private int userId;

    private String userName;

    private int sex;

    private List<Ad> ads;

    @Override
    public String toString() {
        return String.format(
                "{userId:%d,userName:'%s',sex:%d,users:[%s]}"
                ,this.userId
                ,this.userName
                ,this.sex
                ,this.ads.get(0).toString()
        );
    }

}
