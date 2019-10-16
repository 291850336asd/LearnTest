package com.meng.lombok;


import lombok.Builder;
import lombok.experimental.Accessors;

@Accessors(chain = true) //使用链式创建
@Builder
public class UserBuild {
    private String id;
    private String name;
    private Integer age;
}
