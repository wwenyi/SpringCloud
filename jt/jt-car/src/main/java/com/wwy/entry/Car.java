package com.wwy.entry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("购物车")
public class Car {
	@ApiModelProperty("主键")
private Integer id;
	@ApiModelProperty("名称")
private String name;
	@ApiModelProperty("创建时间")
private String createTime;
	@ApiModelProperty("金额")
private Double money;
}
