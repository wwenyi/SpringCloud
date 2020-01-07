package com.wwy.util;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * json工具类
 * @author wwy
 * @date 2019年12月3日
 * @version v0.0.1
 *
 */
public class JSONUtil {

public static final ObjectMapper mapper=new ObjectMapper();
/**
 * 将对象转换为json串
 * @param object
 * @return
 * @throws JsonProcessingException
 */
public static String toJson(Object object) throws JsonProcessingException {
	return mapper.writeValueAsString(object);
}
/**
 * 将json串转换成对象
 * @param <T>
 * @param json
 * @param cls
 * @return
 * @throws JsonParseException
 * @throws JsonMappingException
 * @throws IOException
 */
public static <T>T toObject(String json,Class<T> cls) throws JsonParseException, JsonMappingException, IOException {
	return mapper.readValue(json, cls);
}
/**
 * 将json转换为Map
 * @param json
 * @return
 */
public static Map<String,Object> toMap(String json){
	return JSON.parseObject(json);
}
}
