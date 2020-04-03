package com.luo.provider;

import com.luo.domain.VideoOrder;
import org.apache.ibatis.jdbc.SQL;

/**
 * 动态 sql
 */
public class VideoOrderProvider {

    public String updateVideoOrder(final VideoOrder order) {
        // 不更新的字段不传到 sql
        return new SQL() {{
            UPDATE("order");
            //条件动态配置
            if (order.getNickname() != null) {
                SET("nickname=#{nickname}");
            }
            if (order.getHeadImg() != null) {
                SET("head_img=#{headImg}");
            }

            if (order.getOutTradeNo() != null) {
                SET("out_trade_no=#{outTradeNo}");
            }
            if (order.getOpenid() != null) {
                SET("openid=#{openid}");
            }
            if (order.getState() != null) {
                SET("state=#{state}");
            }
            if (order.getTotalFee() != null) {
                SET("total_fee=#{totalFee}");
            }
            if (order.getVideoImg() != null) {
                SET("video_img=#{videoImg}");
            }
            if (order.getVideoTitle() != null) {
                SET("video_title=#{videoTitle}");
            }
            if (order.getVideoId() != null) {
                SET("video_id=#{videoId}");
            }
            if (order.getUserId() != null) {
                SET("user_id=#{userId}");
            }

            WHERE("id = #{id}");
        }}.toString();
    }
}
