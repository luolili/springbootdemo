package com.luo.netty.controller;

import com.luo.netty.entity.ChatMsg;
import com.luo.netty.service.ChatMsgService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (ChatMsg)表控制层
 *
 * @author makejava
 * @since 2020-04-05 11:33:41
 */
@RestController
@RequestMapping("chatMsg")
public class ChatMsgController {
    /**
     * 服务对象
     */
    @Resource
    private ChatMsgService chatMsgService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ChatMsg selectOne(String id) {
        return this.chatMsgService.queryById(id);
    }

}