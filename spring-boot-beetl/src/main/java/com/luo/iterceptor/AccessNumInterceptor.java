package com.luo.iterceptor;

import com.luo.anno.AccessNum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class AccessNumInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {

            HandlerMethod method = (HandlerMethod) handler;
            if (method.hasMethodAnnotation(AccessNum.class)) {

                AccessNum accessNum = method.getMethodAnnotation(AccessNum.class);
                String prefix = accessNum.prefix();
                Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                String id = map.get("id");
                String cid = map.get("cid");
                log.info("id:{},cid:{}", id, cid);

            }
        }
        return true;
    }
}
