package com.luo.springbootdemo.controller;

import com.luo.springbootdemo.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private StringRedisTemplate strRedis;

    @RequestMapping("/test")
    public JSONResult test() {
        strRedis.opsForValue().set("name-cache", "did");
        return JSONResult.ok(strRedis.opsForValue().get("name-cache"));
    }


}
