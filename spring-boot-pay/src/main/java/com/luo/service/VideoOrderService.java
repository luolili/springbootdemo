package com.luo.service;

import com.luo.config.WechatConfig;
import com.luo.domain.User;
import com.luo.domain.Video;
import com.luo.domain.VideoOrder;
import com.luo.dto.VideoOrderDto;
import com.luo.mapper.UserMapper;
import com.luo.mapper.VideoMapper;
import com.luo.mapper.VideoOrderMapper;
import com.luo.util.CommonUtil;
import com.luo.util.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Slf4j
@Service
public class VideoOrderService {
    @Resource
    VideoOrderMapper orderMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    VideoMapper videoMapper;
    @Resource
    WechatConfig wechatConfig;

    public List<VideoOrder> findAll() {
        return orderMapper.findAll();
    }

    public VideoOrder findById(Integer id) {
        return orderMapper.findById(id);
    }

    public int update(VideoOrder order) {
        return orderMapper.update(order);
    }

    public int delete(Integer id, Integer userId) {
        return orderMapper.delete(id, userId);
    }

    public int save(VideoOrderDto order) {
        Integer videoId = order.getVideoId();
        Integer userId = order.getUserId();
        Video video = videoMapper.findById(videoId);
        User user = userMapper.findById(userId);
        //生成订单
        Integer totoalFee = video.getPrice();
        String coverImg = video.getCoverImg();
        String videoTitle = video.getTitle();

        String name = user.getName();
        String headImg = user.getHeadImg();
        String openid = user.getOpenid();
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setTotalFee(totoalFee);
        videoOrder.setOutTradeNo(CommonUtil.genUUID());
        videoOrder.setVideoImg(coverImg);
        videoOrder.setVideoTitle(videoTitle);
        videoOrder.setUserId(userId);
        videoOrder.setNickname(name);
        videoOrder.setOpenid(openid);
        videoOrder.setHeadImg(headImg);
        videoOrder.setDel(0);
        videoOrder.setCreateTime(new Date());
        //未支付
        videoOrder.setState(0);
        orderMapper.save(videoOrder);
        unifyOrder(videoOrder);

        return orderMapper.save(order);
    }

    public String unifyOrder(VideoOrder order) {
        // 生成签名
        SortedMap<String, String> params = new TreeMap<>();
        // 公众号id,非开发平台id
        params.put("appid", wechatConfig.getAppid());
        params.put("mch_id", wechatConfig.getMchId());
        params.put("nonce_str", CommonUtil.genUUID());
        params.put("body", order.getVideoTitle());
        params.put("out_trade_no", order.getOutTradeNo());
        params.put("total_fee", String.valueOf(order.getTotalFee()));
        params.put("spbill_create_ip", order.getIp());
        params.put("notify_url", wechatConfig.getCallbackUrl());
        params.put("trade_type", "NATIVE");

        String sign = WXPayUtil.createSign(params, wechatConfig.getKey());
        params.put("sign", sign);
        try {

            String toXml = WXPayUtil.mapToXml(params);
        } catch (Exception e) {

        }
        // 统一下单

        return "";
    }
}
