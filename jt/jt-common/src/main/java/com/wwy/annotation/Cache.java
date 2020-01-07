package com.wwy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义缓存注解
 * @author wwy
 * @date 2019年12月13日
 * @version v0.0.1
 *
 */
@Retention(RetentionPolicy.RUNTIME)//声明注解何时起作用（运行时）
@Target({ElementType.METHOD,//声明注解作用的位置（方法）
		ElementType.TYPE})//声明注解作用的位置（类、接口、枚举）
public @interface Cache {
	/**
	 * 名称
	 * @return
	 */
String[] value() default"";
/**
 * 写入还是读取
 * 
 * @return
 */
boolean read() default true;
}
