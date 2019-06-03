package com.luo.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("one")
public class OneController {
    @RequestMapping("/hello")
    public String index() {
        return "/hello";
    }
}
