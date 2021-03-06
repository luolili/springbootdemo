package com.luo.netty.service.impl;

import com.luo.netty.dao.UsersDao;
import com.luo.netty.entity.Users;
import com.luo.netty.service.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Users)表服务实现类
 *
 * @author makejava
 * @since 2020-04-05 11:33:42
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersDao usersDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Users queryById(String id) {
        return this.usersDao.queryById(id);
    }

    @Override
    public boolean queryByUsername(String username) {
        return usersDao.queryByUsername(username) != null;
    }

    @Override
    public Users login(String username, String password) {
        Users u = usersDao.queryByUsername(username);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                return u;
            }
        }

        return null;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Users> queryAllByLimit(int offset, int limit) {
        return this.usersDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users insert(Users users) {
        this.usersDao.insert(users);
        return users;
    }

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users update(Users users) {
        this.usersDao.update(users);
        return this.queryById(users.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.usersDao.deleteById(id) > 0;
    }
}