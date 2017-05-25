package com.mapbar.common.exception.http;

/**
 * Http请求异常。
 * 
 * @author:wujiangbo
 * Created on 2015年8月19日 下午1:37:34
*/
public abstract class HttpRequestException extends RuntimeException {
	
	private static final long serialVersionUID = -9047957431858124809L;
	
	/*错误码*/
	private String errCode;

	public HttpRequestException() {
		super();
	}

	public HttpRequestException(String errCode, Throwable cause) {
		super(cause);
		this.errCode = errCode;
	}

	public HttpRequestException(String errCode) {
		super();
		this.errCode = errCode;
	}
	
	public String getErrCode() {
		return errCode;
	}

}
