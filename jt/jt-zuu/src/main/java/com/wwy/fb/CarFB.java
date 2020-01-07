package com.wwy.fb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
/**
 * 服务的降级类
 * 当zuul转发请求失败时执行该类中的方法
 * @author wwy
 * @date 2020年1月6日
 * @version v0.0.1
 *
 */
@Slf4j
@Component
public class CarFB implements FallbackProvider{
	/**
	 * 指定服务转发失败时执行该代码
	 */
	@Override
	public String getRoute() {
		log.error("转发失败了哟");
		return null;
		//*和null为通配，如果要使用一个降级类通配所有的服务降级，则返回*或者null
	}
	/**
	 * 返回相应结果
	 */
	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		
		return response();
	}
	/**
	 * 
	 * @return
	 */
	private ClientHttpResponse response() {
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				//返回错误信息
				return new ByteArrayInputStream("服务器后台错误".getBytes("UTF-8"));
			}

			@Override
			public HttpHeaders getHeaders() {
				//返回请求头格式为application/json（json格式）
				HttpHeaders h=new HttpHeaders();
				h.setContentType(MediaType.APPLICATION_JSON);
				return h;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				//返回状态信息
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				//返回状态码
				return HttpStatus.INTERNAL_SERVER_ERROR.value();
			}

			@Override
			public String getStatusText() throws IOException {
	
				return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
			}

			@Override
			public void close() {
	
			}
			
		};
	}

}
