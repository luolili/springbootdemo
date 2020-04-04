package com.luo.web;

import com.luo.config.WechatConfig;
import com.luo.domain.User;
import com.luo.domain.VideoOrder;
import com.luo.service.UserService;
import com.luo.service.VideoOrderService;
import com.luo.util.JWTUtil;
import com.luo.util.Result;
import com.luo.util.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

@Slf4j
@RestController
@RequestMapping("api/wx")
public class WxWeb {
    private static final String SUCCESS_RESULT_CODE = "SUCCESS";
    @Resource
    WechatConfig wechatConfig;
    @Resource
    VideoOrderService orderService;
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

        return Result.success("成功");
    }

    // 支付成功后，进入该接口
    @RequestMapping("/user/pay/callback")
    public Result wxPaycallback(@RequestParam String code,
                                HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ServletInputStream in = req.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
        StringBuffer sb = new StringBuffer();
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {

        } finally {
            in.close();
            reader.close();
        }
        Map<String, String> payCallbackMap = WXPayUtil.xmlToMap(sb.toString());
        SortedMap<String, String> sortedMap = WXPayUtil.getSortedMap(payCallbackMap);
        //验证签名
        boolean rightSign = WXPayUtil.checkSign(sortedMap, wechatConfig.getKey());
        if (rightSign) {
            if (SUCCESS_RESULT_CODE.equals(sortedMap.get("result_code"))) {
                //根据流水号 获取订单
                String outTradeNo = sortedMap.get("out_trade_no");
                VideoOrder order = orderService.findByOutTradeNo(outTradeNo);
                if (order == null) {
                    return Result.error("无该订单");
                }
                if (order.getState() == 0) {
                    VideoOrder videoOrder = new VideoOrder();
                    String openid = sortedMap.get("openid");
                    videoOrder.setOpenid(openid);
                    videoOrder.setOutTradeNo(outTradeNo);
                    videoOrder.setNotifyTime(new Date());
                    videoOrder.setState(1);
                    //更新状态
                    int row = orderService.updateState(videoOrder);
                    if (row == 1) {
                        resp.setContentType("text/xml");
                        resp.getWriter().write("success");
                    }

                }
            }
        }
        resp.setContentType("text/xml");
        resp.getWriter().write("fail");
        return Result.error();
    }
}