package com.luo.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ajaxErr")
public class AjaxErrorController {
    @RequestMapping("/ajaxErr")
    public String ajaxError() {
        return "/ajaxErro";
    }
}
