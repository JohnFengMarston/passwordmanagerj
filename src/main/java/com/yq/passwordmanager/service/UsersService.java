package com.yq.passwordmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.model.Users;

import java.util.List;

public interface UsersService extends IService<Users> {
    Result<Users> getUserById(long userId);

    Result<Boolean> addUser(Users users);

    Result<List<Users>> getUsers();

    Result<Boolean> updateUser(Users users);

    Result<Boolean> deleteUser(long userId);
}
