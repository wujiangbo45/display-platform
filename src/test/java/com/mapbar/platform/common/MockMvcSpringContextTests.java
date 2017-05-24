package com.mapbar.platform.common;


import com.mapbar.platform.Application;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * MVC mock测试基类。
 * @author
 * Created on 2016年1月20日 上午10:48:11
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@PropertySource(value = "classpath:application-test.properties")
public class MockMvcSpringContextTests {

//	@Autowired
	protected MockMvc mockMvc;

	protected void assertSuccess() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.post("/").param("param_json", paramJson).param("method", method))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
//				.andReturn();
		mockMvc.perform(MockMvcRequestBuilders.get("/tds/test/").accept(MediaType.APPLICATION_JSON))
				.andReturn();
	}

	@Ignore
	@Test
	public void test() throws Exception {
		System.out.println(111);
		assertSuccess();
	}

}
