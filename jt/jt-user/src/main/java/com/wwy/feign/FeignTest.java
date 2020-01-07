package com.wwy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(fallback =UserFallbace.class,value="jt-order" )

public interface FeignTest {
	
	@PostMapping("order/test/get")
public String get();
}
