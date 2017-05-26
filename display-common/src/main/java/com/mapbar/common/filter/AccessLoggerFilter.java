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




	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
		String uri = httpRequest.getRequestURI();
		String httpMethod = httpRequest.getMethod();
		String addr = httpRequest.getRemoteAddr();
		logger.info("{} {}  from {}", httpMethod, uri, addr);
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
