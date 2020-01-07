package com.wwy.test.feigntest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "测试用")
@RestController
@RequestMapping("test")
public class testController {
	@ApiOperation("feign")
	@PostMapping("get")
public String get() {
		
	return "我是来自order的test，请通过feign调用我";
}
}
