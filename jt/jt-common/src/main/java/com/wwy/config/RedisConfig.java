package com.wwy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
/**
 * 
 * @author wwy
 * @date 2019年11月29日
 * @version v0.0.1
 *redis客户端jedis的配置类
 */
@Slf4j
@Configuration
public class RedisConfig {
	/**
	 * 部署redis的ip地址，配置在yml文件中
	 */
	@Value("${spring.redis.host}")
	private String ip;
	/**
	 * redis启动的端口，配置在yml文件中
	 */
	@Value("${spring.redis.port}")
	private Integer port;
	/**
	 * 将jedis交给spring管理
	 * @return
	 */
@Bean("jedis")
public Jedis getJedis() {
	log.info(ip+">>>>>>>"+port);
	return new Jedis(ip,port);
}
}
