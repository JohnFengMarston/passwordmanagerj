package com.yq.passwordmanager.model;

import com.baomidou.mybatisplus.annotation.TableId;
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
    private Timestamp keyCreateTime;
    private Timestamp keyUpdateTime;
}
