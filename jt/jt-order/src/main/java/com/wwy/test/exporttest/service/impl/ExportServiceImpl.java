package com.wwy.test.exporttest.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wwy.entry.APIEntry;
import com.wwy.test.exporttest.mapper.ExportMapper;
import com.wwy.test.exporttest.service.ExportService;
import com.wwy.util.ExportUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * excel导出的service层
 * @author wwy
 * @date 2019年12月31日
 * @version v0.0.1
 *
 */
@Slf4j
@Service
public class ExportServiceImpl implements ExportService{
	@Autowired
	private ExportMapper mapper;
	private static final String[] TITLE = {"订单ID","分销商昵称","商品名","客户昵称","商品数量","商品总价","代理总价","订单状态","快递单号"};

	@Override
	public APIEntry get(List<Long> id,HttpServletResponse response,HttpServletRequest request) {
		// 设置字符集
		 response.reset();
	     response.setCharacterEncoding("UTF-8");
		//校验参数
		if(id==null||id.size()==0) {
			id=null;
			log.info("导出全部");
		}
		//从数据库中查询数据
		List<Map<String,Object>> list=mapper.get(id);
		//验证数据
		if(list==null||list.size()==0) {
			return APIEntry.ERROR("没有数据");
		}
		for(Map<String,Object> map:list) {
			//将订单状态代号更换成汉字
			// 订单状态（未支付-1，未发货0，发货1，签收2）
			String state=map.get("订单状态")+"";
			switch(state) {
				case "-1":
					map.put("订单状态", "未支付");
					break;
				case "0":
					map.put("订单状态", "未发货");
					break;
				case "1":
					map.put("订单状态", "发货");
					break;
				case "2":
					map.put("订单状态", "签收");
			}
		}
		//导出
		HSSFWorkbook hssfWorkBook = ExportUtil.Export("订单", TITLE, list);
		//实现下载
		OutputStream out=null;
		try {
			//获取输出流
			out=response.getOutputStream();
			//输出
			hssfWorkBook.write(out);
			//设置响应头
			 response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("订单.xls", "UTF-8"));
			log.info("下载成功");
			return APIEntry.OK(URLEncoder.encode("filename="+"订单.xls", "UTF-8"));
		} catch (IOException e) {
			log.error("下载失败");
			e.printStackTrace();
			return APIEntry.ERROR("下载失败");
			//嵌套try{}catch ，保证流正常关闭
		}finally {
			 try {
				out.close();
				hssfWorkBook.close();
			} catch (IOException e) {
				out=null;
				hssfWorkBook=null;
				e.printStackTrace();
			}
		}		
	}
}
