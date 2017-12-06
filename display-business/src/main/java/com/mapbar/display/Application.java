package com.mapbar.display;

import com.mapbar.common.Const;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.stream.Collectors;
import java.util.stream.Stream;


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
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	@Autowired
	RedisTemplate<String,String> redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("启动完毕...");
		// 服务启动候删除redis key
		redisTemplate.delete(Stream.of(Const.WORK_ORDER_DATA_KEY_BY_DAY,Const.WORK_ORDER_DATA_KEY).collect(Collectors.toList()));
		/**
		 * 出现这个吉祥马就是程序启动成功的标识， 勿删 ！！
		 */
		logger.info(" application startup success !\n" +
				"                           _(\\_/) \n" +
				"                         ,((((^`\\\n" +
				"                        ((((  (6 \\ \n" +
				"                      ,((((( ,    \\\n" +
				"  ,,,_              ,(((((  /\"._  ,`,\n" +
				" ((((\\\\ ,...       ,((((   /    `-.-'\n" +
				" )))  ;'    `\"'\"'\"\"((((   (      \n" +
				"(((  /            (((      \\\n" +
				" )) |                      |\n" +
				"((  |        .       '     |\n" +
				"))  \\     _ '      `t   ,.')\n" +
				"(   |   y;- -,-\"\"'\"-.\\   \\/  \n" +
				")   / ./  ) /         `\\  \\\n" +
				"   |./   ( (           / /'\n" +
				"   ||     \\\\          //'|\n" +
				"   ||      \\\\       _//'||\n" +
				"   ||       ))     |_/  ||\n" +
				"   \\_\\     |_/          ||\n" +
				"   `'\"                  \\_\\\n" +
				"                        `'\"");
	}

}