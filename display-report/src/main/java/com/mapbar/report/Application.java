package com.mapbar.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 程序的入口
 *
 * @author yinsihua
 * @version 1.0.0
 */
@SpringBootApplication
@ComponentScan("com.mapbar.report")
public class Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	Test test;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
		test.test(args[0],args[1]);
		System.exit(0);
	}


}