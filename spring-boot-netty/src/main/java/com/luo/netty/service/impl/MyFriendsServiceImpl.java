package com.luo.netty.service.impl;

import com.luo.netty.dao.MyFriendsDao;
import com.luo.netty.entity.MyFriends;
import com.luo.netty.service.MyFriendsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MyFriends)表服务实现类
 *
 * @author makejava
 * @since 2020-04-05 11:33:42
 */
@Service("myFriendsService")
public class MyFriendsServiceImpl implements MyFriendsService {
    @Resource
    private MyFriendsDao myFriendsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public MyFriends queryById(String id) {
        return this.myFriendsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<MyFriends> queryAllByLimit(int offset, int limit) {
        return this.myFriendsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param myFriends 实例对象
     * @return 实例对象
     */
    @Override
    public MyFriends insert(MyFriends myFriends) {
        this.myFriendsDao.insert(myFriends);
        return myFriends;
    }

    /**
     * 修改数据
     *
     * @param myFriends 实例对象
     * @return 实例对象
     */
    @Override
    public MyFriends update(MyFriends myFriends) {
        this.myFriendsDao.update(myFriends);
        return this.queryById(myFriends.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.myFriendsDao.deleteById(id) > 0;
    }
}