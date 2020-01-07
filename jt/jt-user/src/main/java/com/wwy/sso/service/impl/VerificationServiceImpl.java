package com.wwy.sso.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wwy.entry.APIEntry;
import com.wwy.entry.User;
import com.wwy.sso.dao.VerificationDao;
import com.wwy.sso.service.VerificationService;
/**
 * 
 * @author wwy
 * @date 2019年12月2日
 * @version v0.0.1
 *用户信息验证的servcie层接口
 */
@Service
public class VerificationServiceImpl implements VerificationService{
	
	@Autowired
	private VerificationDao verificationDao;
	/**
	 * 对用户输入的值先进行正则验证，验证通过后再查询数据库看用户是否已注册
	 */
	@Override
	public APIEntry telAndEmil(String str,Integer type) {
		User user=null;
		//如果type==1,对tel进行正则验证.type==2则对邮箱进行正则验证
		if(type==1&&!str.matches("^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$")) {
			return APIEntry.ERROR("tel格式有误");
			
		}else if(type==2&&!str.matches("[0-9a-zA-Z_]{0,19}@[0-9a-zA-Z]{1,13}\\.[com,cn,net]{1,3")) {
			return APIEntry.ERROR("emil格式有误");
		}
		
		//如果格式验证通过，调用dao发送sql查询用户是否已注册
		if(type==1) {
		user=verificationDao.tel(str);
		}else {
		user=verificationDao.emil(str);
		}
		//如果返回值user！=null则说明用户已注册
		if(user!=null&&type==1) {
			return APIEntry.ERROR("该tel已被注册");
		}

		if(user!=null&&type==2) {
			return APIEntry.ERROR("该emil已被注册");
		}
		//如果正则验证通过且tel和emil均未被注册返回成功消息
		return APIEntry.OK(null);
	}

}
