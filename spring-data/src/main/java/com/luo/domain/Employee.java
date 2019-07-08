package com.luo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Employee {

    /**
     * @GeneratedValue 默认用的是oracle的自增主键，还会生成hibernate_sequence这张表
     * <prop key="hibernate.id.new_generator_mappings">false</prop>
     * 用IDENTITY就只有一张表生成，也是先了主键自增
     */
    //@GeneratedValue
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
