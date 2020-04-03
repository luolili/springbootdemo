package com.luo.interceptor;

import com.google.gson.Gson;
import com.luo.util.JWTUtil;
import com.luo.util.Result;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    private static final Gson gson = new Gson();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null) {
            token = request.getParameter("token");
        }
        if (token != null) {
            Claims claims = JWTUtil.checkJWT(token);
            if (claims != null) {
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                request.setAttribute("user_id", id);
                request.setAttribute("name", name);
                return true;
            }

        }
        sendMsg(response, Result.error("please login first"));
        return false;
    }

    private void sendMsg(HttpServletResponse resp, Object obj) {
        resp.setContentType("application/json;charset=utf-8");
        try {
            PrintWriter writer = resp.getWriter();
            writer.println(gson.toJson(obj));
            writer.close();
            resp.flushBuffer();
        } catch (Exception e) {

        }

    }
}
