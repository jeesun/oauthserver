package com.simon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan("com.simon.mapper")
//@EnableDiscoveryClient
public class WebApplication implements CommandLineRunner {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		SpringApplication.run(WebApplication.class, args);
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

    @Override
    public void run(String... strings) throws Exception {

    }
}
