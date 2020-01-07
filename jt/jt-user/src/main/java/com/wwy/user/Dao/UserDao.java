package com.wwy.user.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wwy.entry.User;

@Mapper
public interface UserDao {

	int selectCount1();
	int selectCount2();
	List<User> selectAll1(@Param("page")Integer start, @Param("start")Integer page);
	List<User> selectAll2(@Param("page")Integer start, @Param("start")Integer page);
}
