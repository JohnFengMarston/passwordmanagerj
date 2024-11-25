package com.yq.passwordmanager.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yq.passwordmanager.mapper.PasswordMapper;
import com.yq.passwordmanager.model.Key;
import com.yq.passwordmanager.model.Password;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.service.KeyService;
import com.yq.passwordmanager.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PasswordServiceImpl extends ServiceImpl<PasswordMapper, Password> implements PasswordService {
    private final KeyService keyService;

    @Autowired
    public PasswordServiceImpl(KeyService keyService) {
        this.keyService = keyService;
    }

    @Override
    public Result<Boolean> addPassword(Password password) {
        password.setPasswordValue(getDecryptPassword(password));
        boolean saveResult = save(password);
        if (BooleanUtil.isTrue(saveResult)) {
            return Result.success(saveResult, "添加密码成功!", null);
        }
        return Result.failure(saveResult, "添加密码失败！", null);
    }

    @Override
    public Result<Boolean> updatePassword(Password password) {
        if (BooleanUtil.isTrue(StrUtil.isNotEmpty(password.getNativePassword()))) {
            password.setPasswordValue(getDecryptPassword(password));
        }
        boolean updateResult = updateById(password);
        if (BooleanUtil.isTrue(updateResult)) {
            return Result.success(updateResult, "更新密码数据成功！", null);
        }
        return Result.failure(updateResult, "更新密码数据失败！", null);
    }

    @Override
    public Result<Boolean> deletePasswordByUserId(Long userId) {
        QueryWrapper<Password> queryWrapper = new QueryWrapper<Password>().eq("user_id", userId);
        boolean removeResult = remove(queryWrapper);
        if (BooleanUtil.isTrue(removeResult)) {
            return Result.success(removeResult, "删除密码数据成功!", null);
        }
        return Result.failure(removeResult, "删除密码数据失败!", null);
    }


    @Override
    public Result<List<Password>> getPasswordsByUserId(Long userId) {
        QueryWrapper<Password> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Password> passwordList = list(null, queryWrapper);
        if (BooleanUtil.isTrue(CollectionUtil.isNotEmpty(passwordList))) {
            return Result.success(passwordList, "查询密码集合成功！", null);
        }
        return Result.failure(passwordList, "查询密码列表失败！", null);
    }

    private String getNativePassword(Password password) {
        Key key = keyService.getKeyByUserId(password.getUserId()).getData();
        AES aes = new AES(Base64.decode(key.getAesKey()));
        String decryptAesStr = aes.decryptStr(key.getRsaPrivateKey(), CharsetUtil.CHARSET_UTF_8);
        RSA rsa = new RSA(decryptAesStr, null);
        return StrUtil.str(rsa.decrypt(password.getNativePassword(), KeyType.PrivateKey), CharsetUtil.CHARSET_UTF_8);
    }

    private String getDecryptPassword(Password password) {
        Result<Key> keyResult = keyService.getKeyByUserId(password.getUserId());
        Key key = keyResult.getData();
        RSA rsa = new RSA(null, key.getRsaPublicKey());
        byte[] encryptPassword = rsa.encrypt(StrUtil.bytes(password.getNativePassword(), CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        return Base64.encode(encryptPassword);
    }
}
