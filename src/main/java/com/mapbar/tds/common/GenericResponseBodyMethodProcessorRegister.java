package com.mapbar.tds.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * GenericResponseBodyMethodProcessor注册器，将GenericResponseBodyMethodProcessor注册到RequestMappingHandlerAdapter
*/
@Component
public class GenericResponseBodyMethodProcessorRegister implements InitializingBean {

	@Autowired
	private RequestMappingHandlerAdapter adapter;
	@Override
	public void afterPropertiesSet() throws Exception {
		List<HttpMessageConverter<?>> messageConverters = adapter.getMessageConverters();
		List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>(adapter.getReturnValueHandlers());
		
		/**将GenericResponseBodyMethodProcessor注册到RequestMappingHandlerAdapter*/
		GenericResponseBodyMethodProcessor processor = new GenericResponseBodyMethodProcessor(messageConverters);
		returnValueHandlers.add(0, processor);
		adapter.setReturnValueHandlers(returnValueHandlers);
	}
}
