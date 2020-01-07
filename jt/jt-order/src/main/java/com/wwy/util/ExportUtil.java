package com.wwy.util;

import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
/**
 * 文件导出的工具类
 * @author wwy
 * @date 2019年12月31日
 * @version v0.0.1
 *
 */

public class ExportUtil{

	/**
	 * 
	 * @param sheetName sheet名
	 * @param title 表头
	 * @param field 字段名
	 * @param values 值
	 * @return
	 */
	public static HSSFWorkbook Export(String sheetName,String[] title,List<Map<String,Object>> values) {
		//创建一个excel文件对象
		HSSFWorkbook hwb=new HSSFWorkbook();
		//新建一个sheet
		HSSFSheet sheet=hwb.createSheet(sheetName);
		//新建第0行
		HSSFRow row=sheet.createRow(0);	
		//新建列
		HSSFCell cell=null;
		//存入表头内容,并设置表头样式
		//定义样式
		HSSFCellStyle style = getStyle(hwb);	
		style.setFont(titleFont(hwb));
		for(int i=0;i<title.length;i++) {
			cell=row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
			//设置列宽
			sheet.setColumnWidth(i, 7000);
		}
		//将内容放入文件对象中，并设置样式
		HSSFCellStyle style2 = getStyle(hwb);	
		style2.setFont(valueFont(hwb));
		for(int i=0;i<values.size();i++) {
			//每一行的数据
			Map<String,Object> value=values.get(i);
			row=sheet.createRow(i+1);
			if(value!=null&&value.size()!=0) {	
				//遍历数据，将数据存入到文件对象中
				for(int j=0;j<title.length;j++) {
					cell=row.createCell(j);
					cell.setCellValue(value.get(title[j])+"");
					cell.setCellStyle(style2);
				}
			}	 
		}
		return hwb;
	}
	/**
	 * 设置表头字体
	 * @param h
	 * @return
	 */
	private static HSSFFont titleFont(HSSFWorkbook h) {
		HSSFFont font = h.createFont();
		//字体
		font.setFontName("黑体");
		//字号
		font.setFontHeightInPoints((short)14);
		//颜色
		font.setColor(HSSFFont.COLOR_RED);
		//加粗
		font.setBold(true);
		return font;
	}
	/**
	 * 设置内容字体
	 * @param h
	 * @return
	 */
	private static HSSFFont valueFont(HSSFWorkbook h) {
		HSSFFont font = h.createFont();
		//字体
		font.setFontName("仿宋_GB2312");
		//字号
		font.setFontHeightInPoints((short)14);

		return font;
	}
	/**
	 * 设置统一样式
	 * @param h
	 * @return
	 */
	@SuppressWarnings("static-access")
	private static HSSFCellStyle getStyle(HSSFWorkbook h) {
		//新建样式
		HSSFCellStyle style=h.createCellStyle();
		//设置水平居中
		style.setAlignment(HorizontalAlignment.CENTER);
		//设置垂直居中
		style.setVerticalAlignment(style.getVerticalAlignmentEnum().CENTER);
		//设置边框
		style.setBorderBottom(style.getBorderBottomEnum().THIN);
		style.setBorderLeft(style.getBorderBottomEnum().THIN);
		style.setBorderRight(style.getBorderBottomEnum().THIN);
		style.setBorderTop(style.getBorderBottomEnum().THIN);
		//设置自动换行
		style.setWrapText(true);
		return style;
	}
}
