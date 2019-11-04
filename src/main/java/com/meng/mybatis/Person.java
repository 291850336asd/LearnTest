package com.meng.mybatis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

    private int personId;
    private int age;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + personId +
                ", age=" + age +
                '}';
    }
}
