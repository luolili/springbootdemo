package com.chat.imchat.mongo.service;

import com.chat.imchat.mongo.model.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户的服务/操作
 */
@Service
public class UserService {

    private final ConcurrentHashMap<String, User> users;


    public UserService() {
        users = new ConcurrentHashMap<>();
    }

    //添加新用户
    public boolean addUser(User user) {
        boolean isExist = users.containsKey(user.getUsername());
        if (isExist) {
            return false;
        }

        //将新的用户加入users
        users.put(user.getUsername(), user);
        return true;

    }

    //根据用户名获取该用户
    public User getByUsername(String username) {
        return users.get(username);
    }
}
