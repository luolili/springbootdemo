package com.luo.mp;

import com.luo.mp.mapper.UserMapper;
import com.luo.mp.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisplusTest {

    @Resource
    UserMapper userMapper;

    @Test
    public void list() {
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(4, users.size());

    }

    /**
     * EBUG==>  Preparing: INSERT INTO user ( id, username ) VALUES ( ?, ? )
     * DEBUG==> Parameters: 5(Long), xxc(String)
     * DEBUG<==    Updates: 1
     * row:1
     */
    @Test
    public void insert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("xxc");
        int rows = userMapper.insert(user);

        System.out.println("row:" + rows);


    }


}
