package com.mapbar.display.util.http;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Charsets;
import com.mapbar.display.common.Const;
import com.mapbar.display.dto.LocalCloudRespopnse;
import com.mapbar.display.dto.LocationDataResp;
import com.mapbar.display.exception.http.*;
import com.mapbar.display.util.JsonUtil;
import com.mapbar.display.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.Assert;

/**
 * http调用工具类
 * 
 * @author:
 * Created on 2015年8月19日 下午2:38:44
*/
public final class HttpUtil {


	private static final String CONTENT_TYPE = "Content-Type";
	
	private static final String APPLICATION_JSON = "application/json";
	private static final String APPLICATION_JSON_UTF8 = "application/json; charset=utf-8";
	
	private static final String TEXT_XML = "text/xml";
	private static final String TEXT_XML_UTF8 = "text/xml; charset=utf-8";
	
	private static final String TEXT_NORMAL = "application/x-www-form-urlencoded; charset=utf-8";
	
	private static final int OK = 200;
	private static final int PARAM_ERROR = 400;
	private static final int CONNECT_REFUSED = 403;
	private static final int SERVER_ERROR = 500;


	private static final HttpClient httpClient = getHttpClient();

	private static HttpClient getHttpClient() {
		// 此处运用连接池技术。
		MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();

		// 设定参数：与每个主机的最大连接数
		int maxConnPerHost = Const.MAX_CONN_PER_HOST;
		manager.getParams().setDefaultMaxConnectionsPerHost(maxConnPerHost);

		// 设定参数：客户端的总连接数
		int maxTotalConnections = Const.MAX_TOTAL_CONNECTIONS;
		manager.getParams().setMaxTotalConnections(maxTotalConnections);

		// 设置连接超时时间,单位：毫秒
		int connectionTimeOut = Const.CONNECTION_TIMEOUT;
		manager.getParams().setConnectionTimeout(connectionTimeOut);

		// 设置请求读取超时时间，单位：毫秒
		int soTimeOut = Const.SO_TIME_OUT;
		manager.getParams().setSoTimeout(soTimeOut);

		// 使用连接池技术创建HttpClient对象
		HttpClient httpClient = new HttpClient(manager);

		return httpClient;
	}

	/**
	 * json的post请求
	 * 
	 * @param url 请求url
	 * @param reqEntity 请求头与请求体的封装
	 * @param respBodyClass 响应体类型
	 * @return 返回的响应结果
	 * @author:
	 * Created on 2015年8月19日 下午1:50:08
	 */
	public static <T> HttpEntity<T> postJsonRequest(String url,
			HttpEntity<?> reqEntity, Class<T> respBodyClass) {
		Assert.hasLength(url, "请求url不能为空字符串。");
		Assert.notNull(reqEntity, "请求request不能为null。");
		Assert.notNull(reqEntity.getBody(), "Post请求body不能为null。");
		
		EntityEnclosingMethod httpMethod = new PostMethod(url);
		//设置header信息
		Map<String, String> headers = reqEntity.getHeaders();
		//若传入报文头，则设置值
		if (notEmptyHeaders(headers)) {
			setReqHeaders(headers, httpMethod);
		}
		
		//设置body信息
		String reqJson = JsonUtils.toJson(reqEntity.getBody());
		
		String charset = Charsets.UTF_8.name();
		// 发送含xml消息体的对象
		try {
			RequestEntity entity = new StringRequestEntity(reqJson, APPLICATION_JSON, charset);
			httpMethod.setRequestEntity(entity);
			// 执行请求并接收响应码
			int resultCode = httpClient.executeMethod(httpMethod);
			
			String respStr = httpMethod.getResponseBodyAsString();
			if (resultCode == OK) {
				//响应头
				Map<String, String> respHeaders = getRespHeaders(httpMethod);
				//响应体
				HttpEntity<T> rep = null;
				if (isNullOrEmpty(respStr)) {
					rep = new HttpEntity<T>(respHeaders, null);;// 无响应
				} else {
					T respBody = JsonUtils.fromJson(respStr, respBodyClass);
					rep = new HttpEntity<T>(respHeaders, respBody);
				}
				return rep;
			} else {
				StatusLine staline = httpMethod.getStatusLine();
				throw new HttpRequestNotSuccessException(staline.getStatusCode(), staline.getReasonPhrase());
			}
		} catch (IOException e) {
			throw new HttpMessageTransferException(e);
		} finally {
			if (httpMethod != null) {
				httpMethod.releaseConnection();
			}
		}
	}

