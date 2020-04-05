package com.luo.netty.controller;

import com.luo.netty.entity.Users;
import com.luo.netty.service.UsersService;
import com.luo.netty.util.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Users)表控制层
 *
 * @author makejava
 * @since 2020-04-05 11:33:42
 */
@RestController
@RequestMapping("users")
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Users selectOne(String id) {
        return this.usersService.queryById(id);
    }

    @PostMapping("registOrLogin")
    public Result registOrLogin(@RequestBody Users user) {
        if (StringUtils.isEmpty(user.getUsername()) ||
                StringUtils.isEmpty(user.getPassword())) {
            return Result.error();
        }
        boolean existUser = usersService.queryByUsername(user.getUsername());
        if (existUser) {
            //登陆
            Users u = usersService.login(user.getUsername(), user.getPassword());
            if (u == null) {
                return Result.error("用户名或密码错误");
            }
        } else {
            //注册
            user.setFaceImgBig("");
            user.setFaceImg("");
            usersService.insert(user);
        }
        return Result.error();
    }

}