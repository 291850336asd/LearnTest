package com.meng.lombok;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true) //使用链式创建
public class User {
    private String id;
    private String name;
    private Integer age;
}
