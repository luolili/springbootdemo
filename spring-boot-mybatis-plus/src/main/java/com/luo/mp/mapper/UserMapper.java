package com.luo.mp.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luo.mp.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.Constants.WRAPPER;

public interface UserMapper extends BaseMapper<User> {

    /**
     * ${ew.customSqlSegment} 固定写法
     * @SqlParser(filter = true) 这个sql 不加租户id
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    @Select("select * from mp_user ${ew.customSqlSegment}")
    List<User> selectAll(@Param(WRAPPER) Wrapper<User> wrapper);
}
