package com.wwy.config;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;

/**
 * swagger-ui的配置类，
 * @author wwy
 * @date 2019年12月3日
 * @version v0.0.1
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig{
	//服务名
	@Value("${spring.application.name}")
	private String name;
	/**
	 * Docket对象的bean标签
	 * @return
	 */
	@Bean
	public Docket docket() {
		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
		//200
		responseMessages.add(new ResponseMessageBuilder().code(HttpServletResponse.SC_OK).message("操作成功").build());
		//404
		responseMessages.add(new ResponseMessageBuilder().code(HttpServletResponse.SC_NOT_FOUND).message("资源不存在").build());
		//500
		responseMessages.add(new ResponseMessageBuilder().code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).message("服务器异常").build());

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseMessages)
				.globalResponseMessage(RequestMethod.POST, responseMessages)
				.globalResponseMessage(RequestMethod.DELETE, responseMessages)
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.build()
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts());

	}
	/**
	 * 接口信息
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("分销API文档-"+name).version("1.0.0").description("项目描述").build();
	}
	/**
	 * token验证
	 * @return
	 */
	private List<ApiKey> securitySchemes() {
		List<ApiKey> list = new ArrayList<ApiKey>();
		list.add(new ApiKey("Authorization", "token", "header"));
		return list;
	}
	private List<SecurityContext> securityContexts() {
		List<SecurityContext> list = new ArrayList<SecurityContext>();
		list.add(SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("^(?!auth).*$"))
				.build());
		return list;
	}
	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		List<SecurityReference> list = new ArrayList<SecurityReference>();
		list.add(new SecurityReference("Authorization", authorizationScopes));
		return list;
	}
}
