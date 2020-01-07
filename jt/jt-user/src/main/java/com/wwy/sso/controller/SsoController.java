package com.wwy.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wwy.entry.APIEntry;
import com.wwy.entry.User;
import com.wwy.sso.service.SsoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author wwy
 * @date 2019年11月29日
 * @version v0.0.1
 *
 */

@Api(tags="单点登录")
@RestController
@RequestMapping("sso")
public class SsoController {
	
	@Autowired
	private SsoService ssoService;
	
	/**
	 * 用户注册的方法
	 * @return
	 */
	@ApiOperation("注册")	
	@PostMapping("register")
	public APIEntry register(User user,HttpServletRequest request,HttpServletResponse response) {
		return ssoService.register(user);		
		
	}
	/**
	 * 用户登录的方法
	 * @return
	 */
	@ApiOperation("登录")
	@ApiImplicitParams({@ApiImplicitParam(name="tel",value="电话"),@ApiImplicitParam(name="passWord",value="密码")})
	@PostMapping("login")
	public APIEntry login(String tel,String passWord,HttpServletRequest request,HttpServletResponse response) {
		
		return ssoService.login(tel,passWord,request,response);
	}
}
