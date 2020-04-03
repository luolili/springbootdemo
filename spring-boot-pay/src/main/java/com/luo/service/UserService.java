package com.luo.service;

import com.luo.config.WechatConfig;
import com.luo.domain.User;
import com.luo.mapper.UserMapper;
import com.luo.mapper.VideoMapper;
import com.luo.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    WechatConfig wechatConfig;
    @Resource
    VideoMapper videoMapper;

    public User saveUserFromWx(String code) {
        String accessToeknUrl = String.format(WechatConfig.OPEN_ACCESS_TOKEN_URL, wechatConfig.getOpenAppid(), wechatConfig.openAppsecret, code);
        //调用 微信的接口
        Map<String, Object> map = HttpUtil.doGet(accessToeknUrl);
        if (map == null || map.isEmpty()) {
            return null;
        }
        //获取 openid access_token(目的是获取微信用户信息)
        String openId = (String) map.get("openid");
        String accessToken = (String) map.get("access_token");
        User dbUser = userMapper.findByOpenId(openId);
        if (dbUser != null) {
            return dbUser;
        }
        //获取微信用户信息
        String userInfoUrl = String.format(WechatConfig.OPEN_USER_INFO_URL, accessToken, openId);
        Map<String, Object> userMap = HttpUtil.doGet(userInfoUrl);
        if (userMap == null || userMap.isEmpty()) {
            return null;
        }
        //创建新用户
        //字符串字段 会乱码
        String nickname = (String) map.get("nickname");
        try {
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            log.info("解析失败");
        }
        String country = (String) map.get("country");
        String city = (String) map.get("city");
        String headimgUrl = (String) map.get("headimgUrl");
        // sex是double类型
        Double sexTmp = (Double) map.get("sex");
        int sex = sexTmp.intValue();
        User user = genUser(openId, nickname, city, headimgUrl, sex);
        userMapper.save(user);

        return user;
    }

    private User genUser(String openId, String nickname, String city, String headimgUrl, int sex) {
        User user = new User();
        user.setCity(city);
        user.setName(nickname);
        user.setHeadImg(headimgUrl);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());
        return user;
    }
}