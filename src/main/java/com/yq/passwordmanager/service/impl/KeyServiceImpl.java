package com.yq.passwordmanager.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yq.passwordmanager.mapper.KeyMapper;
import com.yq.passwordmanager.model.Key;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.model.Users;
import com.yq.passwordmanager.service.KeyService;
import com.yq.passwordmanager.service.UsersService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class KeyServiceImpl extends ServiceImpl<KeyMapper, Key> implements KeyService {
    private final UsersService usersService;

    @Override
    public Result<Boolean> addKeyByUserId(Long userId) {
        Users users = usersService.getById(userId);
        boolean nullResult = ObjectUtil.isNull(users);
        if (BooleanUtil.isTrue(nullResult)) {
            return Result.failure(nullResult, "数据库查无此用户", null);
        }
        Key key = new Key();
        key.setUserId(userId);
        RSA rsa = new RSA();
        key.setRsaPublicKey(rsa.getPublicKeyBase64());
        byte[] aesKeyBytes = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        key.setAesKey(Base64.encode(aesKeyBytes));
        AES aes = SecureUtil.aes(aesKeyBytes);
        byte[] rsaPrivateKeyBytes = aes.encrypt(rsa.getPrivateKeyBase64());
        key.setRsaPrivateKey(Base64.encode(rsaPrivateKeyBytes));
        boolean saveResult = save(key);
        if (BooleanUtil.isTrue(saveResult)) {
            return Result.success(saveResult, "用户秘钥插入新增成功！", null);
        }
        return Result.failure(saveResult, "用户秘钥插入新增失败！", null);
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
        QueryWrapper<Key> keyQueryWrapper = new QueryWrapper<>();
        keyQueryWrapper.eq("user_id", userId);
        Key key = getOne(keyQueryWrapper);
        boolean isNullResult = ObjectUtil.isNull(key);
        if (isNullResult) {
            return Result.failure(null, "查无该用户秘钥！", null);
        }
        return Result.success(key, "查询秘钥成功！", null);
    }
}
