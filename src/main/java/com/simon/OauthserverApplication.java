package com.simon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class OauthserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthserverApplication.class, args);
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
