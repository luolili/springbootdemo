package com.luo.mapper;

import com.luo.domain.Video;
import com.luo.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VideoMapper {
    @Select("select * from video")
    List<Video> findAll();

    @Select("select * from video where id = #{id}")
    Video findById(Integer id);

    @UpdateProvider(type = VideoProvider.class, method = "updateVideo")
        //@Update("update  video set title = #{title} where id = #{id}")
    int update(Video video);

    @Delete("delete from  video where id = #{id}")
    int delete(Integer id);

    @Insert("insert into video (title,summary,cover_img,view_num,price,create_time,online,point) values (#{title},#{summary},#{coverImg},#{viewNum},#{price},#{createTime},#{online},#{point})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(Video video);


}
