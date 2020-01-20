package com.meng.example.contract.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Ad implements Serializable {

    private int id;

    private String address;

    @Override
    public String toString() {
        return String.format(
                "{id:%d,address:'%s'}"
                ,this.id
                ,this.address
        );
    }

}
