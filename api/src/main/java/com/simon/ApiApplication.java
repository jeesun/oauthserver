package com.simon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.simon.mapper")
@EnableAsync
//@EnableDiscoveryClient
public class ApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		SpringApplication.run(ApiApplication.class, args);
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

	@Override
	public void run(String... strings) throws Exception {

	}
}
