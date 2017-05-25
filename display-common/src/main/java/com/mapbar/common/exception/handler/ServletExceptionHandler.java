package com.mapbar.common.exception.handler;

import com.mapbar.common.exception.ErrorCode;
import com.mapbar.common.web.bind.GenericResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


/**
 * Servlet异常处理器
 * @author:
 * Created on 2016年3月30日 上午11:47:32
 * @modify author:修改人
 * Modify on 修改时间
*/
public class ServletExceptionHandler {
	/**
	 * 处理servlet异常，返回通用模型。
	 * @param request
	 * @param ex
	 * @param messageSource
	 * @return 不能识别的异常时返回null，否则返回通用模型
	 * @author:
	 * Created on 2016年3月30日 上午11:48:23
	 * @modify author:修改人
	 * Modify on 修改时间
	 */
	public static GenericResponse<?> handleHttpRequestException(HttpServletRequest request, ServletException ex, MessageSource messageSource) {
		GenericResponse<?> resp = null;
		
		if (ex instanceof HttpRequestMethodNotSupportedException) {
			HttpRequestMethodNotSupportedException methodEx = (HttpRequestMethodNotSupportedException)ex;

			String code = ErrorCode.HTTP_METHOD_NOT_SUPPORT;
			String[] supportMethods = methodEx.getSupportedMethods();//支持的方法
			String httpMethod = request.getMethod();//实际的方法
			String msg = messageSource.getMessage(code, new Object[] {supportMethods, httpMethod}, LocaleContextHolder.getLocale());
			resp = new GenericResponse<>(code,msg, null);
		} else if (ex instanceof UnsatisfiedServletRequestParameterException) {

			String code = ErrorCode.UNSATISFIED_REQ_PARAM;
			String msg = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
			resp = new GenericResponse<>(code, msg, null);
		}
		return resp;
	}
}
