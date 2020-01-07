package com.wwy.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wwy.entry.User;
import com.wwy.exception.NoTokenException;

import redis.clients.jedis.Jedis;

public class UserUtil {
public static User getUser(HttpServletRequest request,Jedis jedis) throws JsonParseException, JsonMappingException, IOException, NoTokenException {
	String token=request.getHeader("token");
	//System.out.println(token);
	if(token.isEmpty()) {
		throw new NoTokenException("缺少token");
	}
	if(jedis.get(token).isEmpty()) {
		throw new NoTokenException("用户未登录");
	}
	//System.out.println(jedis.get(token));
	return JSONUtil.toObject(jedis.get(token), User.class);
}
}
