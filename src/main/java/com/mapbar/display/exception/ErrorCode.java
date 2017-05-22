package com.mapbar.display.exception;


/**
 * 错误码常量类
 * @author:wujiangbo
 * Created on 2016年3月15日 下午2:20:10
 * @modify author:修改人
 * Modify on 修改时间
*/
public class ErrorCode {

	/*网络IO错误*/
	public static final String NETWORK_IO_ERR = "10";
	public static final String SOCKET_TIMEOUT = "11";
	/*失败的http响应*/
	public static final String HTTP_RESP_FAIL_ERR = "20";
	
	/****servlet异常***/
	/*http方法不被支持*/
	public static final String HTTP_METHOD_NOT_SUPPORT = "35";
	/*请求参数绑定失败*/
	public static final String UNSATISFIED_REQ_PARAM = "38";
	
	/*token过期*/
	public static final String TOKEN_EXPIRE = "99";
	
	/*未知错误*/
	public static final String UNKNOWN_ERR = "100";
	
	/*JD库存不足*/
	public static final String JD_STOCK_INSUFFICIENT = "301";
	
	/*Aserver异常码*/
	/*token失效*/
	public static final String ASER_TOKEN_INVALID = "22229";
	
}
