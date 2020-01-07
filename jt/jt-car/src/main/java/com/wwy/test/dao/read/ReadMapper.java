package com.wwy.test.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wwy.entry.Car;

public interface ReadMapper {

	List<Car> read();

}
