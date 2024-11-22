package com.yq.passwordmanager.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("passwords")
public class Password {
    @TableId
    private Long passwordId;
    private Long userId;
    private String passwordCategory;
    private String websiteName;
    private String accountContent;
    private String passwordValue;
    private String passwordNote;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp passwordCreateTime;
    @TableField(fill = FieldFill.UPDATE)
    private Timestamp passwordUpdateTime;
    @TableField(exist = false)
    private String nativePassword;
}
