package com.luo.mp.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Long id;
    private Integer likedNum;
    private String username;
    private String password;//加密后save

    private String phone;
    private Date created;
    private String salt;//密码的盐

}
