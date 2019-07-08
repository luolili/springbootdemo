package com.luo.domain;

import lombok.Data;

@Data
public class Student {
    private int id;

    private String name;

    private int age;

    //增加的字段，测试hibernate能否更新这各表
    private String email;


}
