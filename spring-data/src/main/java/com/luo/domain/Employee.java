package com.luo.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Employee {

    @GeneratedValue
    @Id
    private Integer id;

    @Column(length = 12)
    @NotEmpty
    private String name;

    private Integer age;
    //会更新列，保留原来的数据
    @NotEmpty//这个注解不能让数据库里面的字段设置为not null
    private String email;




}
