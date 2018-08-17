package com.simon;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.simon.mapper")
//@EnableDiscoveryClient
public class OauthserverApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		SpringApplication.run(OauthserverApplication.class, args);
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
