package com.wwy.test.mybatisplustest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwy.entry.APIEntry;
import com.wwy.entry.Order;
import com.wwy.test.mybatisplustest.service.MBPService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 使用mybatisplus
 * @author wwy
 * @date 2019年12月17日
 * @version v0.0.1
 *
 */
@Api(tags = "mybatisplus测试")
@RestController
@RequestMapping("mb")
public class MBPController {
	@Autowired
	private MBPService service;
	/**
	 * 单表查询的方法
	 * @return
	 */
	@ApiOperation("单表查询")
	@GetMapping("get")
	public APIEntry get() {
		return service.get();
	}
	@ApiOperation("单表更新")
	@PostMapping("updata")
	public APIEntry updata(Order order) {
		return service.updata(order);
	}
}
