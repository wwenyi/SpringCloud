package com.wwy.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags = "测试feign")
@RestController
@RequestMapping("test" )
public class FeignController {
	@Autowired
	private FeignTest t;
	@ApiOperation("测试")
	@PostMapping("get")
public String get() {
	return t.get();
}
}
