package com.luo.web;

import com.luo.anno.AccessNum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

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
}
