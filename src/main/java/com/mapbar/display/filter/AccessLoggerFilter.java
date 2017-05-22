package com.mapbar.display.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mapbar.display.util.JsonUtils;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 访问记录器
 * @author:wujiangbo
 * Created on 2016年3月11日 上午9:48:30
 * @modify author:修改人
 * Modify on 修改时间
*/
@Component
public class AccessLoggerFilter implements Filter {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String ACTION_PARAM_NAME = "token";


	private String actionParamName = ACTION_PARAM_NAME;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String actionName = filterConfig.getInitParameter("token");
		if (actionName != null) {
			actionParamName = actionName;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;

		String actionMethod = httpRequest.getParameter(actionParamName);
		String uri = httpRequest.getRequestURI();
		String httpMethod = httpRequest.getMethod();
		String addr = httpRequest.getRemoteAddr();
		logger.info("{} {} with [token: {}] from {}", httpMethod, uri, actionMethod, addr);
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
