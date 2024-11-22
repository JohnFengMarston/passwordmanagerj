package com.yq.passwordmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yq.passwordmanager.model.Key;
import com.yq.passwordmanager.model.Result;

public interface KeyService extends IService<Key> {
    Result<Boolean> addKeyByUserId(Long userId);

    Result<Boolean> updateKeyByUserId(Key key);

    Result<Boolean> deleteKeyByUserId(Long userId);

    Result<Key> getKeyByUserId(Long userId);
}
