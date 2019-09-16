package com.simon;

import com.simon.common.utils.SpringBeanLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-05
 */
@SpringBootApplication
@MapperScan("com.simon.mapper")
@EnableAsync
@EnableOAuth2Client
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OldTaskApplication implements CommandLineRunner {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		ApplicationContext applicationContext = SpringApplication.run(OldTaskApplication.class, args);
		SpringBeanLoader.setApplicationContext(applicationContext);
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

	@Override
	public void run(String... strings) throws Exception {

	}
}