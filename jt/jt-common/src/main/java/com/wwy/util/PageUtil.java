package com.wwy.util;
/**
 * 分页查询工具类
 * @author wwy
 * @date 2019年12月3日
 * @version v0.0.1
 *
 */
public class PageUtil {
public static Integer getStart(Integer page,Integer pageSize) {
	return (page-1)*pageSize;
}
}
