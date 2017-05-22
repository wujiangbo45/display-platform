package com.mapbar.display.exception;


/**
 * 返回状态码非200，抛出此异常。
 * @author:吴永奎
 * Created on 2016年3月7日 下午4:53:24
 * @modify author:修改人
 * Modify on 修改时间
*/
public class HttpRequestNotSuccessException extends HttpRequestException {
	private static final long serialVersionUID = -7078621911730856514L;

	private int statusCode;
	private String reasonPhrase;
	
	public HttpRequestNotSuccessException(int statusCode, String reasonPhrase) {
		super(ErrorCode.HTTP_RESP_FAIL_ERR);
		this.statusCode = statusCode;
		this.reasonPhrase = reasonPhrase;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	@Override
	public String getMessage() {
		return "StatusCode: " + this.statusCode + ", ReasonPhrase: " + this.reasonPhrase;
	}
}
