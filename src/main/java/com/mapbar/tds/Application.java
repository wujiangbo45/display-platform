package com.mapbar.tds;

import com.mapbar.tds.common.GenericResponseBodyMethodProcessorRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * 程序的入口
 *
 * @author yinsihua
 * @version 1.0.0
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

	@Autowired
	GenericResponseBodyMethodProcessorRegister res;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		res.afterPropertiesSet();
		System.out.println("启动完毕...");
	}

}