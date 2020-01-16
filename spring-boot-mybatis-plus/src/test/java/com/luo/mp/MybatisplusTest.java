package com.luo.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.luo.mp.mapper.UserMapper;
import com.luo.mp.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void selectById() {
        User user = userMapper.selectById(1L);
        System.out.println("user:" + user);
    }

    @Test
    public void selectByIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L));
        users.stream().forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "a");
        map.put("liked_num", 1);
        List<User> users = userMapper.selectByMap(map);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void selectByMap2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("username", "a");
        map.put("liked_num", 1);
        wrapper.allEq(map);
        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void selectByMap3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("username", "a");
        map.put("liked_num", 1);
        wrapper.allEq(map);
        List<Map<String, Object>> users = userMapper.selectMaps(wrapper);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void selectByWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String username = "";
        wrapper.like("username", "c").lt("liked_num", 2).isNotNull("id").orderByAsc("liked_num");
        //username不为空的时候才加入 到sql
        wrapper.like(StringUtils.isNotEmpty(username), "username", "c").lt("liked_num", 2).isNotNull("id").orderByAsc("liked_num");

        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void selectByWrapperPlus() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("username", "password").like("username", "c").lt("liked_num", 2).isNotNull("id").orderByAsc("liked_num");
        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
    }

    /**
     * 排除掉少数不需要的字段
     */
    @Test
    public void selectByWrapperPlus2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("username", "c").lt("liked_num", 2).isNotNull("id").orderByAsc("liked_num").select(User.class, info -> !info.getColumn().equalsIgnoreCase("created") && !info.getColumn().equalsIgnoreCase("password"));
        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.apply("date_format(created,'%Y-%m-%d')={0}", "2020-01-16");
        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
    }

    /**
     * add
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username", "x").and(wq -> wq.gt("liked_num", 1).isNotNull("id"));
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println("user:" + user);
        }
    }

    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username", "x").or(wq -> wq.gt("liked_num", 1).isNotNull("id"));
        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //nest加括号()
        wrapper.nested(wq -> wq.gt("liked_num", 1).isNotNull("id")).likeRight("username", "x");
        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //in
        wrapper.in("liked_num", Arrays.asList(1, 11));
        List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println("user:" + user);
        }
    }

    @Test
    public void selectByWrapper7() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //in
        wrapper.in("liked_num", Arrays.asList(1, 11)).last("limit 2");
        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
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
        user.setUsername("mp");
        user.setRemark("v");
        int rows = userMapper.insert(user);
        System.out.println("row:" + rows);

    }

    @Test
    public void selectByWrapperLmda() {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        //防止 列名写错
        wrapper.like(User::getUsername, "x").lt(User::getLikedNum, 2);
        List<User> users = userMapper.selectList(wrapper);
        users.stream().forEach(System.out::println);
    }

    @Test
    public void selectByWrapperLmda2() {
        LambdaQueryChainWrapper<User> wrapper = new LambdaQueryChainWrapper<User>(userMapper);
        wrapper.like(User::getUsername, "x").lt(User::getLikedNum, 2).list().stream().forEach(System.out::println);
        //List<User> users = userMapper.selectList(wrapper);
        //users.stream().forEach(System.out::println);
    }
}
