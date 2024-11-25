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
public class Password {
    @TableId
    private Long passwordId;
    private Long userId;
    private String passwordCategory;
    private String websiteName;
    private String accountContent;
    private String passwordValue;
    private String passwordNote;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @TableField(fill = FieldFill.INSERT)
    private Timestamp passwordCreatedTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp passwordUpdatedTime;
    @TableField(exist = false)
    private String nativePassword;
}
