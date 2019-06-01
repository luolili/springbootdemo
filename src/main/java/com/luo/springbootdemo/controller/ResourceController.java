package com.luo.springbootdemo.controller;

import com.luo.springbootdemo.pojo.Resource;
import com.luo.springbootdemo.util.JSONResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    @Autowired
    private Resource resource;

    @RequestMapping("/getResource")
    public JSONResult say() {
        System.out.println(resource);
        Resource bean = new Resource();

        BeanUtils.copyProperties(resource, bean);
        return JSONResult.ok(bean);
    }
}
