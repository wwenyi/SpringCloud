package com.wwy.sso.service;

import com.wwy.entry.APIEntry;
/**
 * 
 * @author wwy
 * @date 2019年12月2日
 * @version v0.0.1
 *注册信息验证service层接口
 */
public interface VerificationService {
	/**
	 * 验证信息方法
	 * @param tel
	 * @param type
	 * @return
	 */
	APIEntry telAndEmil(String tel, Integer type);


}
