package com.wwy.test.exporttest.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExportMapper {

	List<Map<String, Object>> get(@Param("ids")List<Long> ids);

}
