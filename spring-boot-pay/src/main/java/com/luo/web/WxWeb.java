package com.luo.web;

import com.luo.config.WechatConfig;
import com.luo.service.VideoService;
import com.luo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("api/wx")
public class WxWeb {
    @Resource
    WechatConfig wechatConfig;
    @Resource
    VideoService videoService;

    // 微信扫一扫跳转的 url
    @RequestMapping("login_url")
    public Result login_url(@RequestParam(value = "access_page") String accessPage) throws Throwable {
        String openRedirecturl = wechatConfig.getOpenRedirecturl();
        // 本 网站的 callback
        String callbackUrl = URLEncoder.encode(openRedirecturl, "GBK");

        String qrcodeUrl = String.format(WechatConfig.OPEN_QRCODE_URL, wechatConfig.getOpenAppid(), callbackUrl, accessPage);

        return Result.success(qrcodeUrl);
    }
}