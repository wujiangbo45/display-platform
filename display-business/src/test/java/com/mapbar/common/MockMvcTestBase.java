package com.mapbar.common;

import com.mapbar.display.Application;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/31 14:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebIntegrationTest("sql.xml.files.path=classpath*:/dynamicsql/*-dynamic.xml")
public class MockMvcTestBase {

    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    protected void assertSuccess(String urlTemplate,Map<String,String> requestMap) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(urlTemplate);
        for (Map.Entry<String,String> entry : requestMap.entrySet()) {
            builder.param(entry.getKey(),entry.getValue());
        }

        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
