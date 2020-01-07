package com.wwy.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwy.entry.APIEntry;
import com.wwy.sso.service.VerificationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author wwy
 * @date 2019年12月2日
 * @version v0.0.1
 *注册时ajax异步请求验证tel是否已注册，格式是否正确
 *邮箱格式是否正确，邮箱是否已注册
 *
 */
@Api(tags="输入信息验证")
@RestController
@RequestMapping("verification")
public class VerificationController {
	@Autowired
	private VerificationService verificationService;
	/**
	 * 
	 * @param str 前端传递的用户输入的值
	 * @param type 验证的类型，1表示验证tel，2验证emil 
	 * @return
	 */
	@ApiOperation("验证")
	@GetMapping("tel/{str}/{type}")
	public APIEntry telAndEmil(@PathVariable("str") String str,@PathVariable("type")Integer type) {
		return verificationService.telAndEmil(str,type);
	}
	
}
