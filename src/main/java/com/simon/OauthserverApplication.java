package com.simon;

import com.netflix.discovery.shared.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
@EnableEurekaClient
public class OauthserverApplication {

	public static void main(String[] args) {
//		SpringApplication.run(OauthserverApplication.class, args);
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
}
