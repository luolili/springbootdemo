package com.luo.mp.pojo;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "mp_user")
public class User {

    //@TableId(value ="xx_id")
    private Long id;
    private Long managerId;
    private Integer likedNum;
    //这个字段模糊查询
    @TableField(condition = SqlCondition.LIKE)
    private String username;
    private String password;//加密后save

    //@TableField("cc")
    private String phone;
    //自动填充
    @TableField(fill = FieldFill.INSERT)
    private Date created;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updated;
    private String salt;//密码的盐

    //排除非表字段,方法1
    //private transient String remark;
    //方法2
    //private static String remark;
    @TableField(exist = false)
    private String remark;

    private Integer version;
    @TableLogic
    //不查出他
    @TableField(select = false)
    private Integer deleted;
}
