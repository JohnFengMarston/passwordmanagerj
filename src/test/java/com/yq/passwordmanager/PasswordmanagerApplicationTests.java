package com.yq.passwordmanager;


import com.baomidou.mybatisplus.core.toolkit.AES;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@Slf4j
class PasswordmanagerApplicationTests {

    @Test
    void contextLoads() {
        log.info("1");
    }

    @Test
    void generatePassword() {
        // 生成16位随机AES密钥
        String randomKey = "4ycMF6ZnavvyuUDk";
        log.info("{}", randomKey);
// 使用随机密钥加密数据
        String urlData = AES.encrypt("jdbc:mysql://192.168.205.128:3306/yq_password_manager?serverTimezone=UTC", randomKey);
        log.info("{}", urlData);
        String userNameData = AES.encrypt("yiquangu", randomKey);
        log.info("{}", userNameData);
        String passwordData = AES.encrypt("x)4al2[W&@6Ido=", randomKey);
        log.info("{}", passwordData);
    }
}
