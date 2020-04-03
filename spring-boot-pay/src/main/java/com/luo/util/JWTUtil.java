package com.luo.util;

import com.luo.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {

    static final String subject = "pay";
    static final int expireTime = 1000 * 60;
    private static String app_secret = "pay";

    public static String genJWT(User user) {
        if ((user.getName() == null) || (user.getHeadImg() == null) || (user.getId() == null)) {
            return null;
        }
        String token = Jwts.builder().setSubject(subject)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("img", user.getHeadImg())
                .setIssuedAt(new Date())
                //60秒
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.ES256, app_secret).compact();
        return token;

    }

    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(app_secret).parseClaimsJws(token).getBody();

            return claims;
        } catch (Exception e) {
            return null;
        }

    }
}
