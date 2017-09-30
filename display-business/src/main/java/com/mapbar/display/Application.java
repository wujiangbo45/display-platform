package com.mapbar.display;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 程序的入口
 *
 * @author yinsihua
 * @version 1.0.0
 */
@SpringBootApplication
@EnableAspectJAutoProxy
//@EnableAutoConfiguration
@ComponentScan("com.mapbar.*")
//@EnableEurekaClient
@EnableScheduling
@MapperScan(basePackages = "com.mapbar.display.dao")
public class Application implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("启动完毕...");
	}

}