package com.yq.passwordmanager.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yq.passwordmanager.mapper.UsersMapper;
import com.yq.passwordmanager.model.Result;
import com.yq.passwordmanager.model.Users;
import com.yq.passwordmanager.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户相关操作服务
 */
@Service
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
        boolean updateResult = updateById(users);
        if (BooleanUtil.isTrue(updateResult)) {
            return Result.success(updateResult, "修改用户信息成功", null);
        }
        return Result.failure(updateResult, "修改用户数据失败！", null);
    }
}
