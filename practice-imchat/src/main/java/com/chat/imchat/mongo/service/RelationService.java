package com.chat.imchat.mongo.service;

import com.chat.imchat.mongo.model.User;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class RelationService {

    private final ConcurrentHashMap<String, List<String>> relations;

    public RelationService() {
        relations = new ConcurrentHashMap<>();
    }

    @Autowired
    private UserService userService;

    //添加好友
    public boolean addFriend(String username, String friendName) {

        //-1 判断这个好友是否存在
        User user = userService.getByUsername(friendName);

        if (user == null) {
            log.info("该用户不存在", friendName);
            return false;
        }


        return true;

    }

    //建立关系：将好友加入你自己的好友列表中
    private void establishRelation(String username, String friendName) {
        //username的好友列表
        List<String> friends = relations.get(username);
        // 如果好友列表为空，初始化他
        if (friends == null) {
            friends = new ArrayList<>();
        }

        //将好友的名字加入到列表中
        friends.add(friendName);
        //建立关系:关系是双向的
        this.establishRelation(username, friendName);
        this.establishRelation(friendName, username);
    }

    //获取好友列表
    private List<User> listFriend(String username) {
        //准备一个空的好友对象列表
        List<User> users = new ArrayList<>();
        //好友的名字列表
        List<String> friends = relations.get(username);


        //如果有好友才便利加入
        if (friends != null) {
            for (String friend : friends) {
                User user = userService.getByUsername(friend);
                users.add(user);
            }
        }

        return users;
    }
}
