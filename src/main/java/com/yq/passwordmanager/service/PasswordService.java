package com.yq.passwordmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yq.passwordmanager.model.Password;
import com.yq.passwordmanager.model.Result;

import java.util.List;

public interface PasswordService extends IService<Password> {
    Result<Boolean> addPassword(Password password);

    Result<Boolean> updatePassword(Password password);

    Result<Boolean> deletePassword(Password password);

    Result<List<Password>> getPasswordsByUserId(Long userId);

    Result<Password> getPasswordById(Long passwordId);
}
