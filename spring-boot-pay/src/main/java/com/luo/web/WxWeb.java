package com.luo.web;

import com.luo.config.WechatConfig;
import com.luo.domain.User;
import com.luo.service.UserService;
import com.luo.util.JWTUtil;
import com.luo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("api/wx")
public class WxWeb {
    @Resource
    WechatConfig wechatConfig;

    @Resource
    UserService userService;
    // 微信扫一扫跳转的 url
    @RequestMapping("login_url")
    public Result login_url(@RequestParam(value = "access_page") String accessPage) throws Throwable {
        String openRedirecturl = wechatConfig.getOpenRedirecturl();
        // 本 网站的 callback
        String callbackUrl = URLEncoder.encode(openRedirecturl, "GBK");

        String qrcodeUrl = String.format(WechatConfig.OPEN_QRCODE_URL, wechatConfig.getOpenAppid(), callbackUrl, accessPage);

        return Result.success(qrcodeUrl);
    }

    // 扫描成功后，进入该接口
    @RequestMapping("/user/callback")
    public Result wxcallback(@RequestParam String code, @RequestParam String state,
                             HttpServletResponse resp) throws Exception {
        User user = userService.saveUserFromWx(code);
        if (user != null) {
            //jwt
            String token = JWTUtil.genJWT(user);
            //跳转到本网站的页面
            String name = user.getName();
            name = URLEncoder.encode(name, "UTF-8");
            //state 需要加http://
            resp.sendRedirect(state + "?token=" + token + "&head_img="
                    + user.getHeadImg() + "&nickname=" + name);
        }


        return Result.error();
    }
}