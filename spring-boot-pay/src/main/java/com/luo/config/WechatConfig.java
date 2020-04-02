package com.luo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:application.properties")
public class WechatConfig {
    @Value("${wxpay.appid}")
    public String appid;
    @Value("${wxpay.appsecret}")
    public String appsecret;
}
