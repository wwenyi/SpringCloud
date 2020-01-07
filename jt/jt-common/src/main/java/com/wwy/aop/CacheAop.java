package com.wwy.aop;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import com.wwy.annotation.Cache;
import com.wwy.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
/**
 * 定义切面类，为查询数据添加缓存
 * @author wwy
 * @date 2019年12月13日
 * @version v0.0.1
 *
 */
@Slf4j
@Component
@Aspect
public class CacheAop {
	
	@Autowired
	private StringRedisTemplate redis;
	
	//环绕通知，切入到自定义注解修饰的方法或者类上（的多有方法）
	@SuppressWarnings("unchecked")
	@Around("@annotation(com.wwy.annotation.Cache)")
	public Object cache(ProceedingJoinPoint joinPoint) {
		//获取方法签名信息（返回值，包名，类名，方法名，参数列表）
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		//获取切入方法操作的数据库名
		String[] dataSourceName=null;
		//获取切入方法是读取数据库还是写入数据库
		boolean read=false;
		Annotation[] annotations = signature.getMethod().getAnnotations();
		for(Annotation a:annotations) {
			if(a.annotationType().equals(Cache.class)) {
				dataSourceName=((Cache)a).value();						
				read=((Cache)a).read();
			}
		}
		//将方法操作的数据库名连接起来，后面连接到key中
		String dataSourceKey="";
		for(String k:dataSourceName) {
			dataSourceKey+=("<"+k+">");
		}
		//获取返回值类型
		Class<Object> returnType = signature.getReturnType();		
		//获取全类名
		String className = signature.getDeclaringTypeName();
		//获取方法名
		String name = signature.getMethod().getName();
		//拼装redis的key为全类名+方法名去除.+数据库名
		String key=(className+name).replace(".", "")+dataSourceKey;
		log.info("Rediskey:"+key);
		//读操作：通过key查询redis中的数据，如果有数据，就返回redis中的数据
		if(read) {
			log.info("查询操作");
			log.info("查询的数据库为："+dataSourceKey);
			String value = redis.opsForValue().get(key);
			if(value==null||value.isEmpty()) {
				Object proceed=null;
				try {				
					//执行连接点方法
					proceed = joinPoint.proceed();
					if(proceed!=null) {
						//将返回的对象存放到redis中
						redis.opsForValue().set(key, JSONUtil.toJson(proceed));
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
				log.info("数据来源于数据库");
				return proceed;
			}else {
				try {
					log.info("数据来源于缓存");
					//如果redis中有值，返回redis中的对象
					return JSONUtil.toObject(value, returnType);
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			//写操作：清楚数据库中的关于数据表的缓存
		}else {
			log.info("增删改操作");
			log.info("增删改的数据库为："+dataSourceKey);
			Object proceed=null;
			try {
				proceed = joinPoint.proceed();
				if(proceed!=null) {		
					//模糊匹配包含数据库名的key
					
					//获取方法需要操作的所有数据库名
					for(String dkey:dataSourceName) {
						
						//获取redis缓存中所有跟该数据库有关的key
						Set<String>  keys=redis.keys("*"+dkey+"*");
						//删除与该数据库有关的所有缓存	
						if(keys!=null&&!keys.isEmpty()) 
						redis.delete(keys);
						log.info("已删除"+dkey+"相关的缓存");
					}
				}
				return proceed;
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
