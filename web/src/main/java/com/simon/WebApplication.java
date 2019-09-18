package com.simon;

import com.simon.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;

/**
 * @author simon
 * @version 1.0
 * @date 2019-09-03
 */
@SpringBootApplication
@MapperScan(value = "com.simon.mapper")
@EnableAsync
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableEurekaClient
public class WebApplication implements CommandLineRunner {
	@Autowired
	private QuartzJobService quartzJobService;

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		SpringApplication.run(WebApplication.class, args);
//		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

    @Override
    public void run(String... strings) throws Exception {
		//项目启动时启动定时任务
		//quartzJobService.runJobsOnStart();
    }

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		//解决RestTemplate使用PATCH方法报错
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(5000);
		requestFactory.setReadTimeout(5000);

		restTemplate.setRequestFactory(requestFactory);
		return restTemplate;
	}
}
