package com.wwy.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 
 * @author wwy
 * @date 2019年11月29日
 * @version v0.0.1
 *用户验证过滤器
 */
@Component
public class Filter extends ZuulFilter{
	/**
	 * 过滤哪些服务
	 * true表示过滤所有
	 */ 
	@Override
	public boolean shouldFilter() {
		System.out.println("过滤");
		return true;
	}
	/**
	 * 过滤器中执行的代码
	 */
	@Override
	public Object run() throws ZuulException {
		System.out.println("过滤器已执行");
		RequestContext ctx=RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();
		String token = request.getParameter("token");
		System.out.println(token);
		if(token==null) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseBody("没有token");
			ctx.setResponseStatusCode(200);
		}
		ctx.setSendZuulResponse(true);
		return null;
	}
	/**
	 * 过滤器顺序
	 */
	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
	}
	/**
	 * 过滤器类型
	 */
	@Override
	public String filterType() {
		
		return FilterConstants.PRE_TYPE;
	}

}
