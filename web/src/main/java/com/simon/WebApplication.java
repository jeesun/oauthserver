package com.simon;

import com.simon.service.QuartzJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.TimeZone;

@SpringBootApplication
@MapperScan(value = "com.simon.mapper")
@EnableAsync
//@EnableDiscoveryClient
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
}
