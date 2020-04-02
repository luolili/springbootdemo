package com.luo.web;

import com.luo.config.WechatConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TestWeb {
    @Resource
    WechatConfig wechatConfig;

    @RequestMapping("test")
    public String test() {
        String appid = wechatConfig.getAppid();
        String appsecret = wechatConfig.getAppsecret();

        log.info("appid:{},{}", appid, appsecret);
        return "ggf";
    }
}
