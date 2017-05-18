package com.mapbar.display.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static final ObjectMapper mapper = new ObjectMapper();
	
	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//bean中不存在的filed，反序列化时不失败
	}

	/**
	 * 从json字符串反序列化到bean
	 * @param jsonString 待反序列化的字符串
	 * @param clazz 目标bean类型
	 * @return 
	 * @author:
	 * Created on 2016年2月25日 上午8:55:02
	 * @modify author:修改人
	 * Modify on 修改时间
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz) {
		T t = null;
		try {
			t = mapper.readValue(jsonString, clazz);
		} catch (JsonParseException e) {
			throw new IllegalArgumentException("Deserialize from JSON failed.", e);
		} catch (JsonMappingException e) {
			throw new IllegalArgumentException("Deserialize from JSON failed.", e);
		} catch (IOException e) {
			throw new IllegalArgumentException("Deserialize from JSON failed.", e);
		}
		return t;
	}
	
	/**
	 * 从json字符串反序列化到bean
	 * @param jsonString
	 * @param valueTypeRef
	 * @return 
	 * @author:
	 * Created on 2016年3月7日 上午11:19:52
	 * @modify author:修改人
	 * Modify on 修改时间
	 */
	public static <T> T fromJson(String jsonString, TypeReference<?> valueTypeRef) {
		try {
			T result = mapper.readValue(jsonString, valueTypeRef);
			return result;
		} catch (IOException e) {
			throw new IllegalArgumentException("Deserialize from JSON failed.", e);
		}
	}
	
	/**
	 * 对象序列化到json字符串
	 * @param obj
	 * @return 
	 * @author:
	 * Created on 2016年2月25日 上午8:55:46
	 * @modify author:修改人
	 * Modify on 修改时间
	 */
	public static String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Serialize to JSON failed.", e);
		}
	}
}
