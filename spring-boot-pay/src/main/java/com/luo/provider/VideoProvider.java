package com.luo.provider;

import com.luo.domain.Video;
import org.apache.ibatis.jdbc.SQL;


/**
 * 动态 sql
 */
public class VideoProvider {

    public String updateVideo(final Video video) {
        // 不更新的字段不传到 sql
        return new SQL() {{
            UPDATE("video");
            //条件动态配置
            if (video.getTitle() != null) {
                SET("title=#{title}");
            }
            if (video.getCoverImg() != null) {
                SET("cover_img=#{coverImg}");
            }

            if (video.getSummary() != null) {
                SET("summary=#{summary}");
            }
            if (video.getPoint() != null) {
                SET("point=#{point}");
            }
            if (video.getPrice() != null) {
                SET("price=#{price}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
