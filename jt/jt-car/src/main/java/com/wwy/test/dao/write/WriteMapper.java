package com.wwy.test.dao.write;

import org.apache.ibatis.annotations.Mapper;

import com.wwy.entry.Car;

public interface WriteMapper {

	int write(Car car);

}
