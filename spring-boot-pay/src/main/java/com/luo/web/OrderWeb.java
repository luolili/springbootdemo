package com.luo.web;

import com.luo.config.WechatConfig;
import com.luo.dto.VideoOrderDto;
import com.luo.service.UserService;
import com.luo.service.VideoOrderService;
import com.luo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
//@RequestMapping("/user/api/v1/order")
@RequestMapping("/api/v1/order")
public class OrderWeb {
    @Resource
    WechatConfig wechatConfig;

    @Resource
    UserService userService;
    @Resource
    VideoOrderService orderService;

    @RequestMapping("add")
    public Result saveOrder(@RequestParam(value = "video_id") Integer videoId, HttpServletRequest req) throws Throwable {

        String ip = "1";
        //req.getAttribute("user_id");
        int userId = 1;
        VideoOrderDto orderDto = new VideoOrderDto();
        orderDto.setUserId(userId);
        orderDto.setVideoId(videoId);

        orderService.save(orderDto);
        return Result.success();
    }


}