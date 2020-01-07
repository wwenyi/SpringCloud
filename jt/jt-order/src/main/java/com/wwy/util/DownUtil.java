package com.wwy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 下载文件的工具类
 * @author wwy
 * @date 2020年1月2日
 * @version v0.0.1
 *
 */
public class DownUtil {
public static void down(File file, HttpServletResponse response,HttpServletRequest request){	
	 FileInputStream in =null;
	 //创建输出流
	 OutputStream out = null;
	 try {
		 String filename = file.getName();
		 response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "UTF-8"));
		 //如果文件不存在直接返回false
		 if(!file.exists()){
		     return;
		 }
			 in = new FileInputStream(file);
			 out = response.getOutputStream();
		 //缓存区
		 byte buffer[] = new byte[1024*100];
		 int length=0;
		 while ((length = in.read(buffer)) != -1) {  
			    out.write(buffer, 0, length);  
			    out.flush();
			} 
		}catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException();
			 //关流
		 }finally {
			 try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				out=null;
				in=null;
			}
		 }
}
}
