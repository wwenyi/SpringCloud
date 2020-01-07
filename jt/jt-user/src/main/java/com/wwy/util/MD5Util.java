package com.wwy.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @author wwy
 * @date 2019年11月29日
 * @version v0.0.1
 *md5加密的工具类
 */
public class MD5Util {
	/**
	 * 普通加密
	 * @param str 用户传递的字符串
	 * @return 通过md5 加密后的字符串
	 */
public static String getMD5(String str) {
	String encodeStr= DigestUtils.md5Hex(str);   
    return encodeStr;
}
/**
 * 多次加密
 * @param str 用户传递的字符串
 * @param num 加密次数
 * @return  通过md5 加密后的字符串
 */
public static String getMD5(String str,int num) {
	String encodeStr=str;
	
	for(int i=0;i<num;i++) {
	encodeStr= DigestUtils.md5Hex(encodeStr);   
	}
	
    return encodeStr;
}
/**
 * 加盐加密
 * @param str 用户传递的字符串
 * @param salt 盐
 * @return  通过md5 加密后的字符串
 */
public static String getMD5(String str,String salt) {
	String encodeStr=str+salt;
		
	encodeStr= DigestUtils.md5Hex(encodeStr);   
	
	
    return encodeStr;
}

/**
 * 多次加盐加密
 * @param str 用户传递的字符串
 * @param num 加密次数
 * @param salt 盐
 * @return  通过md5 加密后的字符串
 */
public static String getMD5(String str,String salt,int num) {
	String encodeStr=str+salt;
	
	for(int i=0;i<num;i++) {
	encodeStr= DigestUtils.md5Hex(encodeStr);   
	}
	
    return encodeStr;
}

}
