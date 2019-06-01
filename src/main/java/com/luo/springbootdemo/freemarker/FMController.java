package com.luo.springbootdemo.freemarker;

import com.luo.springbootdemo.pojo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ftl")
public class FMController {


    @Autowired
    private Resource resource;

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("resource", resource);
        return "/hello";
    }

    @RequestMapping("center")
    public String center() {
        //map.addAttribute("resource", resource);
        return "center/center";
    }
}
