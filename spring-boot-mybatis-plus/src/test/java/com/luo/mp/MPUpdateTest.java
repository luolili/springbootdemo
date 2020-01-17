package com.luo.mp;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.luo.mp.mapper.UserMapper;
import com.luo.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MPUpdateTest {
    @Resource
    UserMapper userMapper;

    @Test
    public void update() {
        User user = new User();
        user.setId(1L);
        user.setLikedNum(5);
        userMapper.updateById(user);
    }

    @Test
    public void updateByWrapper() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("username", "a").eq("password", "1");
        User user = new User();
        user.setLikedNum(52);
        int rows = userMapper.update(user, wrapper);
    }

    @Test
    public void updateByWrapper2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("username", "a").eq("password", "1").set("password", "2");
        int rows = userMapper.update(null, wrapper);
    }

    @Test
    public void updateByWrapperLmd() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(User::getUsername, "a").set(User::getPassword, "22");
        int rows = userMapper.update(null, lambdaUpdate);
    }
}