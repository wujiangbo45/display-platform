package com.mapbar.common.exception.http;

import com.mapbar.common.exception.ErrorCode;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.servlet.ServletException;


/**
 * Http消息传输异常，通常包装{@link SocketTimeoutException}, {@link ServletException}, {@link IOException}等传输相关异常，不含业务上的失败异常。
 * @see HttpBusinessFailureException
 * @author:wujiangbo
 * Created on 2016年3月7日 下午4:44:38
 * @modify author:修改人
 * Modify on 修改时间
*/
public class HttpMessageTransferException extends HttpRequestException {
	private static final long serialVersionUID = -211058368402942152L;

	public HttpMessageTransferException(Throwable cause) {
		super(ErrorCode.NETWORK_IO_ERR, cause);
	}
}
