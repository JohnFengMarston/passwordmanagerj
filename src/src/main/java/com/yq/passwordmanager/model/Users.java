package com.yq.passwordmanager.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    @TableId
    private Long userId;
    private String userName;
    private String userEmail;
    private String userPasswordHash;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @TableField(fill = FieldFill.INSERT)
    private Timestamp userCreatedTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp userUpdatedTime;
    @TableField(exist = false)
    private String userPassword;
    private String userAvatarUrl;
}
