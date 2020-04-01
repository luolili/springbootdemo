package com.luo.web;

import com.google.common.util.concurrent.RateLimiter;
import com.luo.anno.AccessNum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;

@Controller
public class IndexController {

    private RateLimiter rateLimiter;

    @PostConstruct
    public void init() {
        //300 tps
        rateLimiter = RateLimiter.create(300);
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "be");
        return "index.btl";
    }

    @GetMapping("/test/{id}/{cid}")
    @AccessNum(prefix = "dd")
    public String test(@PathVariable("id") Long id, @PathVariable("cid") Long cid) {

        return "";
    }

    /**
     * 令牌桶只可控制总流量
     * 传统限流：1分钟限制调用多少次接口
     */
    @GetMapping("/rate")
    public String rateLimit() {
        if (!rateLimiter.tryAcquire()) {
            return "index.btl";
        }
        return "index.btl";
    }
}
