package com.mapbar.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问记录器
 * @author: wujiangbo
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
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
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
