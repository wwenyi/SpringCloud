package com.wwy.sso.dao;

import org.apache.ibatis.annotations.Mapper;
import com.wwy.entry.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
/**
 * 
 * @author wwy
 * @date 2019年12月2日
 * @version v0.0.1
 *单点登录dao层接口
 */
@Mapper
public interface SsoDao extends BaseMapper<User>{
	/**
	 * 登录
	 * @param tel
	 * @param passWord
	 * @return
	 */
	User login(String tel, String passWord) ;
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	int register(User user);


}
