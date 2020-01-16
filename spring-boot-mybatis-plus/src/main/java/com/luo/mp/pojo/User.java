package com.luo.mp.pojo;


import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "mp_user")
public class User {

    //@TableId(value ="xx_id")
    private Long id;
    private Integer likedNum;
    //这个字段模糊查询
    @TableField(condition = SqlCondition.LIKE)
    private String username;
    private String password;//加密后save

    //@TableField("cc")
    private String phone;
    private Date created;
    private String salt;//密码的盐

    //排除非表字段,方法1
    //private transient String remark;
    //方法2
    //private static String remark;
    @TableField(exist = false)
    private String remark;
}
