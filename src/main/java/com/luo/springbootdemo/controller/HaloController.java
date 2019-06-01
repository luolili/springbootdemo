package com.luo.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * note: classes must be under the package com.luo.springbootdemo
 *
 * if not ,it will occur the whitelable Page
 */
@RestController
public class HaloController {
    @RequestMapping("/halo")
    public Object sayHalo() {
        System.out.println("halo");

        return "halo spring boot";
    }
}
