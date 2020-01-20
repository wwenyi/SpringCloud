package com.wwy.entry;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("订单实体类")
@TableName("order")//mybatisplus指定实体类对应表名
public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1158966650124449133L;
	/**
	 * 订单id
	 */
	@ApiModelProperty("订单id")
	//mybatisplus指定主键对应字段，并指定自增
	@TableId(value="order_id",type = IdType.AUTO)
	private Long orderId;
	/**
	 * 分销商id
	 */
	@ApiModelProperty("分销商id")
	private Long disId;
	/**
	 * 商品id
	 */
	@ApiModelProperty("商品id")
	private Long commodityId;
	/**
	 * 客户id
	 */
	@ApiModelProperty("客户id")
	private Long customerId;
	/**
	 * 商品数量
	 */
	@ApiModelProperty("商品数量")
	private Integer number;
	/**
	 * 商品总价
	 */
	@ApiModelProperty("商品总价")
	private BigDecimal sumAmount;
	/**
	 * 代理总价
	 */
	@ApiModelProperty("代理总价")
	private String actualAmount;
	/**
	 * 订单状态（未支付-1，未发货0，发货1，签收2）
	 */
	@ApiModelProperty("订单状态（未支付-1，未发货0，发货1，签收2）")
	private Integer state;
	/**
	 * 快递单号
	 */
	@ApiModelProperty("快递单号")
	private String courier;

}
