package com.yq.passwordmanager;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class PasswordmanagerApplicationTests {

	@Test
	void contextLoads() {
		log.info("1");
	}
	@Test
	void generatePassword() {
		String content = "test中文";

// 随机生成密钥
		byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();

// 构建
		AES aes = SecureUtil.aes(key);

// 加密
		byte[] encrypt = aes.encrypt(content);
// 解密
		byte[] decrypt = aes.decrypt(encrypt);

// 加密为16进制表示
		String encryptHex = aes.encryptHex(content);
// 解密为字符串
		String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
		System.out.println(decryptStr);
	}
}
