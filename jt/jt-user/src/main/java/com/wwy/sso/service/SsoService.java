package com.wwy.sso.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wwy.entry.APIEntry;
import com.wwy.entry.User;
/**
 * 
 * @author wwy
 * @date 2019年12月2日
 * @version v0.0.1
 *单点登录service层接口
 */
public interface SsoService {
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	APIEntry register(User user);
	/**
	 * 登录
	 * @param tel
	 * @param passWord
	 * @param request
	 * @param response
	 * @return
	 */
	APIEntry login(String tel, String passWord, HttpServletRequest request, HttpServletResponse response);

}
