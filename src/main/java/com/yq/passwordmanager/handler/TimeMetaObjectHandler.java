package com.yq.passwordmanager.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasGetter("userCreateTime")) {
            this.strictInsertFill(metaObject, "userCreatedTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
            this.strictInsertFill(metaObject, "userUpdatedTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        }
        if (metaObject.hasGetter("passwordCreateTime")) {
            this.strictInsertFill(metaObject, "passwordCreatedTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
            this.strictInsertFill(metaObject, "passwordUpdatedTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter("userUpdateTime")) {
            this.strictUpdateFill(metaObject, "userUpdatedTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        }
        if (metaObject.hasGetter("passwordUpdateTime")) {
            this.strictInsertFill(metaObject, "passwordUpdatedTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        }

    }
}
