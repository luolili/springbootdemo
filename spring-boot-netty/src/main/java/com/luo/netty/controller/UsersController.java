package com.luo.netty.controller;

import com.luo.netty.entity.Users;
import com.luo.netty.service.UsersService;
import com.luo.netty.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Users)表控制层
 *
 * @since 2020-04-05 11:33:42
 */
@RestController
@RequestMapping("u")
@Slf4j
public class UsersController {

    @Resource
    private UsersService usersService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("detail")
    public Users selectOne(String id) {
        return this.usersService.queryById(id);
    }

    @GetMapping("login")
    public Result registOrLogin() {
//        if (StringUtils.isEmpty(user.getUsername()) ||
//                StringUtils.isEmpty(user.getPassword())) {
//            return Result.error();
//        }
//        boolean existUser = usersService.queryByUsername(user.getUsername());
//        if (existUser) {
//            //登陆
//            Users u = usersService.login(user.getUsername(), user.getPassword());
//            if (u == null) {
//                return Result.error("用户名或密码错误");
//            }
//        } else {
//            //注册
//            user.setFaceImgBig("");
//            user.setFaceImg("");
//            user.setQrcode("");
//            Users res = usersService.insert(user);
//            UserVo userVo = new UserVo();
//            BeanUtils.copyProperties(res,userVo);
//            return Result.success(userVo);
//
//        }
        log.info("ii--------");
        return Result.success(new Users());
    }

}