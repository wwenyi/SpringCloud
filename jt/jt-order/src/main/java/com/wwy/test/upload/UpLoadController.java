package com.wwy.test.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.wwy.entry.APIEntry;
import com.wwy.test.upload.service.UpLoadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件上传的controller层
 * @author wwy
 * @date 2019年12月16日
 * @version v0.0.1
 *
 */
@Api(tags = "文件上传的测试类")
@RestController
@RequestMapping("file")
public class UpLoadController {
	
	@Autowired
	private UpLoadService service;
	@ApiOperation("上传")
	@PostMapping("upload")
public APIEntry upLoad(MultipartFile multipartFile) {
	return service.upLoad(multipartFile);
}		
}
