package com.wwy.test.exporttest.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwy.entry.APIEntry;
import com.wwy.test.exporttest.service.ExportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 导出订单的excel表的controller
 * @author wwy
 * @date 2019年12月31日
 * @version v0.0.1
 *
 */
@RestController
@RequestMapping("export")
@Api(tags = "excel导出")
public class ExportController {

	@Autowired
private ExportService service;
@PostMapping("get")
@ApiOperation("导出")
public APIEntry get(@RequestBody List<Long> ids,HttpServletResponse response,HttpServletRequest request) {

	 return service.get(ids,response,request);
}
}
