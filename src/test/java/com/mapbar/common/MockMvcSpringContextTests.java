package com.mapbar.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mapbar.display.Application;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * MVC mock测试基类。
 * @author:吴永奎
 * Created on 2016年1月20日 上午10:48:11
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MockMvcSpringContextTests {

	@Autowired
	WebApplicationContext webApplicationConnect;

	protected MockMvc mockMvc;

	@Before
	public void setUp() throws JsonProcessingException {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();

	}

	//	protected void assertSuccess(String method, String paramJson) throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.post("/").param("param_json", paramJson).param("method", method))
//		.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
//		.andReturn();
//	}
	protected void assertSuccess() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.post("/").param("param_json", paramJson).param("method", method))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
//				.andReturn();
		mockMvc.perform(MockMvcRequestBuilders.get("/tds/test/").accept(MediaType.APPLICATION_JSON))
				.andReturn();
	}
	


}
