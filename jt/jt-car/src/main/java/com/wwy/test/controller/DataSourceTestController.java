package com.wwy.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwy.entry.APIEntry;
import com.wwy.entry.Car;
import com.wwy.test.service.DataSourceTestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags = "多数据源测试")
@RestController
@RequestMapping("datasource")
public class DataSourceTestController {
	@Autowired
	private DataSourceTestService dataSourceTestService;
	@ApiOperation("读")
	@GetMapping("read")
public APIEntry read() {
	return dataSourceTestService.read();
}
	@ApiOperation("写")
	@PostMapping("write")
	
public APIEntry write(Car car) {
	return dataSourceTestService.write(car);
}
}
