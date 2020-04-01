package com.luo.web;

import com.luo.vo.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * time 的传递问题
 * post测试必须在 Header里面加Content-Type application/json
 * RequestBody Text:必须 用{"name":"yy"}
 */
@Controller
@RequestMapping("time")
@Slf4j
public class TimeController {

    /**
     * get方式
     *
     * @param time
     * @return
     */
    @GetMapping("/get")
    public String time_get(Model model, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time) {
        model.addAttribute("name", "be");
        log.info("time:{}", time);
        return "index.btl";
    }

    @PostMapping("/post")
    public String time_post(Model model, @RequestBody TestVo testVo) {
        model.addAttribute("name", "be");
        log.info("time:{}", testVo.getTime());
        return "index.btl";
    }
}
