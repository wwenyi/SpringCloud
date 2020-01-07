package com.wwy.util;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wwy.entry.UserInfo;
/**
 * jwt工具类
 * @author wwy
 * @date 2019年12月6日
 * @version v0.0.1
 *
 */
public class JwtUtil {
	
public static String getToken(String qf,UserInfo userInfo) throws IllegalArgumentException, JWTCreationException, JsonProcessingException {
	Algorithm algorithm = Algorithm.HMAC256(qf);
	String token=JWT.create().withIssuer(qf)
            // 1小时过期时间
            .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .withClaim("userInfo",JSONUtil.toJson(userInfo))
            .sign(algorithm);
    return token;
}
}
