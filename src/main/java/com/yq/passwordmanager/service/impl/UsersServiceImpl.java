package com.yq.passwordmanager.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yq.passwordmanager.mapper.UsersMapper;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.model.Users;
import com.yq.passwordmanager.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关操作服务
 */
@Service
@Slf4j
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Override
    public Result<Users> getUserById(long userId) {
        Users user = getById(userId);
        if (ObjectUtil.isNotNull(user)) {
            return Result.success(user, "成功查询到用户！", null);
        }
        return Result.failure(null, "查无此用户！", null);
    }

    @Override
    public Result<Boolean> addUser(Users users) {
        users.setUserPasswordHash(BCrypt.hashpw(users.getUserPassword(), BCrypt.gensalt()));
        boolean saveResult = save(users);
        if (BooleanUtil.isTrue(saveResult)) {
            return Result.success(saveResult, "新增用户成功！", null);
        }
        return Result.failure(null, "新增用户失败！", null);
    }

    @Override
    public Result<List<Users>> getUsers() {
        List<Users> users = list();
        if (ObjectUtil.isNotNull(users)) {
            return Result.success(users, "查询所有用户成功！", null);
        }
        return Result.failure(null, "表中无用户数据", null);
    }

    @Override
    public Result<Boolean> updateUser(Users users) {
        boolean checkPasswordResult = BCrypt.checkpw(users.getUserPassword(), users.getUserPasswordHash());
        if (BooleanUtil.isFalse(checkPasswordResult)) {
            users.setUserPasswordHash(BCrypt.hashpw(users.getUserPassword(), BCrypt.gensalt()));
        }
        boolean updateResult = updateById(users);
        if (BooleanUtil.isTrue(updateResult)) {
            return Result.success(updateResult, "修改用户信息成功", null);
        }
        return Result.failure(updateResult, "修改用户数据失败！", null);
    }

    @Override
    public Result<Boolean> deleteUser(long userId) {
        boolean deleteResult = removeById(userId);
        if (BooleanUtil.isTrue(deleteResult)) {
            return Result.success(deleteResult, "删除用户数据成功", null);
        }
        return Result.failure(deleteResult, "删除用户失败！", null);
    }
}
