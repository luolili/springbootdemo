package com.luo.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("err")
public class ErrorController {
    @RequestMapping("/error")
    public String error() {
        int m = 1 / 0;
        return "/error";
    }
}
