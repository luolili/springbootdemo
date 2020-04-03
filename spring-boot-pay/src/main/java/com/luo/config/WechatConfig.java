package com.luo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:application.properties")
public class WechatConfig {
    public static final String OPEN_QRCODE_URL = "https://mp.weixin.qq.com/connect/qrconnect?appid=%s&redirect_url=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";
    @Value("${wxpay.appid}")
    public String appid;
    @Value("${wxpay.appsecret}")
    public String appsecret;

    @Value("${wxopen.appsecret}")
    public String openAppid;
    @Value("${wxopen.appsecret}")
    public String openAppsecret;
    @Value("${wxopen.redirect_url}")
    public String openRedirecturl;
}
