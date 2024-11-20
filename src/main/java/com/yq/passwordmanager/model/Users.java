package com.yq.passwordmanager.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "yq_pm_users")
public class Users {
    @TableId
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPasswordHash;
    private Timestamp userCreatedTime;
    private Timestamp userUpdatedTime;
}
