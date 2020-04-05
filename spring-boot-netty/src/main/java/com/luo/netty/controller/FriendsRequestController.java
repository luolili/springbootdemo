package com.luo.netty.controller;

import com.luo.netty.entity.FriendsRequest;
import com.luo.netty.service.FriendsRequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (FriendsRequest)表控制层
 *
 * @author makejava
 * @since 2020-04-05 11:33:42
 */
@RestController
@RequestMapping("friendsRequest")
public class FriendsRequestController {
    /**
     * 服务对象
     */
    @Resource
    private FriendsRequestService friendsRequestService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FriendsRequest selectOne(String id) {
        return this.friendsRequestService.queryById(id);
    }

}