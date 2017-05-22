package com.mapbar.display;

import com.mapbar.display.filter.AccessLoggerFilter;
import com.mapbar.display.common.servlet.GenericResponseBodyMethodProcessorRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
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

	@Autowired
	AccessLoggerFilter filter;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		res.afterPropertiesSet();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(filter);
		System.out.println("启动完毕...");
	}

}