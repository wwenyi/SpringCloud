package com.wwy.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import redis.clients.jedis.Jedis;

/**
 * 通过request获取用户信息的工具类
 * @author wwy
 * @date 2019年12月2日
 * @version v0.0.1
 *
 */
public class CookieUtil {
	/**
	 * 通过request获取用户对象
	 * @param request
	 * @param jedis
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
@SuppressWarnings("unchecked")
public static <T>T getUser(HttpServletRequest request,Jedis jedis) 
		throws JsonParseException, JsonMappingException, IOException {
	//通过request获取json字符串
	String userJson=null;
	String value=getValue(request);
	if(!value.isEmpty()) {
		userJson=jedis.get(value);
	}
	//返回json转换为的对象
	return (T)JSONUtil.toObject(userJson, Object.class);
}
/**
 * 判断用户是否可用
 * @param request
 * @param jedis
 * @return
 */
public static boolean available(HttpServletRequest request,Jedis jedis) {
	//通过request获取json字符串
	String userJson=null;
	String value=getValue(request);
	if(!value.isEmpty()) {
		userJson=jedis.get(value);
	}
	Map<String, Object> map = JSONUtil.toMap(userJson);
	if(((Integer)map.get("examine")==0
			&&(Integer)map.get("role")==1)
			||(Integer)map.get("delete")==1
			||(Integer)map.get("prohibit")==1) {
		return false;
	}
	return true;
	
}
/**
 * 获取用户角色方法
 * @param request
 * @param jedis
 * @return
 */
public static int role(HttpServletRequest request,Jedis jedis) {
	//通过request获取json字符串
		String userJson=null;
		String value=getValue(request);
		if(!value.isEmpty()) {
			userJson=jedis.get(value);
			
		}
		Map<String, Object> map = JSONUtil.toMap(userJson);
		return (Integer)map.get("role")!=null?(Integer)map.get("role"):-1;
		
		
}



/**
 * 获取cookie名为TICKET的cookie的value
 * @param request
 * @return
 */
public static String getValue(HttpServletRequest request) {
	if(request==null) {
		System.out.println("request==null");
		return null;
	}
	Cookie[] cookies = request.getCookies();
	if(cookies==null) {
		System.out.println("cookies==null");
		return null;
	}
	for(Cookie cookie:cookies) {
		if("TICKET".equals(cookie.getName())) {
			System.out.println("cookie=="+cookie.getValue());

			return cookie.getValue();
		}
	}
	return null;
}
}
