package com.mapbar.platform.common.utils.http;

import java.util.HashMap;
import java.util.Map;

/**
 * http头与body的封装
 * 
 * @author:
 * Created on 2015年8月19日 下午1:38:00
*/
public class HttpEntity<T> {
	
	/**空的报文头*/
	public static final Map<String, String> EMPTY_HEADERS = new HashMap<String, String>(0);

	/**报文头*/
	private final Map<String, String> headers;

	/**报文体*/
	private final T body;

	public HttpEntity(Map<String, String> headers, T body) {
		this.headers = headers;
		this.body = body;
	}
	
	public HttpEntity(T body) {
		this.headers = EMPTY_HEADERS;
		this.body = body;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public T getBody() {
		return body;
	}
}
