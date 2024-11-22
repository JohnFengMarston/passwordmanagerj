package com.yq.passwordmanager.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {
    private static final Map<String, String[]> FILL_MAP = new HashMap<>();

    static {
        FILL_MAP.put("userCreatedTime", new String[]{"userCreatedTime", "userUpdatedTime"});
        FILL_MAP.put("passwordCreatedTime", new String[]{"passwordUpdatedTime", "passwordCreatedTime"});
        FILL_MAP.put("keyCreatedTime", new String[]{"keyCreatedTime", "keyUpdatedTime"});
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        for (Map.Entry<String, String[]> entry : FILL_MAP.entrySet()) {
            if (metaObject.hasGetter(entry.getKey())) {
                for (String field : entry.getValue()) {
                    this.strictInsertFill(metaObject, field, Timestamp.class, new Timestamp(System.currentTimeMillis()));
                }
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter("userUpdatedTime")) {
            this.strictUpdateFill(metaObject, "userUpdatedTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        }
        if (metaObject.hasGetter("passwordUpdatedTime")) {
            this.strictUpdateFill(metaObject, "passwordUpdatedTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        }
        if (metaObject.hasGetter("keyUpdatedTime")) {
            this.strictUpdateFill(metaObject, "keyUpdatedTime", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        }
    }
}
