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
public class MPDeleteTest {
    @Resource
    UserMapper userMapper;

    @Test
    public void del() {

        userMapper.deleteById(1);
    }


    @Test
    public void updateByWrapper2() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("username", "a").eq("password", "1").set("password", "122");
        int rows = userMapper.update(null, wrapper);
    }

    @Test
    public void updateByWrapperLmd() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(User::getUsername, "a").set(User::getPassword, "22");
        int rows = userMapper.update(null, lambdaUpdate);
    }
}