	/**
	 * json的get请求
	 * 
	 * @param url 请求url
	 * @param headers 请求头
	 * @param respBodyClass 响应体类型
	 * @return 返回的响应结果
	 * @author:wujiangbo
	 * Created on 2015年8月19日 下午2:06:53
	 */
	public static <T> HttpEntity<T> getJsonRequest(String url, Map<String, String> headers,
			Class<T> respBodyClass) {
		Assert.hasLength(url, "请求url不能为空字符串。");
		
		HttpMethodBase httpMethod = new GetMethod(url);
		//若传入报文头，则设置值
		if (notEmptyHeaders(headers)) {
			setReqHeaders(headers, httpMethod);
		}
		httpMethod.setRequestHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8);
		// 发送含xml消息体的对象
		try {
			// 执行请求并接收响应码
			int resultCode = httpClient.executeMethod(httpMethod);
			
			String respStr = httpMethod.getResponseBodyAsString();
			if (resultCode == OK) {
				// 响应头
				Map<String, String> respHeaders = getRespHeaders(httpMethod);
				// 响应体
				HttpEntity<T> rep = null;
				if (isNullOrEmpty(respStr)) {
					rep = new HttpEntity<T>(respHeaders, null);;// 无响应
				} else {
					T respBody = JsonUtils.fromJson(respStr, respBodyClass);
					rep = new HttpEntity<T>(respHeaders, respBody);
				}
				return rep;
			} else {
				StatusLine staline = httpMethod.getStatusLine();
				throw new HttpRequestNotSuccessException(staline.getStatusCode(), staline.getReasonPhrase());
			}
		} catch (IOException e) {
			throw new HttpMessageTransferException(e);
		} finally {
			if (httpMethod != null) {
				httpMethod.releaseConnection();
			}
		}
	}


	public static <T> T getLocalCloudJsonRequest(String url, TypeReference<T> valueTypeRef) {
		Assert.hasLength(url, "请求url不能为空字符串。");

		HttpMethodBase httpMethod = new GetMethod(url);

		httpMethod.setRequestHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8);
		// 发送含xml消息体的对象
		try {
			// 执行请求并接收响应码
			int resultCode = httpClient.executeMethod(httpMethod);

			String respStr = httpMethod.getResponseBodyAsString();
			if (resultCode == OK) {
				// 响应体
				LocalCloudRespopnse<T> rep = JsonUtils.fromJson(respStr, valueTypeRef);
				int businesCode = rep.getCode();
				T data = null;
				switch (businesCode){
					case OK : data = rep.getData();
					case PARAM_ERROR : throw new LocalCloudParamErrorException();
					case CONNECT_REFUSED : throw new LocalCloudRefusedException();
					case SERVER_ERROR : throw new LocalCloudServerErrorException();
				}
				return data;
			} else {
				StatusLine staline = httpMethod.getStatusLine();
				throw new HttpRequestNotSuccessException(staline.getStatusCode(), staline.getReasonPhrase());
			}
		} catch (IOException e) {
			throw new HttpMessageTransferException(e);
		} finally {
			if (httpMethod != null) {
				httpMethod.releaseConnection();
			}
		}
	}

	/**
	 * 获取http响应码是200的指定响应头值
	 * @param url
	 * @param respHeaderName respHeaderName 响应头名
	 * @return respHeaderName对应的响应头值
	 * @author:wujiangbo
	 * Created on 2016年4月6日 上午9:32:25
	 * @modify author:修改人
	 * Modify on 修改时间
	 */
	private static String getResponseHeader(String url, String respHeaderName) {
		return getResponseHeader(url, respHeaderName, HttpServletResponse.SC_OK);
	}
	
	/**
	 * 获取指定响应头值
	 * @param url
	 * @param respHeaderName 响应头名
	 * @param expectStatusCode 期望的http响应状态码
	 * @return respHeaderName对应的响应头值
	 * @author:
	 * Created on 2016年4月6日 上午9:29:52
	 * @modify author:修改人
	 * Modify on 修改时间
	 */
	private static String getResponseHeader(String url, String respHeaderName, int expectStatusCode) {
		Assert.hasLength(url);
		Assert.hasLength(respHeaderName);
		
		HttpMethodBase httpMethod = new GetMethod(url);
		try {
			int resultCode = httpClient.executeMethod(httpMethod);
			if (resultCode != expectStatusCode) {
				//非期望的状态码，抛异常
				throw new HttpRequestNotSuccessException(resultCode, String.format("Expect %d, but actually %d", expectStatusCode, resultCode));
			}
			
			String headerVal = httpMethod.getResponseHeader(respHeaderName).getValue();
			return headerVal;
		} catch (IOException e) {
			throw new HttpMessageTransferException(e);
		} finally {
			if (httpMethod != null) {
				httpMethod.releaseConnection();
			}
		}
	}
	
	/**
	 * 存在header
	 * @param headers
	 * @return 
	 * @author:
	 * Created on 2016年3月7日 下午4:23:03
	 * @modify author:修改人
	 * Modify on 修改时间
	 */
	private static boolean notEmptyHeaders(Map<String, String> headers) {
		return MapUtils.isNotEmpty(headers);
	}
	
	/**
	 * 得到响应头信息
	 * 
	 * @param httpMethod 调用http请求的方法
	 * @return 所有的响应头信息
	 * @author:
	 * Created on 2015年8月19日 下午1:45:56
	 */
	private static Map<String, String> getRespHeaders(HttpMethodBase httpMethod) {
		//得到响应头
		Header[] respHeaders = httpMethod.getResponseHeaders();
		Map<String, String> headers = new HashMap<String, String>(respHeaders.length);
		for (Header header : respHeaders)
			headers.put(header.getName(), header.getValue());
		return headers;
	}
	
	/**
	 * 设置请求头
	 * 
	 * @param headers 要设置的请求头
	 * @param httpMethod 接收请求头的http请求方法
	 * @author:wujiangbo
	 * Created on 2015年8月19日 下午1:47:11
	 */
	private static void setReqHeaders(Map<String, String> headers, HttpMethodBase httpMethod) {
		//设置请求头
		for (Map.Entry<String, String> header : headers.entrySet()) {
			httpMethod.addRequestHeader(header.getKey(), header.getValue());
		}
	}

	private static boolean isNullOrEmpty(String obj) {
		if (obj == null || obj.isEmpty()) {
			return true;
		}
		return false;
	}

	public static String getUrl(String url, Object t) throws IOException {
		return url + "?" + getRequestParam(getParam(t));
	}

	private static Map<String,Object> getParam(Object obj) throws IOException {
		return JsonUtil.toMap(JsonUtils.toJson(obj));
	}

	private static String getRequestParam(Map<String,Object> map){
		List<BasicNameValuePair> params = new ArrayList<>();
		Set<Map.Entry<String,Object>> mapSet = map.entrySet();
		for (Map.Entry<String,Object> entry: mapSet){
			params.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
		}
		return URLEncodedUtils.format(params,"UTF-8");
	}

}
