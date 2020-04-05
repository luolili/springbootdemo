package com.luo.netty.controller;

import com.luo.netty.entity.MyFriends;
import com.luo.netty.service.MyFriendsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (MyFriends)表控制层
 *
 * @author makejava
 * @since 2020-04-05 11:33:42
 */
@RestController
@RequestMapping("myFriends")
public class MyFriendsController {
    /**
     * 服务对象
     */
    @Resource
    private MyFriendsService myFriendsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public MyFriends selectOne(String id) {
        return this.myFriendsService.queryById(id);
    }

}