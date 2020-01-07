package com.wwy.test.upload.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.wwy.entry.APIEntry;
import com.wwy.test.upload.service.UpLoadService;
import lombok.extern.slf4j.Slf4j;
/**
 * 实现文件上传的service层
 * @author wwy
 * @date 2019年12月16日
 * @version v0.0.1
 *
 */

@Slf4j
@Service
public class UpLoadServiceImpl implements UpLoadService{
	@Value("${spring.servlet.multipart.location}")
	private String fileRootPath;
	
	@Override
	public APIEntry upLoad(MultipartFile multipartFile) {
		/////////////////////1.处理文件名///////////////////////////////////////////////////////////
		//获取原文件名
		String fileName=multipartFile.getOriginalFilename();
		//根据原文件名获取文件类型(后缀名,例如：.txt  .html .jpg)
		String suName=fileName.substring(fileName.indexOf("."));
		//定义文件名前缀，一般使用一个永不重复的字符串作为文件名
		String newPreFileName=UUID.randomUUID().toString().replace("-", "");
		//拼接新的文件名
		String newFileName=newPreFileName+suName;
		/////////////////////////2.定义上传路径/////////////////////////////////////////////////////
		//文件夹的名字用当前日期命名，例如2019-2-16上传的文件则存放在20196/12/16目录下
		//获取当前时间的字符串表示
		String Date=new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		//拼装文件路径
		String filePath=fileRootPath+"/"+Date+"/";
		//新建文件
		File file=new File(filePath+newFileName);
		//如果文件夹不存在就创建文件夹
		if(!file.getParentFile().exists()) {
			//注意这个坑：mkdirs()方法是创建多级目录，mkdir()只能创建单级目录！！！！
			boolean mkdir = file.getParentFile().mkdirs();
			if(!mkdir) {
				log.error("文件夹创建失败");
				new RuntimeException("创建文件夹失败");
			}
		}
		/////////////////////3.上传文件//////////////////////////////////////////////////////////////////////////
		//上传文件至指定的目录下的指定文件名
		try {
			multipartFile.transferTo(file);
			log.info("上传成功！");
			//返回虚拟路径，方便前端图片回显
			String path="/upload/"+Date+"/"+newFileName;
			log.info(path);
			return APIEntry.OK(path);			
		} catch (IllegalStateException | IOException e) {
			log.error("文件上传失败");
			log.error("path="+file.getName());
			e.printStackTrace();			
			return APIEntry.ERROR("文件上传失败");
		}
	}

}
