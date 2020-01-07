package com.wwy.test.down;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwy.entry.APIEntry;
import com.wwy.util.DownUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("down")
@Api(tags = "文件下载")
public class DownController {
	
	
	@GetMapping("get")
	@ApiOperation("下载")
public APIEntry get( HttpServletResponse response,HttpServletRequest request,String filepath) {
	//1.判断用户权限
	//2.根据前端传递的filepath（回显的虚拟地址）查询真实文件路径
	//这里只做测试，直接把path写死，也不做权限的验证
		filepath="C:\\Users\\1\\Desktop\\20191204.zip";
		File file=new File(filepath);
		DownUtil.down(file, response, request);
		
		return APIEntry.OK(null);
}
}
