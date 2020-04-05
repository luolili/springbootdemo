package com.luo.netty.service.impl;

import com.luo.netty.dao.FriendsRequestDao;
import com.luo.netty.entity.FriendsRequest;
import com.luo.netty.service.FriendsRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (FriendsRequest)表服务实现类
 *
 * @author makejava
 * @since 2020-04-05 11:33:42
 */
@Service("friendsRequestService")
public class FriendsRequestServiceImpl implements FriendsRequestService {
    @Resource
    private FriendsRequestDao friendsRequestDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FriendsRequest queryById(String id) {
        return this.friendsRequestDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<FriendsRequest> queryAllByLimit(int offset, int limit) {
        return this.friendsRequestDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param friendsRequest 实例对象
     * @return 实例对象
     */
    @Override
    public FriendsRequest insert(FriendsRequest friendsRequest) {
        this.friendsRequestDao.insert(friendsRequest);
        return friendsRequest;
    }

    /**
     * 修改数据
     *
     * @param friendsRequest 实例对象
     * @return 实例对象
     */
    @Override
    public FriendsRequest update(FriendsRequest friendsRequest) {
        this.friendsRequestDao.update(friendsRequest);
        return this.queryById(friendsRequest.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.friendsRequestDao.deleteById(id) > 0;
    }
}