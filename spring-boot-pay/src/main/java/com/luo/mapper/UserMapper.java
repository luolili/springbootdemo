package com.luo.mapper;

import com.luo.domain.User;
import com.luo.provider.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    User findById(Integer id);

    @Select("select * from user where openid = #{openid}")
    User findByOpenId(String openid);

    @UpdateProvider(type = UserProvider.class, method = "updateUser")
    int update(User user);

    @Delete("delete from  user where id = #{id}")
    int delete(Integer id);

    @Insert("insert into user (name,openid,city,head_img,phone,create_time,sex,sign) values (#{name},#{openid},#{city},#{headImg},#{phone},#{createTime},#{sex},#{sign})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(User user);


}
