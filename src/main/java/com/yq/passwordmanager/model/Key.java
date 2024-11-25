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
public class Key {
    @TableId
    private Long keyId;
    private Long userId;
    private String rsaPrivateKey;
    private String rsaPublicKey;
    private String aesKey;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @TableField(fill = FieldFill.INSERT)
    private Timestamp keyCreatedTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp keyUpdatedTime;
}
