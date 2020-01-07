package com.wwy.test.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wwy.annotation.Cache;
import com.wwy.entry.APIEntry;
import com.wwy.entry.Car;
import com.wwy.test.dao.read.ReadMapper;
import com.wwy.test.dao.write.WriteMapper;
import com.wwy.test.service.DataSourceTestService;

/**
 * 
 * @author wwy
 * @date 2019年12月12日
 * @version v0.0.1
 *
 */
@Service
public class DataSourceTestServiceImpl implements DataSourceTestService{

	@Autowired
	private ReadMapper readMapper;
	@Autowired
	private WriteMapper writeMapper;
	/** 
	 * 读
	 */
	@Override
	//@Cache(read = true,value= {"car"})//次查询通过redis缓存
	public APIEntry read() {
		// TODO Auto-generated method stub
		List<Car> list=readMapper.read();
		if(list!=null&&!list.isEmpty()) {
			return APIEntry.OK(list);
		}else {
			return APIEntry.ERROR("查询失败");
		}				
	}
	/**
	 * 写
	 */
	@Cache(read = false,value= {"car","order"})
	@Override
	public APIEntry write(Car car) {
		int count=writeMapper.write(car);
		if(count<=0) {
			return APIEntry.ERROR("写入失败");
		}else {
			return APIEntry.OK(null);
		}	
	}
}
