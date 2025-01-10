package com.yq.passwordmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yq.passwordmanager.mapper")
public class PasswordmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordmanagerApplication.class, args);
	}

}
