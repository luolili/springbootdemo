package com.luo.web;

import com.luo.entity.User;
import com.luo.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("redis")
@Slf4j
public class RedisController {

    private static final int expire_time = 70;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    ArticleService articleService;

    @GetMapping("test")
    public void set() {
        String key = "user";
        User user = getUser();
        redisTemplate.opsForValue().set(key, user);
        User u = (User) redisTemplate.opsForValue().get(key);
        log.info("user name:{}", user.getName());
    }

    @GetMapping("expire")
    public void expire() {
        String key = "user";
        User user = getUser();
        redisTemplate.opsForValue().set(key, user);
        User u = (User) redisTemplate.opsForValue().get(key);
        redisTemplate.expire(key, expire_time, TimeUnit.SECONDS);
        log.info("user name:{}", u.getName());
    }

    @GetMapping("rank")
    public void rank() {
        articleService.sortByLikedNum();
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setName("yu");
        return user;
    }


}
