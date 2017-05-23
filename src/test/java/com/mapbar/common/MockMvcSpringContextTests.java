package com.mapbar.common;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * MVC mock测试基类。
 * @author:吴永奎
 * Created on 2016年1月20日 上午10:48:11
*/

public class MockMvcSpringContextTests {

	@Autowired
	WebApplicationContext webApplicationConnect;

	protected MockMvc mockMvc;


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
