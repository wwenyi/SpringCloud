package com.wwy.user.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwy.entry.APIEntry;
import com.wwy.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @author wwy
 * @date 2019年12月3日
 * @version v0.0.1
 *
 */
@Api(tags="用户信息")
@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @param page 当前页码
	 * @param type 1表示查询商家，2表示查询分销商
	 * @return
	 */
	@ApiOperation("分页查询所有用户信息")
	@GetMapping("selectAll/{type}")
	public APIEntry selectAll(Integer page,@PathVariable("type")Integer type,HttpServletRequest request,HttpServletResponse response) {
		return userService.selectAll(page,type,request,response);
		
	}
	/**
	 * 通过商家id查询商家的所有分销商信息
	 * @param id
	 * @param type 1查询已审核的，2查询未审核的，3表示查询所有的
	 * @return
	 */
	@ApiOperation("通过商家id查询分销商")
	@GetMapping("selectDisByBu/{type}")
	public APIEntry selectDisByBu(Long id,@PathVariable("type")Integer type) {
		//TODO
		return null;
	}
	/**
	 * 通过分销商id查询所有商家信息
	 * @param id
	 * @param type 1查询已审核的，2查询未审核的，3表示查询所有的
	 * @return
	 */
	@GetMapping("selectBuByDis/{type}")
	@ApiOperation("通过分销商id查询所有商家信息")
	public APIEntry selectBuByDis(Long id,@PathVariable("type")Integer type) {
		//TODO
		return null;
	}
	
	
}
