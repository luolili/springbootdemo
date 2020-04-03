package com.luo.web;

import com.luo.config.WechatConfig;
import com.luo.domain.Video;
import com.luo.service.VideoService;
import com.luo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class TestWeb {
    @Resource
    WechatConfig wechatConfig;
    @Resource
    VideoService videoService;
    @RequestMapping("test")
    public String test() {
        String appid = wechatConfig.getAppid();
        String appsecret = wechatConfig.getAppsecret();
        log.info("appid:{},{}", appid, appsecret);
        return "ggf";
    }

    @RequestMapping("list")
    public List<Video> list() {

        return videoService.findAll();
    }

    @RequestMapping("detail")
    public Result detail(int id) {
        Video video = videoService.findById(id);
        return Result.success(video);
    }

    @RequestMapping("edit")
    public Object edit(String title) {
        Video video = new Video();
        video.setId(2);
        video.setTitle("tetst");
        return videoService.update(video);
    }

}
