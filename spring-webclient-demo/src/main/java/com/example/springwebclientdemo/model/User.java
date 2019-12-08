package com.example.springwebclientdemo.model;

import lombok.Builder;
import lombok.Data;

/**
 * 到mongodb的bin目录下 执行 mongod --dbpath ../data/db
 * db文件需要在 data 里面自己新建
 * MongoDB starting : pid=12572 port=27017
 * dbpath=../data/db 64-bit host=P4KWS2IFJO6W25Q
 */
@Data
@Builder
public class User {

    private String id;
    private String name;
    private int age;
}
