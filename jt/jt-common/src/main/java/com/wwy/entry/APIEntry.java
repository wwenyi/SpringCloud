package com.wwy.entry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @author wwy
 * @date 2019年11月29日
 * @version v0.0.1
 *	返回前端数据的封装实体类
 */
@ApiModel("返回数据实体")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class APIEntry {
	/**
	 * 返回数据的状态码
	 * 500表示异常
	 * 200表示正常
	 */
	@ApiModelProperty("状态码")
	private int state;
	/**
	 * 返回前端的状态信息
	 */
	@ApiModelProperty("状态信息")
	private String msg;
	/**
	 * 记录数
	 */
	@ApiModelProperty("记录数")
	private int count;
	/**'
	 * 返回前端的数据
	 */
	@ApiModelProperty("返回数据")
	private Object data;
	
	/**
	 * 正常状态的方法
	 * @param 返回数据
	 * @return APIEntry
	 */
	public static APIEntry OK(Object data) {
		return new APIEntry(200,"OK",0,data);
	}
	/**
	 * 正常状态的方法
	 * @param 返回数据
	 * @return APIEntry
	 */
	public static APIEntry OK(Object data,int count) {
		return new APIEntry(200,"OK",count,data);
	}
	/**
	 * 正常状态的方法
	 * @param 返回数据
	 * @return APIEntry
	 */
	public static APIEntry ERROR(String msg) {
		return new APIEntry(500,msg,0,null);
	}
	public APIEntry(int state, String msg, int count, Object data) {
		super();
		this.state = state;
		this.msg = msg;
		this.count = count;
		this.data =  data;
	}
	
}
