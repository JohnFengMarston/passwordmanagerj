package com.yq.passwordmanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yq.passwordmanager.mapper.PasswordMapper;
import com.yq.passwordmanager.model.Password;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class PasswordServiceImpl extends ServiceImpl<PasswordMapper, Password> implements PasswordService {
    @Override
    public Result<Boolean> addPassword(Password password) {
        
        return null;
    }

    @Override
    public Result<Boolean> updatePassword(Password password) {
        return null;
    }

    @Override
    public Result<Boolean> deletePassword(Password password) {
        return null;
    }

    @Override
    public Result<List<Password>> getPasswordsByUserId(Long userId) {
        return null;
    }

    @Override
    public Result<Password> getPasswordById(Long passwordId) {
        return null;
    }
}
