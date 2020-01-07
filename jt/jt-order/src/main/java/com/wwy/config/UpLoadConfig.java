package com.wwy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * web配置类
 * @author wwy
 * @date 2019年12月16日
 * @version v0.0.1
 *
 */
@Configuration
public class UpLoadConfig implements WebMvcConfigurer{
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/**
		 * 将/upload/**映射到file:D://upload
		 */
	        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/upload/");
	    }

}
