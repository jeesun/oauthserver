package com.simon;

import com.simon.config.SimpleFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@SpringBootApplication
@EnableEurekaServer
@EnableZuulProxy
public class EurekaServerApplication implements CommandLineRunner {
	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		SpringApplication.run(EurekaServerApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

	}
}