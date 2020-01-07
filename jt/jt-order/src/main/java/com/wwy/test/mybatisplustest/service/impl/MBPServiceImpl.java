package com.wwy.test.mybatisplustest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwy.entry.APIEntry;
import com.wwy.entry.Order;
import com.wwy.test.mybatisplustest.dao.MBPDao;
import com.wwy.test.mybatisplustest.service.MBPService;
import lombok.extern.slf4j.Slf4j;
/**
 * 使用mybatisplus执行单表操作的service层
 * @author wwy
 * @date 2019年12月17日
 * @version v0.0.1
 *
 */
@Service
@Slf4j
public class MBPServiceImpl implements MBPService{
	@Autowired
	private MBPDao dao;
	@Override
	public APIEntry get() {
		//封装查询条件的对象
		QueryWrapper<Order> wrapper=new QueryWrapper<>();
		//添加查询条件,第一个参数表示字段名，第二个参数表示你要匹配的数据
		wrapper.eq("state", -1);
		//分页查询方法，page中第一个参数为当前页，第二个参数为每页显示条数
		//wrapper表示查询条件
		IPage<Order> selectPage = dao.selectPage(new Page<>(1,5),wrapper);
		if(selectPage==null||selectPage.getRecords()==null) {
			throw new RuntimeException("没有记录");
		}
		//获取分页查询的记录列表
		List<Order> records = selectPage.getRecords();		
		//获取总页数
		long size = selectPage.getSize();
		//当前满足条件总行数
		long total = selectPage.getTotal();
		log.info("总页数："+size);
		log.info("总行数："+total);
		log.info(records.toString());	
		return APIEntry.OK(records);
	}

	@Override
	public APIEntry updata(Order order) {
		//更新条件
		UpdateWrapper<Order> updateWrapper=new UpdateWrapper<>();
		updateWrapper.eq("state", -1);
		//根据更新条件更新数据
		int update = dao.update(order, updateWrapper);
		if(update<=0) {
			throw new RuntimeException("未更新数据");
		}
		return APIEntry.OK(update);
	}

}
