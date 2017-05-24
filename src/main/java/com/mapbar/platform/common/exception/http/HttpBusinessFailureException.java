package com.mapbar.platform.common.exception.http;

import com.mapbar.platform.common.web.bind.GenericResponse;
import org.springframework.util.Assert;


/**
 * http请求业务失败异常，不含通信相关异常。
 * @see HttpMessageTransferException
 * @author:
 * Created on 2016年3月7日 下午4:47:58
 * @modify author:修改人
 * Modify on 修改时间
*/
public class HttpBusinessFailureException extends HttpRequestException {
	private static final long serialVersionUID = 7675385908698230661L;

	private GenericResponse<?> failureResponse;

	public HttpBusinessFailureException(GenericResponse<?> failureResponse) {
		super();
		Assert.notNull(failureResponse, "Business failure response must not null");
		this.failureResponse = failureResponse;
	}

	public GenericResponse<?> getFailureResponse() {
		return failureResponse;
	}
	
	@Override
	public String getMessage() {
		return "Business failure [" + failureResponse + "]";
	}
}
