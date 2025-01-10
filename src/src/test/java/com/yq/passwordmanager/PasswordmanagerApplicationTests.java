package com.yq.passwordmanager;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.baomidou.mybatisplus.core.toolkit.AES;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.security.PublicKey;


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
        String urlData = AES.encrypt("jdbc:mysql://192.168.44.128:3306/yq_password_manager?serverTimezone=UTC", randomKey);
        log.info("{}", urlData);
        String userNameData = AES.encrypt("HomeSkating", randomKey);
        log.info("{}", userNameData);
        String passwordData = AES.encrypt("x)4al2[W&@6Ido=", randomKey);
        log.info("{}", passwordData);
    }

    @Test
    void testRSAAndAES() {
        RSA rsa = new RSA();
        String privateKeyBase64 = rsa.getPrivateKeyBase64();
        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        log.info("privateKeyBase64:{}", privateKeyBase64);
// 构建
        cn.hutool.crypto.symmetric.AES aes = SecureUtil.aes(key);
        String encryptHex = aes.encryptHex(privateKeyBase64);
        log.info("encryptHex:{}", encryptHex);
        String decryptStr = aes.decryptStr(encryptHex);
        log.info("decryptStr:{}", decryptStr);
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("Hello World!", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
        log.info("{}", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));
    }
}
