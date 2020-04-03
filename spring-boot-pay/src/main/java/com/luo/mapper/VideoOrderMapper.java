package com.luo.mapper;

import com.luo.domain.VideoOrder;
import com.luo.provider.VideoOrderProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VideoOrderMapper {
    @Select("select * from video_order where del=0")
    List<VideoOrder> findAll();

    @Select("select * from video_order where id = #{id} and del=0")
    VideoOrder findById(Integer id);

    @Select("select * from video_order where user_id = #{userId} and del=0")
    List<VideoOrder> findByUserId(Integer userId);

    @Select("select * from video_order where out_trade_no = #{outTradeNo} and del=0")
    VideoOrder findByOutTradeNo(String outTradeNo);

    @UpdateProvider(type = VideoOrderProvider.class, method = "updateVideoOrder")
    int update(VideoOrder order);

    //更新状态
    @Update("update video_order set state=#{state},notify_time=#{notifyTime},openid=#{openid} where out_trade_no=#{outTradeNo}  and state=0 and del=0")
    int updateState(VideoOrder order);

    //logic del
    @Update("update video_order set del=1 where id = #{id} and user_id=#{userId}")
    int delete(@Param("id") Integer id, @Param("userId") Integer userId);

    @Insert("insert into video_order (nickname,openid,out_trade_no,video_id,user_id,create_time,total_fee,head_img,state,video_title,video_img,notify_time,del) values (#{nickname},#{openid},#{outTradeNo},#{videoId},#{userId},#{createTime},#{totalFee},#{headImg},#{videoTitle},#{videoImg},#{notifyTime},#{del})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(VideoOrder order);


}
