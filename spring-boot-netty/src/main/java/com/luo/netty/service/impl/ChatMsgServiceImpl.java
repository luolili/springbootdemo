package com.luo.netty.service.impl;

import com.luo.netty.dao.ChatMsgDao;
import com.luo.netty.entity.ChatMsg;
import com.luo.netty.service.ChatMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ChatMsg)表服务实现类
 *
 * @author makejava
 * @since 2020-04-05 11:33:40
 */
@Service("chatMsgService")
public class ChatMsgServiceImpl implements ChatMsgService {
    @Resource
    private ChatMsgDao chatMsgDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ChatMsg queryById(String id) {
        return this.chatMsgDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ChatMsg> queryAllByLimit(int offset, int limit) {
        return this.chatMsgDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param chatMsg 实例对象
     * @return 实例对象
     */
    @Override
    public ChatMsg insert(ChatMsg chatMsg) {
        this.chatMsgDao.insert(chatMsg);
        return chatMsg;
    }

    /**
     * 修改数据
     *
     * @param chatMsg 实例对象
     * @return 实例对象
     */
    @Override
    public ChatMsg update(ChatMsg chatMsg) {
        this.chatMsgDao.update(chatMsg);
        return this.queryById(chatMsg.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.chatMsgDao.deleteById(id) > 0;
    }
}