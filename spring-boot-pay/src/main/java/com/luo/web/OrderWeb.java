package com.luo.web;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    public Result saveOrder(@RequestParam(value = "video_id") Integer videoId, HttpServletRequest req, HttpServletResponse resp) throws Throwable {
        String ip = "1";
        //req.getAttribute("user_id");
        int userId = 1;
        VideoOrderDto orderDto = new VideoOrderDto();
        orderDto.setUserId(userId);
        orderDto.setVideoId(videoId);
        String codeUrl = orderService.save(orderDto);
        if (codeUrl == null || "".equals(codeUrl)) {
            return Result.error("链接无效");
        }
        // create qrcode
        try {
            //纠错等级
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //编码
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            //设置二维码类型，大小
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 400, 400, hints);
            ServletOutputStream out = resp.getOutputStream();
            //写入输出流
            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
        } catch (Exception e) {
            log.info("生成二维码失败");
        }

        return Result.success();
    }


}