package com.luo.web;

import com.luo.config.WechatConfig;
import com.luo.domain.Video;
import com.luo.service.VideoService;
import com.luo.util.QrcodeUtil;
import com.luo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
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

    @RequestMapping("qrcode")
    public Object createQrcode(HttpServletResponse resp) {
        String url = "http://tools.jb51.net/static/api/qrcode/api2.php?text=test&bg=ffffff&fg=000000&wk=000000&nk=000000&size=250&radio=1&level=M&logo=";
        try {
            QrcodeUtil.createQRCode(url, resp);
        } catch (Exception e) {

        }
        return null;
    }

    @RequestMapping("qrcode2")
    public Object createQrcodeFromFile(HttpServletResponse resp) {
        try {
            String filePath = "F:\\githubpro\\springbootdemo\\spring-boot-pay\\src\\main\\resources\\public\\qrcode.jpg";
            BufferedImage image = QrcodeUtil.scaleImage(filePath, 300, 300, false);
            QrcodeUtil.showQrcode(image, resp);
        } catch (Exception e) {

        }
        return null;
    }
}
