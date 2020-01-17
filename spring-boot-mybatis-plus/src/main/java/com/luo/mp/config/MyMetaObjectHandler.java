package com.luo.mp.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("created")) {
            setInsertFieldValByName("created", new Date(), metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("update---");
        setUpdateFieldValByName("updated", LocalDateTime.now(), metaObject);
    }
}