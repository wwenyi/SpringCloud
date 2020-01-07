package com.wwy.sso.dao;

import org.apache.ibatis.annotations.Mapper;
import com.wwy.entry.User;
/**
 * 
 * @author wwy
 * @date 2019年12月2日
 * @version v0.0.1
 *注册信息验证dao层
 *
 */
@Mapper
public interface VerificationDao {
	/**
	 * 验证方法
	 * @param str
	 * @param type
	 * @return
	 */
	User tel(String str);

	User emil(String str);
	
}
