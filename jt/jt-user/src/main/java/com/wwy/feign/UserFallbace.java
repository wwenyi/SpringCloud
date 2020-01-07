package com.wwy.feign;

import org.springframework.stereotype.Component;

@Component
public class UserFallbace implements FeignTest{

	@Override
	public String get() {
		
		return "调用order失败，我是降级方法";
	}

}
