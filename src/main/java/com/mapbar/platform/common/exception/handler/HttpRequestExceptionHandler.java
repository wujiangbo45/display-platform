package com.mapbar.platform.common.exception.handler;

import javax.servlet.http.HttpServletRequest;

import com.mapbar.platform.common.exception.http.HttpBusinessFailureException;
import com.mapbar.platform.common.exception.http.HttpMessageTransferException;
import com.mapbar.platform.common.exception.http.HttpRequestException;
import com.mapbar.platform.common.exception.http.HttpRequestNotSuccessException;
import com.mapbar.platform.common.web.bind.GenericResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


/**
 * Http请求异常处理器
 * @author:wujiangbo
 * Created on 2016年3月30日 上午11:47:32
 * @modify author:修改人
 * Modify on 修改时间
*/
public class HttpRequestExceptionHandler {
	/**
	 * 处理http请求异常，返回通用模型。
	 * @param request
	 * @param ex
	 * @param messageSource
	 * @return 不能识别的异常时返回null，否则返回通用模型
	 * @author:
	 * Created on 2016年3月30日 上午11:48:23
	 * @modify author:修改人
	 * Modify on 修改时间
	 */
	public static GenericResponse<?> handleHttpRequestException(HttpServletRequest request, HttpRequestException ex, MessageSource messageSource) {
		GenericResponse<?> resp = null;
		
		if (ex instanceof HttpBusinessFailureException) {
			HttpBusinessFailureException businessException = (HttpBusinessFailureException)ex;
			resp = businessException.getFailureResponse();//直接返回错误的业务响应
		} 
		else if (ex instanceof HttpMessageTransferException) {
			String code = ex.getErrCode();
			String msg = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
			resp = new GenericResponse<>(code, msg, null);
		} 
		else if (ex instanceof HttpRequestNotSuccessException) {
			HttpRequestNotSuccessException notSuccessException = (HttpRequestNotSuccessException)ex;
			String code = notSuccessException.getErrCode();
			String msg = messageSource.getMessage(code, new Object[] {notSuccessException.getStatusCode(), notSuccessException.getReasonPhrase()} , LocaleContextHolder.getLocale());
			resp = new GenericResponse<>(code, msg, null);
		}
		return resp;
	}
}
