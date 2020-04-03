package com.luo.provider;

import com.luo.domain.User;
import org.apache.ibatis.jdbc.SQL;


/**
 * 动态 sql
 */
public class UserProvider {

    public String updateUser(final User user) {
        // 不更新的字段不传到 sql
        return new SQL() {{
            UPDATE("user");
            //条件动态配置
            if (user.getName() != null) {
                SET("name=#{name}");
            }
            if (user.getHeadImg() != null) {
                SET("head_img=#{headImg}");
            }

            if (user.getCity() != null) {
                SET("city=#{city}");
            }
            if (user.getOpenid() != null) {
                SET("openid=#{openid}");
            }
            if (user.getSign() != null) {
                SET("sign=#{sig}");
            }
            if (user.getSex() != null) {
                SET("sex=#{sex}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
