package com.wwy.sso.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wwy.entry.APIEntry;
import com.wwy.entry.User;
import com.wwy.entry.UserInfo;
import com.wwy.parm.ComParm;
import com.wwy.sso.dao.SsoDao;
import com.wwy.sso.service.SsoService;
import com.wwy.util.JSONUtil;
import com.wwy.util.JwtUtil;
import com.wwy.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
/**
 * 
 * @author wwy
 * @date 2019年11月29日
 * @version v0.0.1
 *	登录注册的servcie层实现类
 */
@Slf4j
@Service
public class SsoServiceImpl implements SsoService{
	@Autowired
	private SsoDao ssoDao;
	@Autowired
	private Jedis jedis;
	/**
	 * 注册方法
	 */
	@Override
	public APIEntry register(User user) {
		log.error("password"+user.getPassWord());
		//判断用户信息是否完善
		if(!isEmpty(user)) {
			log.error("ERR>>>>用户信息未完善");
			log.error(user.toString());
			
			return APIEntry.ERROR("用户信息未完善");
			
		}
		//将用户信息写入数据库
		user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		user.setPassWord(MD5Util.getMD5(user.getPassWord()));
		int count=ssoDao.register(user);
		if(count<1) {
			log.error("ERR>>>>注册失败");
			return APIEntry.ERROR("注册失败，请重试");
		}
		log.info("OK>>>>注册成功");
		return APIEntry.OK(null);
	}
	

	/**
	 * 用户登录的service层
	 */
	@Override
	public APIEntry login(String tel, String passWord, HttpServletRequest request, HttpServletResponse response) {
		//在数据库中查询用户
		User user = ssoDao.login(tel,MD5Util.getMD5(passWord));
///////////////////////////////////用户验证/////////////////////////////////////////////////////////////	
		//如果用户不存在，说明用户未注册或密码错误
		if(user==null) {
			return APIEntry.ERROR("手机号或密码错误");
		}		
		//判断用户是否被禁用
		if(user.getProhibit()==1) {
			return APIEntry.ERROR("您的账号已被禁用，请联系管理员");
		}
		//如果是商家用户，判断用户是否通过审核
		if(user.getRole()==1&&user.getExamine()==0) {
			return APIEntry.ERROR("您的账号尚未通过审核，请等待");
		}
		//判断用户是否删除
		if(user.getDelete()==1) {
			return APIEntry.ERROR("您的账户已注销");
		}
/////////////////////////////////用户登录///////////////////////////////////////////
		//jwt传入参数
		UserInfo userInfo=new UserInfo();
		userInfo.setUserId(user.getUserId());
		userInfo.setNickName(user.getNickName());
		userInfo.setTel(user.getTel());
		userInfo.setRole(user.getRole());
		String token=null;
		try {
		token=JwtUtil.getToken(ComParm.QF, userInfo);
		//将用户信息存储在redis中
		log.info(token);
			jedis.setex(token, ComParm.TIME, JSONUtil.toJson(user));
		} catch (JsonProcessingException e) {
			log.error("json转换异常");
		}
		return APIEntry.OK(user.getRole()).setData(token);
	}
	
	
	/**
	 * 
	 * @param user 用户对象
	 * @return 用户是否为空
	 * 判断用户信息是否正确
	 */
public boolean isEmpty(User user) {
	if(user==null||user.getUserName()==null||user.getUserName()==""||
		user.getTel()==null||user.getTel()==""||user.getWeixin()==null
		||user.getWeixin()=="") {
		return false;
	}else {
		return true;
	}
}
}
