package com.yq.passwordmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yq.passwordmanager.mapper.KeyMapper;
import com.yq.passwordmanager.model.Key;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.service.KeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KeyServiceImpl extends ServiceImpl<KeyMapper, Key> implements KeyService {
    @Override
    public Result<Boolean> addKeyByUserId(Long userId) {
        return null;
    }

    @Override
    public Result<Boolean> updateKeyByUserId(Key key) {
        return null;
    }

    @Override
    public Result<Boolean> deleteKeyByUserId(Long userId) {
        return null;
    }

    @Override
    public Result<Key> getKeyByUserId(Long userId) {
        return null;
    }
}
