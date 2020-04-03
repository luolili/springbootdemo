package com.luo.web;

import com.luo.config.WechatConfig;
import com.luo.service.UserService;
import com.luo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/user/api/v1/order")
public class OrderWeb {
    @Resource
    WechatConfig wechatConfig;

    @Resource
    UserService userService;

    @RequestMapping("save")
    public Result saveOrder(@RequestParam(value = "access_page") String accessPage) throws Throwable {

        return Result.success();
    }

    
}