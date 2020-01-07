package com.wwy.user.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wwy.entry.APIEntry;
import com.wwy.entry.User;
import com.wwy.exception.NoTokenException;
import com.wwy.user.Dao.UserDao;
import com.wwy.user.service.UserService;
import com.wwy.util.CookieUtil;
import com.wwy.util.PageUtil;
import com.wwy.util.UserUtil;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
@Service
@Slf4j
public class UserServcieImpl implements UserService{
	@Value("${page.size}")
	private Integer pageSize;
	@Autowired
	private UserDao userDao;
	@Autowired
	private Jedis jedis;
	@Override
	public APIEntry selectAll(Integer page, Integer type,HttpServletRequest request,HttpServletResponse response) {
		//总条数
		int count=0;
		//用户信息
		List<User> list=null;
		//每页起始数据
		int start=PageUtil.getStart(page, pageSize);
		User user =null;
		//如果type=1，则查询商家总记录数，type=2则查询分销商总记录数
		//判断用户是否登录
//		System.out.println("===========>"+request.getCookies());
//		if(CookieUtil.getValue(request)==null) {
//			return APIEntry.ERROR("缺少token");
//		}
//		//判断用户角色并查询相应角色的数据
//		if(CookieUtil.role(request, jedis)==1&&type==1) {
//			count=userDao.selectCount1();
//			list=count==0?null:userDao.selectAll1(start,pageSize);
//		}
//		else if(CookieUtil.role(request, jedis)==2&&type==2) {
//			count=userDao.selectCount2();
//			list=count==0?null:userDao.selectAll1(start,pageSize);
//		}else {
//			return APIEntry.ERROR("不允许查询与用户性质不匹配的信息");
//		}
		System.out.println("===========>"+request.getHeader("token"));
		try {
			 user=UserUtil.getUser(request, jedis);
			
		} catch (IOException | NoTokenException e) {
			log.error("用户认证失败");
			e.printStackTrace();
		}
		if(user.getRole()==1&&type==1) {
			count=userDao.selectCount1();
			list=count==0?null:userDao.selectAll1(start,pageSize);
		}else if(user.getRole()==2&&type==2) {
			count=userDao.selectCount2();
			list=count==0?null:userDao.selectAll1(start,pageSize);
		}else {
			return APIEntry.ERROR("不允许查询与用户性质不匹配的信息");
		}
		
		
		
		//判断是否有数据
		if(list==null) {
			return APIEntry.ERROR("没有数据");
		}
		//返回正确结果
		return APIEntry.OK(list,count);

	}

}
